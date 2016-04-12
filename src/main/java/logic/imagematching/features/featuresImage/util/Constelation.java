package logic.imagematching.features.featuresImage.util;


import logic.imagematching.features.featuresImage.ClusterImage;
import org.opencv.features2d.KeyPoint;

public class  Constelation {
    double[][] relativeConstelationPoints;
    int insertedIndexNearestPoints = 0;
    double[] pos;
    PointsEqualator equalator;
    public boolean debug;

    public Constelation(double[] inpos,int size){
            pos = inpos;
            relativeConstelationPoints = new double[size][2];
    }
    public double[] createPoint(double x, double y){
        double[] pos = new double[2];
        pos[0]=x;
        pos[1]=y;
        return pos;
    }

    public Constelation(double[] obsPoint, Object[] modpNeightbours) {
        this(obsPoint,modpNeightbours.length);
        for(Object pointo: modpNeightbours){
            KeyPoint point = (KeyPoint) pointo;
            addOwnPoint(createPoint(point.pt.x,point.pt.y));
        }
    }

    public void addOwnPoint(double[] point){
        if(insertedIndexNearestPoints >= relativeConstelationPoints.length){
            System.out.println("adding to much stars");
            return;
        }
        double dx = point[0]-pos[0];
        double dy = point[1]-pos[1];
        if(dx == 0 && dy == 0){
            return;
        }
        relativeConstelationPoints[insertedIndexNearestPoints][0] = dx;
        relativeConstelationPoints[insertedIndexNearestPoints][1] = dy;
        insertedIndexNearestPoints++;
    }

    public void print(String s){
        if(debug){
            System.out.println("["+this.getClass().getName()+"]"+s);
        }
    }

    public float doesItMatch(KeyPoint observedPointPosibleCenterOfModelConstelation, ClusterImage simplifiedKeyPointsPrecisionIsClusterWidth, double[] color) {
        float similarity = 0;
        int inArray = 0;
        for (double[] relativePoint: relativeConstelationPoints){
            double[] suposedPoint = createSuposedPointAcordingToConstelationWithCenterAtPosibleCenter(relativePoint,observedPointPosibleCenterOfModelConstelation);
            similarity = similarity + simplifiedKeyPointsPrecisionIsClusterWidth.doColides(suposedPoint,color);
        }
        return similarity/relativeConstelationPoints.length;
    }

    private boolean isIn(double[] colided, double[][] matched) {
        for(double[] matchp:matched){
            if(matchp[0] == colided[0] && matchp[1] == colided[1]){
                return true;
            }
        }
        return false;
    }

    private double[] midlePoint(double[] match, double[] match2) {
        double[] newpoint = new double[2];
        newpoint[0] = (match[0]+match2[0])/2;
        newpoint[1] = (match[1]+match2[1])/2;
        return newpoint;
    }

    private double[] createSuposedPointAcordingToConstelationWithCenterAtPosibleCenter(double[] relativePoint,KeyPoint observedPointPosibleCenterOfModelConstelation) {
        double[] ans = new double[2];
        ans[0] = observedPointPosibleCenterOfModelConstelation.pt.x + relativePoint[0];
        ans[1] = observedPointPosibleCenterOfModelConstelation.pt.y + relativePoint[1];
        return ans;
    }
}