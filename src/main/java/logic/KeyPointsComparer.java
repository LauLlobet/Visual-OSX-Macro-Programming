package logic;

import net.sf.javaml.core.kdtree.KDTree;
import org.opencv.core.MatOfKeyPoint;
import org.opencv.features2d.DMatch;
import org.opencv.features2d.KeyPoint;

import java.awt.*;
import java.security.cert.PolicyNode;
import java.util.ArrayList;

/**
 * Created by quest on 6/4/16.
 */
public class KeyPointsComparer {
    private final int matchingPercent;
    private final FeatureSearchParams constalationParams;
    KDTree kdreeMod = new KDTree(2);
    KDTree kdreeObs = new KDTree(2);
    ArrayList<double[][]> matchingPoints;
    private MatOfKeyPoint observedKeyPoints;
    private MatOfKeyPoint modelKeyPoints;
    private MatOfKeyPoint matchingObservedKeyPoints;
    private MatOfKeyPoint matchingModelKeyPoints;

    public KeyPointsComparer(FeatureSearchParams constelationParams){
        this.constalationParams = constelationParams;
        this.matchingPercent = constelationParams.matchingPercent;
    }

    public Point findOffsetBetweenImages(MatOfKeyPoint modelKeyPoints, MatOfKeyPoint observedKeyPoints, MatOfKeyPoint matchingModelKeyPoints, MatOfKeyPoint matchingObservedKeyPoints){
        this.matchingModelKeyPoints = matchingModelKeyPoints;
        this.matchingObservedKeyPoints = matchingObservedKeyPoints;
        this.modelKeyPoints = modelKeyPoints;
        this.observedKeyPoints = observedKeyPoints;
        createKDTree(kdreeMod,modelKeyPoints);
        createKDTree(kdreeObs,observedKeyPoints);
        findMatchingConstelationsPoints();
        return null;
    }

    public double[] createPoint(double x, double y){
        double[] pos = new double[2];
        pos[0]=x;
        pos[1]=y;
        return pos;
    }

    private void findMatchingConstelationsPoints() {
        ArrayList<KeyPoint> modelKPMatched = new ArrayList<KeyPoint>();
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
        matchingObservedKeyPoints.fromList(observerdKPMatched);
    }

    private void createKDTree(KDTree tree, MatOfKeyPoint kp) {
        double[] point = new double[2];
        KeyPoint[] keypoints = kp.toArray();
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
}
