package TestFiles;

import ProjectFiles.SpeciesManager;
import ProjectFiles.Species;

import java.util.ArrayList;
import java.util.List;

public class SpeciesManagerTester {
    public static void main(String[] args) {

        // At present I cannot figure out how to whitebox test the add() method since by design it takes User input to construct the new object
        // Without altering the structure of the method to be able to pass an object as an argument, I cannot write a test for the method

        // Initialize SpeciesManager and Mock Species List
        SpeciesManager speciesManager = new SpeciesManager();
        List<Species> speciesList = new ArrayList<>();

        // Test Display Method
        System.out.println("\nTesting display() method:");
        speciesManager.display(speciesList);  // Should print all species

        System.out.println("\nSpeciesManager Class Testing Complete!");
    }
}
