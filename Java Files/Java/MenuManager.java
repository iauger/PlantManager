package PlantManager.Java;

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
            System.out.println("3) Add a New Species");
            System.out.println("4) Add a New Room");
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
                        SpeciesManager.addSpecies(speciesList);
                        break;
                    case 4:
                        RoomManager.addRoom(roomList);
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
