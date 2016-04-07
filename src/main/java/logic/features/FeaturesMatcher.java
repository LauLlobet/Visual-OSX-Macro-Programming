package logic.features;

import logic.features.featuresImage.ClusterSimplifiedMat;
import org.opencv.core.Mat;
import org.opencv.core.MatOfDMatch;
import org.opencv.features2d.Features2d;
import org.opencv.highgui.Highgui;

/**
 * Created by quest on 7/4/16.
 */
public class FeaturesMatcher {
    public FeaturesMatcher(String constelations) {
        
    }

    public void printKeyPoints(ClusterSimplifiedMat small, ClusterSimplifiedMat big) {
        Mat out = new Mat();
        Features2d.drawMatches(small.getImage(),
                small.getKeyPoints(),
                big.getImage(),
                big.getKeyPoints(),
                new MatOfDMatch(),
                out);
        Highgui.imwrite("./assets/simplifiedKeyPoints.png",out);
        out = new Mat();
        Features2d.drawMatches(small.getImage(), small.getDefaultKeyPoints(), big.getImage(), big.getDefaultKeyPoints(), new MatOfDMatch(), out);
        Highgui.imwrite("./assets/defaultKeyPoints.png",out);

    }
}
