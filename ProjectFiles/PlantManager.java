package ProjectFiles;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;


public class PlantManager {
    
    private static Scanner scanner = new Scanner(System.in);

    public static void addPlant(List<Plant> plantList, List<Species> speciesList, List<Room> roomList) {
        while (true) {
            System.out.println("Add a new Plant");

            Species selectedSpecies = null;
            while (selectedSpecies == null) {
                System.out.println("Available Species:");
                for (int i = 0; i < speciesList.size(); i++) {
                    System.out.println((i + 1) + ": " + speciesList.get(i).getSpeciesName());
                }
                System.out.println("Select a Species by number from 1 to " + speciesList.size());

                try {
                    int speciesIndex = Integer.parseInt(scanner.nextLine().trim()) - 1;
                    if (speciesIndex >= 0 && speciesIndex < speciesList.size()) {
                        selectedSpecies = speciesList.get(speciesIndex);
                    } else {
                        System.out.println("Invalid choice, please enter a number between 1 and " + speciesList.size());
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Input must be a number between 1 and " + speciesList.size());
                }
            }

            Room selectedRoom = null;
            while (selectedRoom == null) {
                System.out.println("\nAvailable Rooms:");
                for (int i = 0; i < roomList.size(); i++) {
                    System.out.println((i + 1) + ": " + roomList.get(i).getName());
                }
                System.out.println("Select a Room by number from 1 to " + roomList.size());

                try {
                    int roomIndex = Integer.parseInt(scanner.nextLine().trim()) - 1;
                    if (roomIndex >= 0 && roomIndex < roomList.size()) {
                        selectedRoom = roomList.get(roomIndex);
                    } else {
                        System.out.println("Invalid choice, please enter a number between 1 and " + roomList.size());
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Input must be a number between 1 and " + roomList.size());
                }
            }

            LocalDate lastWateredDate = null;
            while (lastWateredDate == null) {
                System.out.print("\nEnter last watered date (YYYY-MM-DD): ");
                String userInput = scanner.nextLine().trim();
                try {
                    lastWateredDate = LocalDate.parse(userInput);
                    if (lastWateredDate.isAfter(LocalDate.now())) {
                        System.out.println("Error: The date cannot be in the future. Try again.");
                        lastWateredDate = null;
                    }
                } catch (DateTimeParseException e) {
                    System.out.println("Invalid date format! Please use YYYY-MM-DD.");
                }
            }

            // Generate the next watering date
            LocalDate nextWateringDate = lastWateredDate.plusDays(selectedSpecies.getWateringCadence());

            // Ensure nextWateringDate is not in the past
            if (nextWateringDate.isBefore(LocalDate.now())) {
                nextWateringDate = LocalDate.now();
            }

            String plantId = IDGenerator.generatePlantId(selectedSpecies.getSpeciesName(), plantList);
            Plant newPlant = new Plant(plantId, selectedSpecies, selectedRoom, lastWateredDate, nextWateringDate);

            plantList.add(newPlant);
            FileHandler.writePlantsToFile(plantList);

            System.out.println("\nSuccessfully added new plant: " + newPlant);

            break;
        }
    }

    // Log Watering Events
    public static  void logWateringEvent(List<Plant> plantList, List<Room> roomList) {
        System.out.println("\nLog a Watering Event");
        if (plantList.isEmpty()) {
                System.out.println("There are no plants in your home.");
                return;
            }
        while (true) {  

            // Display rooms for User
            System.out.println("Select a room to view plants that need to be watered:");
            int roomIndex;

            // Pick a room
            while (true) {
                for (int i = 0; i < roomList.size(); i++) {
                    System.out.println((i + 1) + ") " + roomList.get(i).getName());
                }
                try {
                    roomIndex = Integer.parseInt(scanner.nextLine().trim()) - 1;
                    if (roomIndex >= 0 && roomIndex < roomList.size()) {
                        break;  
                    }
                    System.out.println("Invalid selection, please enter a number between 1 and " + roomList.size());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a number.");
                }
            }
            
            Room selectedRoom = roomList.get(roomIndex);
            LocalDate today = LocalDate.now();
            List<Plant> plantsToWater = new ArrayList<>();

            for (Plant plant : plantList) { 
                if (plant.getRoom().equals(selectedRoom) && !plant.getNextWateringDate().isAfter(today)) {
                    plantsToWater.add(plant);
                }
            }

            Plant selectedPlant = null;

            while (true) {
                if (plantsToWater.isEmpty()) {
                    System.out.println("No plants in " + selectedRoom.getName() + " need to be watered.");
                    break;
                }

                System.out.println("Plants in " + selectedRoom.getName() + " that need to be watered: ");
                for (int i = 0; i < plantsToWater.size(); i++) {
                    System.out.println((i + 1) + ") " + plantsToWater.get(i));
                }

                System.out.println("Select a plant to water from the list to log a watering event: ");
                try {
                    int plantIndex = Integer.parseInt(scanner.nextLine().trim()) - 1;
                    
                    if (plantIndex < 0 || plantIndex >= plantsToWater.size()) {
                        System.out.println("Invalid selection. Please enter a valid plant number.");
                        continue; 
                    }

                    selectedPlant = plantsToWater.get(plantIndex);
                    break; // Exit loop once a valid plant is selected
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a number.");
                }
            }

            // Ensure a plant was selected before proceeding
            if (selectedPlant != null) {
                selectedPlant.setLastWateredDate(today);
                selectedPlant.setNextWateringDate(today.plusDays(selectedPlant.getSpecies().getWateringCadence())); // Adjust NextWateringDate by Cadence variable

                // Print confirmation for User
                System.out.println("Successfully logged watering for " + selectedPlant.getPlantTag());
                System.out.println("Next watering scheduled for " + selectedPlant.getNextWateringDate());

                // Update storage
                FileHandler.writePlantsToFile(plantList);
            }

            System.out.println("Do you want to check plants in another room?");
            System.out.println("1) Enter new Room");
            System.out.println("2) Return to Main Menu");

            try {
                int userInt = Integer.parseInt(scanner.nextLine().trim());
                if (userInt == 2) {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Error. Please enter 1 or 2.");
            }
        }
    }

    public static void movePlant(List<Plant> plantList, List<Room> roomList) {
        if (plantList.isEmpty()) {
            System.out.println("There are no plants in your home.");
            return;
        }
    
        List<Plant> activePlants = new ArrayList<>();
        for (Plant plant : plantList) {
            if (plant.isActive()) {
                activePlants.add(plant);
            }
        }
    
        if (activePlants.isEmpty()) {
            System.out.println("There are no active plants to move.");
            return;
        }
    
        Plant selectedPlant = null;
        Room selectedRoom = null;
    
        // Select a Plant
        while (true) {
            System.out.println("\nActive Plants List:");
            for (int i = 0; i < activePlants.size(); i++) {
                System.out.println((i + 1) + ") " + activePlants.get(i));
            }
            System.out.println("Enter the number of the plant you want to move, or 0 to cancel:");
    
            try {
                int plantIndex = Integer.parseInt(scanner.nextLine().trim()) - 1;
    
                if (plantIndex == -1) {
                    System.out.println("Move canceled.");
                    break;
                }
    
                if (plantIndex < 0 || plantIndex >= activePlants.size()) {
                    System.out.println("Invalid selection. Please enter a number between 1 and " + activePlants.size());
                    continue;
                }
    
                selectedPlant = activePlants.get(plantIndex);
                break; // Exit loop once a valid plant is selected
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    
        // Select a New Room
        while (true) {
            System.out.println("\nAvailable Rooms:");
            for (int i = 0; i < roomList.size(); i++) {
                System.out.println((i + 1) + ") " + roomList.get(i).getName());
            }
            System.out.println("Which room would you like to move " + selectedPlant.getSpeciesName() + " to?");
            System.out.println("Enter a number between 1 and " + roomList.size() + ", or 0 to cancel:");
    
            try {
                int roomIndex = Integer.parseInt(scanner.nextLine().trim()) - 1;
    
                if (roomIndex == -1) {
                    System.out.println("Move canceled.");
                    break;
                }
    
                if (roomIndex < 0 || roomIndex >= roomList.size()) {
                    System.out.println("Invalid selection. Please enter a number between 1 and " + roomList.size());
                    continue;
                }
    
                selectedRoom = roomList.get(roomIndex);
    
                // Prevent moving to the same room
                if (selectedPlant.getRoom().equals(selectedRoom)) {
                    System.out.println("" + selectedPlant.getSpeciesName() + " is already in " + selectedRoom.getName() + ".");
                    continue;
                }
    
                break; // Exit loop once a valid room is selected
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    
        // Move the Plant & Save Changes
        selectedPlant.setRoom(selectedRoom);
        System.out.println(selectedPlant.getSpeciesName() + " has been moved to " + selectedRoom.getName());
    
        // Update file storage
        FileHandler.writePlantsToFile(plantList);
    }
    

    public static void deactivatePlant(List<Plant> plantList) {
        
        List<Plant> activePlants = new ArrayList<>();
        Plant selectedPlant = null;
        
        // Identify all active plants
        for (Plant plant : plantList) {
            if (plant.isActive()) {
                activePlants.add(plant);
            }
        }

        if (activePlants.isEmpty()) {
            System.out.println("There are no active plants in your home.");
            return;
        }

        while (true) {
            
            // Present active list to User
            System.out.println("Active Plants List:");
            for (int i = 0; i < activePlants.size(); i++) {
                System.out.println((i + 1) + ") " + activePlants.get(i));
            }

            // Accept input from User for deactivation
            System.out.println("Select the Plant that you would like to deactivate: ");
            try {
                int plantIndex = Integer.parseInt(scanner.nextLine().trim()) - 1;
                
                if (plantIndex < 0 || plantIndex >= activePlants.size()) {
                    System.out.println("Invalid selection. Please enter a number between 1 and " + activePlants.size());
                    continue; 
                }

                selectedPlant = activePlants.get(plantIndex);
                break; // Exit loop once a valid plant is selected
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }

        // Confirmation for User
        selectedPlant.setActive(false);
        System.out.println(selectedPlant.getSpeciesName() + " in " + selectedPlant.getRoomName() + " has been deactivated.");

        // Update storage
        FileHandler.writePlantsToFile(plantList);
        return;
    }

    // Method for reactivating inactive plants
    // Identical flow as above, but filtering for isActive = False
    public static void reactivatePlant(List<Plant> plantList) {
        
        List<Plant> inactivePlants = new ArrayList<>();
        Plant selectedPlant = null;
        
        for (Plant plant : plantList) {
            if (!plant.isActive()) {
                inactivePlants.add(plant);
            }
        }

        if (inactivePlants.isEmpty()) {
            System.out.println("There are no inactive plants in your home.");
            return;
        }

        while (true) {
            
            System.out.println("Inactive Plants List:");
            for (int i = 0; i < inactivePlants.size(); i++) {
                System.out.println((i + 1) + ") " + inactivePlants.get(i));
            }

            System.out.println("Select the Plant that you would like to reactivate: ");
            try {
                int plantIndex = Integer.parseInt(scanner.nextLine().trim()) - 1;
                
                if (plantIndex < 0 || plantIndex >= inactivePlants.size()) {
                    System.out.println("Invalid selection. Please enter a number between 1 and " + inactivePlants.size());
                    continue; 
                }

                selectedPlant = inactivePlants.get(plantIndex);
                break; // Exit loop once a valid plant is selected
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }

        selectedPlant.setActive(true);
        System.out.println(selectedPlant.getSpeciesName() + " in " + selectedPlant.getRoomName() + " has been reactivated.");

        FileHandler.writePlantsToFile(plantList);
        return;
    }
}



