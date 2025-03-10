package TestFiles;

import ProjectFiles.Room;
import ProjectFiles.RoomManager;

import java.util.ArrayList;
import java.util.List;

public class RoomManagerTester {

    public static void main(String[] args) {
        
        // At present I cannot figure out how to whitebox test the add() method since by design it takes User input to construct the new object
        // Without altering the structure of the method to be able to pass an object as an argument, I cannot write a test for the method

        // Initialize mock roomList and add an object
        List<Room> roomList = new ArrayList<>();
        RoomManager roomManager = new RoomManager();
        roomList.add(new Room("room_001", "Living Room", "South", "High"));

        // Test Display Method
        System.out.println("\nTesting display() method:");
        roomManager.display(roomList);  // Should print all rooms

        System.out.println("\nRoomManager Class Testing Complete!");
    }
    
}
