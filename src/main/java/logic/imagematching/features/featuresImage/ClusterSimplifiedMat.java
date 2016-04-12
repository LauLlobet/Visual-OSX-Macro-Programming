package logic.imagematching.features.featuresImage;

import logic.imagematching.features.featuresImage.util.ClusterColider;
import logic.imagematching.features.featuresImage.util.FeatureSearchParams;
import org.opencv.core.Mat;
import org.opencv.core.MatOfKeyPoint;
import org.opencv.features2d.KeyPoint;

import java.util.ArrayList;

/**
 * Created by quest on 7/4/16.
 */
public class ClusterSimplifiedMat extends FeaturesMat {

    protected final FeatureSearchParams params;
    ClusterImage clusterKeyPointsSimplifier;

    private MatOfKeyPoint defaultKeyPoints;

    public ClusterSimplifiedMat(Mat img, FeatureSearchParams params) {
        clusterKeyPointsSimplifier = new ClusterImage(img.width(),img.height());
        this.params = params;
    }

    @Override
    public void setImage(Mat img){
        super.setImage(img);
        ClusterColider.reserveColisions();
        super.extractKeyPoints();
        defaultKeyPoints = keyPoints;
        filterKeypoints();
    }

    private void filterKeypoints(){
        ArrayList<KeyPoint> ans = clusterKeyPointsSimplifier.groupKeyPointsInSingleOnes(keyPoints.toArray(),
                (int)params.simplifiyingPrecision,targetImg);
        keyPoints = new MatOfKeyPoint();
        keyPoints.fromList(ans);
    }

    public MatOfKeyPoint getDefaultKeyPoints() {
        return defaultKeyPoints;
    }
}
