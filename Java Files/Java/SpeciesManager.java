package PlantManager.Java;

import java.util.*;

public class SpeciesManager {

    private static Scanner scanner = new Scanner(System.in);

    // addSpecies Method construction
    public static void addSpecies(List<Species> speciesList) {
        while (true) {
            System.out.println("Add a New Plant Species");

            // Get Name
            System.out.println("Enter species name: ");
            String speciesName = scanner.nextLine().trim();
            if (speciesName.isEmpty()) {
                System.out.println("Name cannot be empty!");
                continue;
            }
            
            // Get Watering Cadence
            int  wateringCadence = 0;
            while (wateringCadence <= 0) {
                System.out.println("Enter a watering cadence in days (ex. 7):");
                try  {
                    wateringCadence = Integer.parseInt(scanner.nextLine().trim());
                    if (wateringCadence < 1) {
                        System.out.println("Cadence must be a positive integer!");
                        continue;
                    }
                } 
                catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a number.");
                }
            }

            // Light Requirements
            String lightRequirements = "";
            while  (!lightRequirements.matches("(?i)low|medium|high")) {
                System.out.println("Enter light requirements (Low, Medium, High): ");
                lightRequirements = scanner.nextLine().trim().toLowerCase();
                if (!lightRequirements.matches("low|medium|high")) {
                    System.out.println("Invalid input.  Please enter 'Low', 'Medium', or 'High'.");
                    continue;
                }
            }

            // Generate ID
            String speciesId = IDGenerator.generateSpeciesId(speciesList);

            // Create New Species Object
            Species newSpecies = new Species(speciesId, speciesName, wateringCadence, lightRequirements);

            // Save to storage
            speciesList.add(new Species(speciesId, speciesName, wateringCadence, lightRequirements));
            FileHandler.writeSpeciesToFile(speciesList);

            System.out.println("\nSuccessfully added new species: " + newSpecies);

            System.out.println("Do you want to add another species?");
            System.out.println("1) Add another species");
            System.out.println("2) Return to Main Menu");

            try {
                int choice = Integer.parseInt(scanner.nextLine().trim());
                if (choice == 2) {
                    break;
                }
            }
            catch (NumberFormatException e) {
                System.out.println("Invalid choice. Please enter 1 or 2.");
            }
        }
    }
}
