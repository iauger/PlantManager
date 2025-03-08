import java.util.List;

public class IDGenerator {
    
    // Generate next available Species ID
    public static String generateSpeciesId(List<Species> speciesList) {
        int maxId = 0;

        // Iterates through a list of species to find the highest ID value and incrememnts by 1 for new ID
        for (Species species : speciesList) {
            String id =  species.getSpeciesId().replace("species_", ""); // Extract numeric part
            try {
                int num = Integer.parseInt(id);
                if (num > maxId) {
                    maxId = num;
                }
            } catch (NumberFormatException e) {
                System.out.println("Error parsing species ID: " + id);
            }
        }
        // Format count as three-digit number with leading zeroes
        String formattedNumber = String.format("%03d", maxId + 1);

        return "species_" + formattedNumber;
    }

    // Generate next available Room ID
    public static String generateRoomId(List<Room> roomList) {
        int maxId = 0;

        for (Room room : roomList) {
            String id = room.getRoomId().replace("room_", ""); // Extract numeric part
            try {
                int num = Integer.parseInt(id);
                if (num > maxId) {
                    maxId = num;
                }
            } catch (NumberFormatException e) {
                System.out.println("Error parsing room ID: " + id);
            }
        }
        // Format count as three-digit number with leading zeroes
        String formattedNumber = String.format("%03d", maxId + 1);

        return "room_" + formattedNumber;
    }

    public static String generatePlantId(String speciesName, List<Plant> plantList) {
        // Convert species name to lowercase snake_case for consistency
        String speciesPrefix = speciesName.toLowerCase().replace(" ", "_");
    
        // Initialize counter
        int count = 0;
    
        // Loop through plant data to count existing plants of the same species
        for (Plant plant : plantList) {
            if (plant.getPlantTag().startsWith(speciesPrefix)) { // Check if plant ID starts with species name
                count++;
            }
        }
    
        // Format count as three-digit number with leading zeroes
        String formattedNumber = String.format("%03d", count + 1);
    
        // Return the final plant ID
        return speciesPrefix + "_" + formattedNumber;
    }
    
}

