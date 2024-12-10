package serwer.realFishing;

public class Fish {
    private final String species;
    private final double weight;
    private final boolean isAdult;

    public Fish(String species, double weight, boolean isAdult) {
        this.species = species;
        this.weight = weight;
        this.isAdult = isAdult;
    }

    public String getSpecies() {
        return species;
    }

    public double getWeight() {
        return weight;
    }

    public boolean isAdult() {
        return isAdult;
    }
}