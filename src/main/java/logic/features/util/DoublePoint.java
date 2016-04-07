package logic.features.util;

/**
 * Created by quest on 7/4/16.
 */
public class DoublePoint {
    public double getX() {
        return x;
    }

    private final double x;

    public double getY() {
        return y;
    }

    private final double y;

    public DoublePoint(double x, double y){
        this.x = x;
        this.y = y;
    }
}
