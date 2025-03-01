package PlantManager.Java;

import java.time.LocalDate;
import java.util.*;

public class DatabaseManager {

    private static Scanner scanner = new Scanner(System.in);

    public static void resetDatabase(List<Species> speciesList, List<Room> roomList, List<Plant> plantList) {
        System.out.print("Are you sure you want to reset the database? This will delete all current data. (Y/N): ");
        String confirmation = scanner.nextLine().trim().toLowerCase();
    
        if (confirmation.equals("y")) {
            plantList.clear();
            speciesList.clear();
            roomList.clear();
    
            speciesList.addAll(getDefaultSpecies());
            roomList.addAll(getDefaultRooms());
            plantList.addAll(getDefaultPlants(speciesList, roomList));
    
            FileHandler.writePlantsToFile(plantList);
            FileHandler.writeRoomsToFile(roomList);
            System.out.println("All database records have been reset to default values.");
        } else {
            System.out.println("Database reset cancelled.");
        }
    }
    
    private static List<Plant> getDefaultPlants(List<Species> speciesList, List<Room> roomList) {
        List<Plant> plants = new ArrayList<>();
        plants.add(new Plant("pothos_001", findSpeciesById("species_001", speciesList), findRoomById("room_001", roomList), LocalDate.now(), LocalDate.now().plusDays(7)));
        plants.add(new Plant("monstera_001", findSpeciesById("species_002", speciesList), findRoomById("room_002", roomList), LocalDate.now(), LocalDate.now().plusDays(10)));
        plants.add(new Plant("snake_plant_001", findSpeciesById("species_003", speciesList), findRoomById("room_003", roomList), LocalDate.now(), LocalDate.now().plusDays(14)));
        return plants;
    }
    
    private static List<Species> getDefaultSpecies() {
        List<Species> species = new ArrayList<>();
        species.add(new Species("species_001", "Pothos", 7, "Medium"));
        species.add(new Species("species_002", "Monstera", 10, "High"));
        species.add(new Species("species_003", "Snake Plant", 14, "Low"));
        return species;
    }
    
    private static List<Room> getDefaultRooms() {
        List<Room> rooms = new ArrayList<>();
        rooms.add(new Room("room_001", "Living Room", "South", "High"));
        rooms.add(new Room("room_002", "Kitchen", "East", "Medium"));
        rooms.add(new Room("room_003", "Bedroom", "West", "Low"));
        return rooms;
    }
    
    private static Species findSpeciesById(String speciesId, List<Species> speciesList) {
        for (Species species : speciesList) {
            if (species.getSpeciesId().equals(speciesId)) {
                return species;
            }
        }
        return null;
    }

    private static Room findRoomById(String roomId, List<Room> roomList) {
        for (Room room : roomList) {
            if (room.getRoomId().equals(roomId)) {
                return room;
            }
        }
        return null;
    }

    public static List<Plant> loadPlantDatabase(List<Species> speciesList, List<Room> roomList) {
        return FileHandler.readPlantsFromFile(speciesList, roomList);
    }
    
    public static List<Species> loadSpeciesDatabase() {
        return FileHandler.readSpeciesFromFile();
    }    
    
    public static List<Room> loadRoomDatabase() {
        return FileHandler.readRoomsFromFile();
    }

    public static void displayPlantDatabase(List<Plant> plantList) {
        // Sort plants by Room Name first, then by Species Name, then by Next Watering Date
        while (true) {
            plantList.sort((p1, p2) -> {
                int roomCompare = p1.getRoomName().compareTo(p2.getRoomName());
                if (roomCompare != 0) return roomCompare;

                int speciesCompare = p1.getSpeciesName().compareTo(p2.getSpeciesName());
                if (speciesCompare != 0) return speciesCompare;

                return p1.getNextWateringDate().compareTo(p2.getNextWateringDate());
            });

            // Print database headers
            System.out.println("\nPlant Database");
            System.out.println("---------------------------------------------------");
            System.out.printf("%-15s | %-15s | %-15s | %-12s | %-12s\n",
                    "Plant ID", "Species", "Room", "Last Watered", "Next Watering");
            System.out.println("---------------------------------------------------");

            // Track current room to print headers correctly
            String currentRoom = "";
            for (Plant plant : plantList) {
                String roomName = plant.getRoomName();
                String speciesName = plant.getSpeciesName();

                // Print room header only when it changes
                if (!roomName.equals(currentRoom)) {
                    currentRoom = roomName;
                    System.out.println("\nRoom: " + roomName);
                }

                // Print plant details
                System.out.printf("%-15s | %-15s | %-15s | %-12s | %-12s\n",
                        plant.getPlantTag(), speciesName, roomName,
                        plant.getLastWateredDate(), plant.getNextWateringDate());
            }

            System.out.println("\nPress Enter to return to Main Menu");
            scanner.nextLine();  // Waits for user input before returning
            return;
        }
    }
}
