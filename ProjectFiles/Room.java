package ProjectFiles;

public class Room {
    protected String roomId;
    protected String roomName;
    protected String sunExposure;
    protected String lightVolume;

    public Room(String roomId, String roomName, String sunExposure, String lightVolume) {
        this.roomId = roomId;
        this.roomName = roomName;
        this.sunExposure = sunExposure;
        this.lightVolume = lightVolume;
    }
    public String getName() {
        return roomName;
    }
    public String getRoomId() {
        return roomId;
    }
    public String getSunExposure() {
        return sunExposure;
    }
    public String getLightVolume() {
        return lightVolume;
    }
    public String toString() {
        return roomName + " (Exposure: " + sunExposure + ", Light: " + lightVolume + ")";

    }
}
