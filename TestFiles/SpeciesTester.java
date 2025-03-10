package TestFiles;

import ProjectFiles.Species;

public class SpeciesTester {
    public static void main(String[] args) {

        // Test Default Species Constructor
        ProjectFiles.Species mySpecies = new Species("Species_001", "Test Species", 7, "High");
        if (!(mySpecies.getSpeciesId().equals("Species_001") &&
              mySpecies.getSpeciesName().equals("Test Species") &&
              mySpecies.getWateringCadence() == 7 &&
              mySpecies.getLightRequirements().equals("High"))){
            System.out.println("Species Constructor Test Failed");
        } else {
            System.out.println("Species Constructor Test Passed!");
        } 
        if (!mySpecies.getSpeciesId().equals("Species_001")) {
            System.out.println("getSpeciesId() Test Failed");
        } else {
            System.out.println("getSpeciesId() Test Passed");
        }

         if (!mySpecies.getSpeciesName().equals("Test Species")) {
             System.out.println("getName() Test Failed");
         } else {
             System.out.println("getName() Test Passed");
         }

         if (!mySpecies.getLightRequirements().equals("High")) {
             System.out.println("getLightVolume() Test Failed");
         } else {
             System.out.println("getLightVolume() Test Passed");
         }

        // Test toString() Method
        String expectedToString = "Living Species (Exposure: South, Light: High)";
        if (!mySpecies.toString().equals(expectedToString)) {
            System.out.println("toString() Test Failed: Got \"" + mySpecies.toString() + "\" instead of \"" + expectedToString + "\"");
        } else {
            System.out.println("toString() Test Passed");
        }

        System.out.println("Species Class Testing Complete!");
    }
}
