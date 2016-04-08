package logic.features;

import logic.features.featuresImage.ClusterSimplifiedMat;
import logic.features.featuresImage.MatchingObservedMat;
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

    public void printKeyPoints(ClusterSimplifiedMat small, MatchingObservedMat big) {
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

        out = new Mat();
        Features2d.drawMatches(small.getImage(), big.getModelMatchingKeyPoints(), big.getImage(), big.getObslMatchingKeyPoints(), new MatOfDMatch(), out);
        Highgui.imwrite("./assets/matches.png",out);


    }
}
