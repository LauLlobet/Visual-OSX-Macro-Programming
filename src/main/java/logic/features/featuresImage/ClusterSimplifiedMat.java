package logic.features.featuresImage;

import logic.features.ClusterImage;
import org.opencv.core.MatOfKeyPoint;
import org.opencv.features2d.KeyPoint;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by quest on 7/4/16.
 */
public class ClusterSimplifiedMat extends FeaturesMat {

    ClusterImage clusterKeyPointsSimplifier;

    int keyPointContactClusteringArea = 3;
    private MatOfKeyPoint defaultKeyPoints;

    public ClusterSimplifiedMat(BufferedImage img, float ratio) {
        super(img, ratio);
        clusterKeyPointsSimplifier = new ClusterImage(img.getWidth(),img.getHeight());
    }

    @Override
    public void setImage(BufferedImage img){
        super.setImage(img);
        super.extractKeyPoints();
        defaultKeyPoints = keyPoints;
        filterKeypoints();
    }

    private void filterKeypoints(){
        ArrayList<KeyPoint> ans = clusterKeyPointsSimplifier.groupKeyPointsInSingleOnes(keyPoints.toArray(),
                keyPointContactClusteringArea);
        keyPoints = new MatOfKeyPoint();
        keyPoints.fromList(ans);
    }

    public int getKeyPointContactClusteringArea() {
        return keyPointContactClusteringArea;
    }

    public void setKeyPointContactClusteringArea(int keyPointContactClusteringArea) {
        this.keyPointContactClusteringArea = keyPointContactClusteringArea;
    }

    public MatOfKeyPoint getDefaultKeyPoints() {
        return defaultKeyPoints;
    }
}
