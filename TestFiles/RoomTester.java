package TestFiles;

import ProjectFiles.Room;

public class RoomTester {
    public static void main(String[] args) {

        // Test Default Room Constructor
        Room myRoom = new Room("room_001", "Living Room", "South", "High");
        if (!(myRoom.getRoomId().equals("room_001") &&
              myRoom.getName().equals("Living Room") &&
              myRoom.getSunExposure().equals("South") &&
              myRoom.getLightVolume().equals("High"))) {
            System.out.println("Room Constructor Test Failed");
        } else {
            System.out.println("Room Constructor Test Passed!");
        }

        // Test Getters
        if (!myRoom.getRoomId().equals("room_001")) {
            System.out.println("getRoomId() Test Failed");
        } else {
            System.out.println("getRoomId() Test Passed");
        }

        if (!myRoom.getName().equals("Living Room")) {
            System.out.println("getName() Test Failed");
        } else {
            System.out.println("getName() Test Passed");
        }

        if (!myRoom.getSunExposure().equals("South")) {
            System.out.println("getSunExposure() Test Failed");
        } else {
            System.out.println("getSunExposure() Test Passed");
        }

        if (!myRoom.getLightVolume().equals("High")) {
            System.out.println("getLightVolume() Test Failed");
        } else {
            System.out.println("getLightVolume() Test Passed");
        }

        // Test toString() Method
        String expectedToString = "Living Room (Exposure: South, Light: High)";
        if (!myRoom.toString().equals(expectedToString)) {
            System.out.println("toString() Test Failed: Got \"" + myRoom.toString() + "\" instead of \"" + expectedToString + "\"");
        } else {
            System.out.println("toString() Test Passed");
        }

        System.out.println("Room Class Testing Complete!");
    }
}
