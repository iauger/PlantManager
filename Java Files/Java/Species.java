public class Species {
    private String speciesId;
    private String speciesName;
    private int wateringCadence;
    private String lightRequirements;

    public Species(String speciesId, String speciesName, int wateringCadence, String lightRequirements) {
        this.speciesId = speciesId;
        this.speciesName = speciesName;
        this.wateringCadence = wateringCadence;
        this.lightRequirements = lightRequirements;
        }

        public String getSpeciesId() {
            return speciesId;
        }
        public String getSpeciesName() {
            return speciesName;
        }
        public int getWateringCadence() {
            return wateringCadence;
        }
        public void setWateringCadence(int wateringCadence) {
            this.wateringCadence = wateringCadence;
        } 
        public String getLightRequirements() {
            return lightRequirements;
        }
        public void setLightRequirements(String lightRequirements) {
            this.lightRequirements = lightRequirements;
        }
        public void updateSpecies(String newName, int newWateringCadence, String newLightRequirements) {
            this.speciesName = newName;
            this.wateringCadence = newWateringCadence;
            this.lightRequirements = newLightRequirements;
        }
        public String toString() {
            return speciesName + " (Water every " + wateringCadence + " days, Light: " + lightRequirements + ")";
        }
    
}
