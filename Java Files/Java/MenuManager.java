import java.util.*;

public class MenuManager {
    private static Scanner scanner = new Scanner(System.in);
    private static List<Room> roomList;
    private static List<Species> speciesList;
    private static List<Plant> plantList;

    public static void main(String[] args) {
        // Load the database
        roomList = DatabaseManager.loadRoomDatabase();
        speciesList = DatabaseManager.loadSpeciesDatabase();
        plantList = DatabaseManager.loadPlantDatabase(speciesList, roomList);

        while (true) {
            clearConsole();
            System.out.println("\nWelcome to Plant Manager");
            System.out.println("-----------------------------------");
            System.out.println("1) View Plant Database");
            System.out.println("2) Add a New Plant");
            System.out.println("3) Add & View Species");
            System.out.println("4) Add & View Rooms");
            System.out.println("5) Log Watering Event");
            System.out.println("6) Deactivate a Plant");
            System.out.println("7) Reactivate a Plant");
            System.out.println("8) Reset Database");
            System.out.println("9) Exit");
            System.out.println("-----------------------------------");
            System.out.print("Enter your choice: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine().trim());
                switch (choice) {
                    case 1:
                        DatabaseManager.displayPlantDatabase(plantList);
                        break;
                    case 2:
                        PlantManager.addPlant(plantList, speciesList, roomList);
                        break;
                    case 3:
                    SpeciesManager speciesManager = new SpeciesManager();

                    while (true) {
                        System.out.println("\nSpecies Management:");
                        System.out.println("1) Add a new plant species");
                        System.out.println("2) View existing plant species");
                        System.out.println("3) Return to Main Menu");
                    
                        int speciesChoice = -1;
                    
                        try {
                            speciesChoice = Integer.parseInt(scanner.nextLine().trim());
                    
                            switch (speciesChoice) {
                                case 1:
                                    speciesManager.add(speciesList);
                                    continue; 
                                case 2:
                                    speciesManager.display(speciesList);
                                    continue;
                                case 3:
                                    clearConsole();
                                    break; 
                                default:
                                    System.out.println("Invalid input, please select 1, 2, or 3.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input: " + e.getMessage());
                        }
                    
                        if (speciesChoice == 3) { 
                            break;
                        }
                    }
                    break;
                    case 4:
                        RoomManager roomManager = new RoomManager();
                        while (true) {
                            System.out.println("\nRoom Management:");
                            System.out.println("1) Add a new room");
                            System.out.println("2) View existing rooms");
                            System.out.println("3) Return to Main Menu");
                        
                            int roomChoice = -1;
                        
                            try {
                                roomChoice = Integer.parseInt(scanner.nextLine().trim());
                        
                                switch (roomChoice) {
                                    case 1:
                                        roomManager.add(roomList);
                                        continue; // ✅ Stays inside Room Management
                                    case 2:
                                        roomManager.display(roomList);
                                        continue; // ✅ Stays inside Room Management
                                    case 3:
                                        clearConsole();
                                        break; // ✅ Exit Room Management
                                    default:
                                        System.out.println("Invalid input, please select 1, 2, or 3.");
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("Invalid input: " + e.getMessage());
                            }
                        
                            if (roomChoice == 3) { // ✅ Ensures loop exits when returning to Main Menu
                                break;
                            }
                        }
                        break;
                    case 5:
                        PlantManager.logWateringEvent(plantList, roomList);
                        break;
                    case 6:
                        PlantManager.deactivatePlant(plantList);
                        break;
                    case 7:
                        PlantManager.reactivatePlant(plantList);
                        break;
                    case 8:
                        DatabaseManager.resetDatabase(speciesList, roomList, plantList);
                        break;
                    case 9:
                        System.out.println("Goodbye!");
                        return;
                    default:
                        System.out.println("Invalid choice. Please enter a number from 1 to 8.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number from 1 to 8.");
            }
        }
    }
    // method to "clear" the console 
    public static void clearConsole() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
    
}
