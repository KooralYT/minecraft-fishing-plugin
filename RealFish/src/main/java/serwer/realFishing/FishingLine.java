package serwer.realFishing;

public class FishingLine {
    private final double thickness;
    private final double length;

    public FishingLine(double thickness, double length) {
        this.thickness = thickness;
        this.length = length;
    }

    public double getThickness() {
        return thickness;
    }

    public double getLength() {
        return length;
    }
}