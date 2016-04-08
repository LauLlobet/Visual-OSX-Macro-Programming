package logic.features.featuresImage;

import logic.features.featuresImage.util.Constelation;
import logic.features.featuresImage.util.DoublePoint;
import logic.features.featuresImage.util.FeatureSearchParams;
import net.sf.javaml.core.kdtree.KDTree;
import net.sf.javaml.core.kdtree.KeyDuplicateException;
import net.sf.javaml.core.kdtree.KeySizeException;
import org.opencv.core.Mat;
import org.opencv.core.MatOfKeyPoint;
import org.opencv.features2d.KeyPoint;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * Created by quest on 7/4/16.
 */


public class MatchingObservedMat extends ClusterSimplifiedMat {

    FeatureSearchParams params;
    private net.sf.javaml.core.kdtree.KDTree kdreeObs;
    private net.sf.javaml.core.kdtree.KDTree kdreeMod;

    private Hashtable<KeyPoint,Constelation> ownConstelations;

    KeyPoint[] modelKeyPoints;
    KeyPoint[] observedKeyPoints;

    ArrayList<KeyPoint> modelKPMatched = new ArrayList<KeyPoint>();
    ArrayList<KeyPoint> observerdKPMatched = new ArrayList<KeyPoint>();

    public Constelation getOwnConstelation(KeyPoint point){
        try{
            return ownConstelations.get(point);
        }catch (Exception e){
            double[] modePoint = createPoint(point.pt.x,point.pt.y);
            Object[] modpNeightbours = new Object[0];
            try {
                modpNeightbours = kdreeMod.nearest(modePoint,params.nstars);
            } catch (KeySizeException e1) {
                e1.printStackTrace();
            }
            Constelation modeConst = new Constelation(modePoint,modpNeightbours,params);
            ownConstelations.put(point,modeConst);
            return modeConst;
        }
    }

    public MatchingObservedMat(Mat img, FeatureSearchParams params) {
        super(img);
        ownConstelations = new Hashtable<KeyPoint, Constelation>();
        this.params = params;
    }
    @Override
    public void setImage(Mat img){
        super.setImage(img);
        kdreeObs = new KDTree(2);
        observedKeyPoints = this.getKeyPoints().toArray();
        createKDTree(kdreeObs,observedKeyPoints);
    }

    public DoublePoint findOffset(ClusterSimplifiedMat otherImage){
        kdreeMod = new KDTree(2);
        modelKeyPoints = otherImage.getKeyPoints().toArray();
        createKDTree(kdreeMod,modelKeyPoints);
        findMatchingConstelationsPoints();
        return findOffsetInMatches();
    }

    private DoublePoint findOffsetInMatches() {
        DoublePoint[] deltas = new DoublePoint[modelKPMatched.size()];
        double avgDelta = 0;
        int i = 0;
        if(modelKPMatched.size() == 0){
            return null;
        }
        KDTree moduls = new KDTree(1);
        for (KeyPoint m:modelKPMatched){
            KeyPoint o = observerdKPMatched.get(i);
            DoublePoint delta = new DoublePoint(m.pt.x-o.pt.x,m.pt.y-o.pt.y);
            deltas[i] = delta;
            double[] deltad =  new double[1];
            double deltamod = delta.getQuadDist();
            deltad[0] = deltamod;
            try {
                moduls.insert(deltad,delta);
            } catch (Exception e) {

            }
            System.out.println("Delta:"+Math.sqrt(deltamod));
            avgDelta = ((avgDelta*(double)i)+deltamod)/(i+1);
            i++;
        }
        double[] avgdeltaA =  new double[1];
        avgdeltaA[0] = avgDelta;
        try {
            Object[] array = (Object[])moduls.nearest(avgdeltaA,1);
            DoublePoint ans = (DoublePoint) array[0];
            return ans;
        } catch (KeySizeException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void findMatchingConstelationsPoints() {


        for(KeyPoint obsp : observedKeyPoints){
            try{
                double[] obsPoint = createPoint(obsp.pt.x, obsp.pt.y);
                Object[] obsNeightbours = kdreeObs.nearest(obsPoint,params.nstars);
                Constelation obsConst = new Constelation(obsPoint,obsNeightbours,params);

                for (KeyPoint modp:modelKeyPoints){

                    double[] modePoint = createPoint(modp.pt.x,modp.pt.y);
                    Object[] modpNeightbours = kdreeMod.nearest(modePoint,params.nstars);
                    Constelation modeConst = new Constelation(modePoint,modpNeightbours,params);

                    float matching = modeConst.doesItMatch(obsConst);

                    if(matching > ((float)params.matchingPercent/100)){
                        modelKPMatched.add(modp);
                        observerdKPMatched.add(obsp);
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }

    public void printMatches(){

        int i =0;
        for(KeyPoint k: modelKPMatched){
            printKeyPoint(k);
            System.out.println("matchesV");
            printKeyPoint(observerdKPMatched.get(i));
            System.out.println("------");
            i++;
        }
        printKeyPoints(modelKeyPoints);
        System.out.println("ObservedKeyPoints:"+observedKeyPoints.length);
        printKeyPoints(observedKeyPoints);

    }

    public void printKeyPoints(){
        System.out.println("ModelKeyPoints:"+modelKeyPoints.length);
        printKeyPoints(modelKeyPoints);
        System.out.println("ObservedKeyPoints:"+observedKeyPoints.length);
        printKeyPoints(observedKeyPoints);

    }
    public void printCaracteristics(){
        System.out.println("ModelKeyPoints:"+modelKeyPoints.length);
        System.out.println("ObservedKeyPoints:"+observedKeyPoints.length);
        System.out.println("NumMatches:"+modelKPMatched.size());
        System.out.println("ObserverdKPMatched:"+observerdKPMatched.size());
    }
    private void printKeyPoints(KeyPoint[] modelKeyPoints) {
        for(KeyPoint k : modelKeyPoints){
            printKeyPoint(k);
        }
    }
    public void printKeyPoint(KeyPoint k){
        System.out.println("const1.addOwnPoint(createPoint("+k.pt.x+","+k.pt.y+"));");
    }

    private void createKDTree(KDTree tree, KeyPoint[] keypoints) {
        double[] point = new double[2];
        for(KeyPoint k : keypoints){
                point[0] = k.pt.x;
                point[1] = k.pt.y;
            try{
                tree.insert(point,k);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public double[] createPoint(double x, double y){
        double[] pos = new double[2];
        pos[0]=x;
        pos[1]=y;
        return pos;
    }

    public double[] createPoint(ArrayList<Double> a){
        double[] pos = new double[2];
        pos[0]=a.get(0);
        pos[1]=a.get(1);
        return pos;
    }


    public MatOfKeyPoint getModelMatchingKeyPoints() {
        MatOfKeyPoint ans = new MatOfKeyPoint();
        ans.fromList(modelKPMatched);
        return ans;
    }

    public MatOfKeyPoint getObslMatchingKeyPoints() {
        MatOfKeyPoint ans = new MatOfKeyPoint();
        ans.fromList(observerdKPMatched);
        return ans;
    }
}
