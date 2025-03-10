package ProjectFiles;
import java.util.*;

public class SpeciesManager extends BaseObjectManager<Species> {

    private static Scanner scanner = new Scanner(System.in);

    // Extended add Method construction
    public void add(List<Species> speciesList) {
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
            break;
        }
    }

    // Extended display method
    public void display(List<Species> speciesList) {
        if (speciesList.isEmpty()) {
            System.out.println("No species available.");
            return;
        }
        for (Species species : speciesList) {
            System.out.println(species);
        }
    }
}
