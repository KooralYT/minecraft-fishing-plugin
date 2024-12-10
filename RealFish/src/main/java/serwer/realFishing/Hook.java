package serwer.realFishing;

public class Hook {
    private final String type;
    private final double strength;

    public Hook(String type, double strength) {
        this.type = type;
        this.strength = strength;
    }

    public String getType() {
        return type;
    }

    public double getStrength() {
        return strength;
    }
}