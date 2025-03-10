package TestFiles;

import ProjectFiles.Plant;
import ProjectFiles.Species;
import ProjectFiles.Room;
import java.time.LocalDate;

public class PlantTester {
    public static void main(String[] args) {

        // Create Mock Species and Room Objects
        Species testSpecies = new Species("species_001", "Pothos", 7, "Medium");
        Room testRoom = new Room("room_001", "Living Room", "South", "High");

        // Test Plant Constructor
        LocalDate lastWatered = LocalDate.of(2024, 3, 1);
        LocalDate nextWatering = lastWatered.plusDays(testSpecies.getWateringCadence());

        Plant myPlant = new Plant("pothos_001", testSpecies, testRoom, lastWatered, nextWatering);

        if (!myPlant.getPlantTag().equals("pothos_001")) {
            System.out.println("Plant Constructor Test Failed (Plant Tag)");
        } else {
            System.out.println("Plant Constructor Test Passed (Plant Tag)");
        }

        if (!myPlant.getSpecies().getSpeciesName().equals("Pothos")) {
            System.out.println("Plant Constructor Test Failed (Species)");
        } else {
            System.out.println("Plant Constructor Test Passed (Species)");
        }

        if (!myPlant.getRoom().getName().equals("Living Room")) {
            System.out.println("Plant Constructor Test Failed (Room)");
        } else {
            System.out.println("Plant Constructor Test Passed (Room)");
        }

        // Test Watering Date Update
        myPlant.setLastWateredDate(LocalDate.of(2024, 3, 5));
        myPlant.setNextWateringDate(myPlant.getLastWateredDate().plusDays(myPlant.getSpecies().getWateringCadence()));

        if (!myPlant.getNextWateringDate().equals(LocalDate.of(2024, 3, 12))) {
            System.out.println("Watering Date Update Test Failed");
        } else {
            System.out.println("Watering Date Update Test Passed");
        }

        // Test Room Update
        Room newRoom = new Room("room_002", "Bedroom", "West", "Low");
        myPlant.setRoom(newRoom);

        if (!myPlant.getRoom().getName().equals("Bedroom")) {
            System.out.println("Room Update Test Failed");
        } else {
            System.out.println("Room Update Test Passed");
        }

        // Test Deactivate Method
        myPlant.setActive(false);
        if (myPlant.isActive()) {
            System.out.println("Deactivate Method Test Failed");
        } else {
            System.out.println("Deactivate Method Test Passed");
        }

        // Test Reactivate Method
        myPlant.setActive(true);
        if (!myPlant.isActive()) {
            System.out.println("Reactivate Method Test Failed");
        } else {
            System.out.println("Reactivate Method Test Passed");
        }

        // Test toString() Method
        String expectedToString = "pothos_001) Species-Pothos, Room-Bedroom, Next Watering Date-2024-03-12";
        if (!myPlant.toString().equals(expectedToString)) {
            System.out.println("toString() Test Failed");
        } else {
            System.out.println("toString() Test Passed");
        }

        System.out.println("\nPlant Class Testing Complete!");
    }
}
