package PlantManager.Java;

import java.time.LocalDate;

public class Plant {
    private String plantTag;
    private Species species; // ✅ Store full Species object
    private Room room;       // ✅ Store full Room object
    private LocalDate lastWateredDate;
    private LocalDate nextWateringDate;
    private boolean isActive;

    public Plant(String plantTag, Species species, Room room, LocalDate lastWateredDate, LocalDate nextWateringDate) {
        this.plantTag = plantTag;
        this.species = species;
        this.room = room;
        this.lastWateredDate = lastWateredDate;
        this.nextWateringDate = nextWateringDate;
        this.isActive = true;
    }

    // Getters
    public String getPlantTag() {
        return plantTag;
    }
    public Species getSpecies() {
        return species;
    }
    public Room getRoom() {
        return room;
    }
    public String getSpeciesId() {
        return species.getSpeciesId();
    }
    public String  getSpeciesName() {
        return species.getSpeciesName();
    }
    public String getRoomId() {
        return room.getRoomId();
    }
    public String getRoomName() {
        return room.getName();
    }
    public String getRoomLightVolume() {
        return room.getLightVolume();
    }
    public String getSpeciesLightRequirements() {
        return species.getLightRequirements();
    }
    public LocalDate getLastWateredDate() {
        return lastWateredDate;
    }
    public LocalDate getNextWateringDate() {
        return nextWateringDate;
    }
    public boolean isActive() {
        return isActive;
    }

    // Setters
    public void setLastWateredDate(LocalDate date) {
        this.lastWateredDate =  date;
    }
    public void setNextWateringDate(LocalDate date) {
        this.nextWateringDate = date;
    }
    public void setRoom(Room newRoom) {
        this.room = newRoom;
    }
    public void  setActive(boolean active) {
        this.isActive = active;
    }
    
    public String toString() {
        return plantTag + ") Species-" + species.getSpeciesName() + ", Room-" + room.getName() + ", Next Watering Date-" + nextWateringDate;
    }
}
