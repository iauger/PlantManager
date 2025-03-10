package TestFiles;

import ProjectFiles.FileHandler;
import ProjectFiles.Species;
import ProjectFiles.Room;
import ProjectFiles.Plant;
import java.util.List;

public class FileHandlerTester {
    public static void main(String[] args) {

        // Testing the read methods in the class but write functionality should be tested in the  main program  flow
        // I'm hesitant to add  testing methods that will overwrite or append data  to storage 

        // Test Species File Read
        System.out.println("Testing readSpeciesFromFile()...");
        List<Species> speciesData = FileHandler.readSpeciesFromFile();

        if (speciesData.isEmpty()) {
            System.out.println("readSpeciesFromFile() Test Failed: No data found.");
        } 
        else {
            System.out.println("readSpeciesFromFile() Test Passed: Data loaded.");
        }

        // Test Rooms File Read
        System.out.println("\nTesting readRoomsFromFile()...");
        List<Room> roomData = FileHandler.readRoomsFromFile();

        if (roomData.isEmpty()) {
            System.out.println("readRoomsFromFile() Test Failed: No data found.");
        } 
        else {
            System.out.println("readRoomsFromFile() Test Passed: Data loaded.");
        }

        // Test Plants File Read
        System.out.println("\nTesting readPlantsFromFile()...");
        List<Plant> plantData = FileHandler.readPlantsFromFile(speciesData, roomData);

        if (plantData.isEmpty()) {
            System.out.println("readPlantsFromFile() Test Failed: No data found.");
        } 
        else {
            System.out.println("readPlantsFromFile() Test Passed: Data loaded.");
        }

        System.out.println("\nFileHandler Read Testing Complete!");
    }
}
