package logic.imagematching.features.featuresImage.util;

/**
 * Created by quest on 7/4/16.
 */
public class DoublePoint {
    public double getX() {
        return x;
    }

    private double x;

    public double getY() {
        return y;
    }

    private double y;

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

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

}
