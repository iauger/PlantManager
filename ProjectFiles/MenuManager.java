package ProjectFiles;
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

        // Menu formatting
        while (true) {
            clearConsole();
            System.out.println("\nWelcome to Plant Manager");
            System.out.println("-----------------------------------");
            System.out.println("1) Add, Update and View Plants");
            System.out.println("2) Add & View Species");
            System.out.println("3) Add & View Rooms");
            System.out.println("4) Log Watering Event");
            System.out.println("5) Deactivate a Plant");
            System.out.println("6) Reactivate a Plant");
            System.out.println("7) Reset Database");
            System.out.println("8) Exit");
            System.out.println("-----------------------------------");
            System.out.print("Enter your choice: ");

            // Menu flow
            try {
                int choice = Integer.parseInt(scanner.nextLine().trim());
                switch (choice) {
                    case 1:
                        while (true) {
                            System.out.println("\nPlant Management:");
                            System.out.println("1) Add a new plant");
                            System.out.println("2) View existing plants");
                            System.out.println("3) Move existing plant to new room");
                            System.out.println("4) Return to Main Menu");

                            int plantChoice = -1;
                            try {
                                // Process User input
                                plantChoice = Integer.parseInt(scanner.nextLine().trim());
                                switch (plantChoice) {
                                    
                                    case 1:
                                        PlantManager.addPlant(plantList, speciesList, roomList);
                                        continue;
                                    case 2:
                                        DatabaseManager.displayPlantDatabase(plantList);
                                        continue;
                                    case 3:
                                        PlantManager.movePlant(plantList, roomList);
                                        continue;
                                    case 4:
                                        clearConsole();
                                        break;
                                
                                    default:
                                        break;
                                }
                            }
                            catch (NumberFormatException e) {
                                System.out.println("Invalid input: " + e.getMessage());
                            }
                        
                            if (plantChoice == 4) { 
                                break;
                            } 
                        }
                        break;
                    case 2:
                    // Initialize instance for manager class
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
                    case 3:
                        // Initialize instance of manager class
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
                                        continue; 
                                    case 2:
                                        roomManager.display(roomList);
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
                        
                            if (roomChoice == 3) { 
                                break;
                            }
                        }
                        break;
                    case 4:
                        PlantManager.logWateringEvent(plantList, roomList);
                        break;
                    case 5:
                        PlantManager.deactivatePlant(plantList);
                        break;
                    case 6:
                        PlantManager.reactivatePlant(plantList);
                        break;
                    case 7:
                        DatabaseManager.resetDatabase(speciesList, roomList, plantList);
                        break;
                    case 8:
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
    // Method to "clear" the console by adding blank lines to terminal
    public static void clearConsole() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
    
}
