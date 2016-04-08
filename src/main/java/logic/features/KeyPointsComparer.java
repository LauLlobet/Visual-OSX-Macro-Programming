package logic.features;

import logic.features.featuresImage.util.FeatureSearchParams;
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
    KDTree kdreeMod = new KDTree(2);
    KDTree kdreeObs = new KDTree(2);
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
        ArrayList<KeyPoint> ans = ci.groupKeyPointsInSingleOnes(mod,(int)constalationParams.clusterContactDistance);
        matchingModelKeyPoints.fromList(ans);
        return null;
    }




}
