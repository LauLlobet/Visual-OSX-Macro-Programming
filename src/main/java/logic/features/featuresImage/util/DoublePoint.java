package logic.features.featuresImage.util;

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

    public double getQuadDist() {
        double quadDist = x*x+y*y;
        return quadDist;
    }

    public String toString(){
        return "("+x+","+y+")";
    }

    public DoublePoint compare(DoublePoint doublePoint) {
        double x = Math.abs(Math.abs(doublePoint.getX()) - Math.abs(this.getX()));
        double y = Math.abs(Math.abs(doublePoint.getY()) - Math.abs(this.getY()));
        return new DoublePoint(x,y);
    }
}
