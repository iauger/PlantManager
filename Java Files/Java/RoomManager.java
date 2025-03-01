package PlantManager.Java;

import java.util.List;
import java.util.Scanner;

public class RoomManager {

    private static Scanner scanner = new Scanner(System.in);

    // addRoom Method construction
    public static void addRoom(List<Room> roomList) {
        while (true) {
            System.out.println("Add a New Room");

            // Get Room Name
            String roomName;
            boolean isDuplicate;
            do {
                System.out.println("Enter room name:");
                roomName = scanner.nextLine().trim();
                
                isDuplicate = false;
                for (Room room : roomList) {
                    if (room.getName().equalsIgnoreCase(roomName)) {
                        System.out.println("Room already exists, enter a new name.");
                        isDuplicate = true;
                        break;
                    }
                }
            } while (isDuplicate);

            // Sun Exposure
            String sunExposure = "";
            while  (!sunExposure.matches("(?i)north|south|east|west")) {
                System.out.println("Enter sun exposure (North, South, East, West): ");
                sunExposure = scanner.nextLine().trim().toLowerCase();
                if (!sunExposure.matches("north|south|east|west")) {
                    System.out.println("Invalid input.  Please enter 'North', 'South', 'East' or 'West'.");
                    continue;
                }
            }

            // Light Volume
            String lightVolume = "";
            while  (!lightVolume.matches("(?i)low|medium|high")) {
                System.out.println("Enter light requirements (Low, Medium, High): ");
                lightVolume = scanner.nextLine().trim().toLowerCase();
                if (!lightVolume.matches("low|medium|high")) {
                    System.out.println("Invalid input.  Please enter 'Low', 'Medium', or 'High'.");
                    continue;
                }
            }

            // Generate Room ID
            String roomId = IDGenerator.generateRoomId(roomList);

            // Add new room as an object to roomList
            Room newRoom = new Room(roomId, roomName, sunExposure, lightVolume);
            roomList.add(newRoom);

            // Save updates to file
            FileHandler.writeRoomsToFile(roomList);

            System.out.println("\nSuccessfully added new room: " + newRoom.getName());

            System.out.println("Do you want to add another room?");
            System.out.println("1) Add another room");
            System.out.println("2) Return to Main Menu");

            try {
                int choice = Integer.parseInt(scanner.nextLine().trim());
                if (choice == 2) {
                    break;
                }
            }
            catch (NumberFormatException e) {
                System.out.println("Invalid choice. Please enter 1 or 2.");
            }
        }           
    }
}
