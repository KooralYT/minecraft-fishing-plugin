package serwer.realFishing;

public class Bait {
    private final String type;
    private final double catchChance;

    public Bait(String type, double catchChance) {
        this.type = type;
        this.catchChance = catchChance;
    }

    public String getType() {
        return type;
    }

    public double getCatchChance() {
        return catchChance;
    }
}