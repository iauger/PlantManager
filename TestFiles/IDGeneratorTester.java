package TestFiles;
import ProjectFiles.Plant;
import ProjectFiles.Species;
import ProjectFiles.Room;

import java.util.ArrayList;
import java.util.List;

import ProjectFiles.IDGenerator;

public class IDGeneratorTester {

    public static void main(String[] args) {
        
        // Initialize mock speciesList and add an object
        List<Species> speciesList = new ArrayList<>();
        speciesList.add(new Species("species_001", "Pothos", 7, "Medium"));

        // Call generateSpeciesId method and verify output
        String newSpeciesID = IDGenerator.generateSpeciesId(speciesList);
        if (!newSpeciesID.equals("species_002")) {
            System.out.println("generateSpeciesId() Test Failed: Got " + newSpeciesID);
        }
        else {
            System.out.println("generateSpeciesId() Test Passed");
        }

        // Initialize mock roomList and add an object
        List<Room> roomList = new ArrayList<>();
        roomList.add(new Room("room_001", "Living Room", "South", "High"));

        // Call generateRoomId method and verify output
        String newRoomId = IDGenerator.generateRoomId(roomList);
        if (!newRoomId.equals("room_002")) {
            System.out.println("generateRoomId() Test Failed: Got " + newRoomId + ", Expected room_004");
        } else {
            System.out.println("generateRoomId() Test Passed");
        }

        // Initialize mock plantList
        List<Plant> plantList = new ArrayList<>();
        plantList.add(new Plant("pothos_001", speciesList.get(0), roomList.get(0), null, null));
        
        // Call generatePlantId method and verify output
        String newPlantId = IDGenerator.generatePlantId("Pothos", plantList);
        if (!newPlantId.equals("pothos_002")) {
            System.out.println("generatePlantId() Test Failed: Got " + newPlantId + ", Expected pothos_003");
        } else {
            System.out.println("generatePlantId() Test Passed");
        }

        System.out.println("\nIDGenerator Class Testing Complete");
    }
    
}
