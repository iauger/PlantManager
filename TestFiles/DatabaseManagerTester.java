package TestFiles;

import ProjectFiles.DatabaseManager;
import ProjectFiles.Species;
import ProjectFiles.Room;
import ProjectFiles.Plant;

import java.util.ArrayList;
import java.util.List;

public class DatabaseManagerTester {
    public static void main(String[] args) {

        // Initialize mock database lists
        List<Species> speciesList = new ArrayList<>();
        List<Room> roomList = new ArrayList<>();
        List<Plant> plantList = new ArrayList<>();

        // Prepopulate species list for testing
        speciesList.add(new Species("species_001", "Pothos", 7, "Medium"));
        speciesList.add(new Species("species_002", "Monstera", 10, "High"));
        speciesList.add(new Species("species_003", "Snake Plant", 14, "Low"));

        // Prepopulate room list for testing
        roomList.add(new Room("room_001", "Living Room", "South", "High"));
        roomList.add(new Room("room_002", "Kitchen", "East", "Medium"));
        roomList.add(new Room("room_003", "Bedroom", "West", "Low"));

        // Prepopulate plant list for testing
        plantList.add(new Plant("pothos_001", speciesList.get(0), roomList.get(0), null, null));
        plantList.add(new Plant("monstera_001", speciesList.get(1), roomList.get(1), null, null));
        plantList.add(new Plant("snake_plant_001", speciesList.get(2), roomList.get(2), null, null));

        // Test Reset Database Method
        System.out.println("\n🔄 Testing resetDatabase()...");
        DatabaseManager.resetDatabase(speciesList, roomList, plantList);

        if (speciesList.size() != 3 || roomList.size() != 3 || plantList.size() != 3) {
            System.out.println("resetDatabase() Test Failed: Lists not reset correctly.");
        } else {
            System.out.println("resetDatabase() Test Passed");
        }

        // Test Load Methods
        System.out.println("\nTesting loadSpeciesDatabase()...");
        List<Species> loadedSpecies = DatabaseManager.loadSpeciesDatabase();
        if (loadedSpecies.isEmpty()) {
            System.out.println("loadSpeciesDatabase() Test Failed: No species loaded.");
        } else {
            System.out.println("loadSpeciesDatabase() Test Passed");
        }

        System.out.println("\nTesting loadRoomDatabase()...");
        List<Room> loadedRooms = DatabaseManager.loadRoomDatabase();
        if (loadedRooms.isEmpty()) {
            System.out.println("loadRoomDatabase() Test Failed: No rooms loaded.");
        } else {
            System.out.println("loadRoomDatabase() Test Passed");
        }

        System.out.println("\nTesting loadPlantDatabase()...");
        List<Plant> loadedPlants = DatabaseManager.loadPlantDatabase(speciesList, roomList);
        if (loadedPlants.isEmpty()) {
            System.out.println("loadPlantDatabase() Test Failed: No plants loaded.");
        } else {
            System.out.println("loadPlantDatabase() Test Passed");
        }

        // Test Display Method (No assertions, just visual validation)
        System.out.println("\nTesting displayPlantDatabase()...");
        DatabaseManager.displayPlantDatabase(plantList); // Should print plant list properly

        System.out.println("\nDatabaseManager Class Testing Complete!");
    }
}
