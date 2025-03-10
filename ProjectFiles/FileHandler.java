package ProjectFiles;
import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class FileHandler {

    // Initialize CSV files by creating them if they do not exist
    // Ensure proper headers are written for each file
    public static void initializeCSVFiles() {
        createFileIfMissing("Data/plants.csv", new String[]{"plantTag", "speciesId", "roomId", "lastWateredDate", "nextWateringDate"});
        createFileIfMissing("Data/species.csv", new String[]{"speciesId", "speciesName", "wateringCadence", "lightRequirements"});
        createFileIfMissing("Data/rooms.csv", new String[]{"roomId", "roomName", "sunExposure", "lightVolume"});
    }

    // Creates the file if not present and writes the  header row
    private static void createFileIfMissing(String filename, String[] headers) {
        File file = new File(filename);
        if (!file.exists()) {
            try {
                new File("Data").mkdirs(); // Ensure directory exists
                BufferedWriter bw = new BufferedWriter(new FileWriter(file));
                bw.write(String.join(",", headers));
                bw.newLine();
                bw.close();
                System.out.println("Created missing file: " + filename);
            } catch (IOException e) {
                System.out.println("Error creating file: " + filename);
            }
        }
    }

    // Reads plant data from 'plants.csv' and  converts the file to a list  of Plant objects.
    public static List<Plant> readPlantsFromFile(List<Species> speciesList, List<Room> roomList) {
        List<Plant> plants = new ArrayList<>();
        String filename = "Data/plants.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            boolean firstLine = true;
            while ((line = br.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue;
                }
                String[] row = line.split(",");
                if (row.length == 5) {
                    String plantTag = row[0];
                    String speciesId = row[1];
                    String roomId = row[2];
                    LocalDate lastWatered = LocalDate.parse(row[3]); // Convert from string
                    LocalDate nextWatering = LocalDate.parse(row[4]); // Convert from string

                    Species species = findSpeciesById(speciesId, speciesList); // Uses the id to return the full object
                    Room room = findRoomById(roomId, roomList); // Uses the id to return the full object
                    
                    if (species != null && room != null) {
                        plants.add(new Plant(plantTag, species, room, lastWatered, nextWatering));
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + filename);
        }

        return plants;
    }

    // Writes plant data to 'plants.csv' and overwrites existing data
    // Appending data to the list of all  plant  objects happens inside the program
    public static void writePlantsToFile(List<Plant> plantList) {
        String filename = "Data/plants.csv";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            bw.write("plantTag,speciesId,roomId,lastWateredDate,nextWateringDate");
            bw.newLine();

            for (Plant plant : plantList) {
                bw.write(String.join(",", 
                    plant.getPlantTag(),
                    plant.getSpecies().getSpeciesId(),
                    plant.getRoom().getRoomId(),
                    plant.getLastWateredDate().toString(),
                    plant.getNextWateringDate().toString()
                ));
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + filename);
        }
    }

    // Reads room data from 'rooms.csv' and converts it to a list of Room objects.
    public static List<Room> readRoomsFromFile() {
        List<Room> rooms = new ArrayList<>();
        String filename = "Data/rooms.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            boolean firstLine = true;
            while ((line = br.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue;
                }
                String[] row = line.split(",");
                if (row.length == 4) {
                    rooms.add(new Room(row[0], row[1], row[2], row[3]));
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + filename);
        }

        return rooms;
    }

    // Writes room data to 'rooms.csv' and overwrites existing data.
    public static void writeRoomsToFile(List<Room> rooms) {
        String filename = "Data/rooms.csv";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            bw.write("roomId,roomName,sunExposure,lightVolume");
            bw.newLine();

            for (Room room : rooms) {
                bw.write(String.join(",", room.getRoomId(), room.getName(), room.getSunExposure(), room.getLightVolume()));
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + filename);
        }
    }

    // Reads species data from 'species.csv' and converts it to a list of Species objects.
    public static List<Species> readSpeciesFromFile() {
        List<Species> speciesList = new ArrayList<>();
        String filename = "Data/species.csv";
    
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            boolean firstLine = true;
            while ((line = br.readLine()) != null) {
                if (firstLine) { 
                    firstLine = false;
                    continue;
                }
                String[] row = line.split(",");
                if (row.length == 4) {
                    speciesList.add(new Species(row[0], row[1], Integer.parseInt(row[2]), row[3]));
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + filename);
        }
        
        return speciesList;
    }
    
    // Writes species data to 'species.csv' and overwrites existing data.
    public static void writeSpeciesToFile(List<Species> speciesList) {
        String filename = "Data/species.csv";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            bw.write("speciesId,speciesName,wateringCadence,lightRequirements");
            bw.newLine();
    
            for (Species species : speciesList) {
                bw.write(String.join(",",
                    species.getSpeciesId(),
                    species.getSpeciesName(),
                    String.valueOf(species.getWateringCadence()),
                    species.getLightRequirements()
                ));
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + filename);
        }
    }
    
    // Helper methods for reading plants data from csv
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
}
