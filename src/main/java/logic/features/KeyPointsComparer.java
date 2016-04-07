package logic.features;

import logic.features.util.FeatureSearchParams;
import net.sf.javaml.core.kdtree.KDTree;
import org.opencv.core.Mat;
import org.opencv.core.MatOfKeyPoint;
import org.opencv.features2d.KeyPoint;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by quest on 6/4/16.
 */
public class KeyPointsComparer {
    private final int matchingPercent;
    private final FeatureSearchParams constalationParams;
    KDTree kdreeModX = new KDTree(1);
    KDTree kdreeObsX = new KDTree(1);
    KDTree kdreeModY = new KDTree(1);
    KDTree kdreeObsY = new KDTree(1);
    ArrayList<double[][]> matchingPoints;
    private MatOfKeyPoint observedKeyPoints;
    private MatOfKeyPoint modelKeyPoints;



    public KeyPointsComparer(FeatureSearchParams constelationParams){
        this.constalationParams = constelationParams;
        this.matchingPercent = constelationParams.matchingPercent;
    }

    public Point findOffsetBetweenImages(Mat w, Mat h, MatOfKeyPoint modelKeyPoints, MatOfKeyPoint observedKeyPoints, MatOfKeyPoint matchingModelKeyPoints, MatOfKeyPoint matchingObservedKeyPoints){

        ClusterImage ci = new ClusterImage(w.width(),h.height());
        KeyPoint[] mod = modelKeyPoints.toArray();
        int modl = mod.length;
        ArrayList<KeyPoint> ans = ci.groupKeyPointsInSingleOnes(mod,(int)constalationParams.precision);
        matchingModelKeyPoints.fromList(ans);
        return null;
    }

    public double[] createPoint(double x, double y){
        double[] pos = new double[2];
        pos[0]=x;
        pos[1]=y;
        return pos;
    }

    private void findMatchingConstelationsPoints() {
      /*  ArrayList<KeyPoint> modelKPMatched = new ArrayList<KeyPoint>();
        ArrayList<KeyPoint> observerdKPMatched = new ArrayList<KeyPoint>();
        for(KeyPoint obsp:observedKeyPoints.toList()){
            try{
                double[] obsPoint = createPoint(obsp.pt.x, obsp.pt.y);
                Object[] obspNeightbours = (Object[])kdreeObs.nearest(obsPoint, constalationParams.nstars);
                Constelation obsConst = new Constelation(obsPoint,obspNeightbours,constalationParams);
                for (KeyPoint modp:modelKeyPoints.toList()){

                    double[] modePoint = createPoint(modp.pt.x,modp.pt.y);
                    Object[] modpNeightbours = kdreeMod.nearest(modePoint,constalationParams.nstars);
                    Constelation modeConst = new Constelation(modePoint,modpNeightbours,constalationParams);

                    float matching = modeConst.doesItMatch(obsConst);

                    if(matching > ((float)matchingPercent/100)){
                        modelKPMatched.add(modp);
                        observerdKPMatched.add(obsp);
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        System.out.println("ModelKeyPoints:"+modelKeyPoints.size());
        System.out.println("NumMatches:"+modelKPMatched.size());
        System.out.println("ObserverdKPMatched:"+observerdKPMatched.size());
        matchingModelKeyPoints.fromList(modelKPMatched);
        matchingObservedKeyPoints.fromList(observerdKPMatched);*/
    }

    private void createKDTree(KDTree tree, MatOfKeyPoint kp, int x0y1) {
        double[] point = new double[1];
        KeyPoint[] keypoints = kp.toArray();
        for(KeyPoint k : keypoints){
            if(x0y1 == 0){
                point[0] = k.pt.x;
            }else{
                point[0] = k.pt.y;
            }
            try{
                tree.insert(point,k);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
