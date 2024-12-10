package serwer.realFishing;

public class FishingRod {
    private final double length;
    private final double maxCastWeight;

    public FishingRod(double length, double maxCastWeight) {
        this.length = length;
        this.maxCastWeight = maxCastWeight;
    }

    public double getLength() {
        return length;
    }

    public double getMaxCastWeight() {
        return maxCastWeight;
    }
}