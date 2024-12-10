package serwer.realFishing;

public class Reel {
    private final double gearRatio;
    private final double maxLineLength;
    private final double brakeStrength;

    public Reel(double gearRatio, double maxLineLength, double brakeStrength) {
        this.gearRatio = gearRatio;
        this.maxLineLength = maxLineLength;
        this.brakeStrength = brakeStrength;
    }

    public double getGearRatio() {
        return gearRatio;
    }

    public double getMaxLineLength() {
        return maxLineLength;
    }

    public double getBrakeStrength() {
        return brakeStrength;
    }
}