package logic.imagematching.features.featuresImage;

import org.opencv.core.Mat;
import org.opencv.core.MatOfDMatch;
import org.opencv.features2d.Features2d;
import org.opencv.highgui.Highgui;

/**
 * Created by quest on 7/4/16.
 */
public class FeaturesMatcher {
    String set = ".";
    public FeaturesMatcher(String constelations) {
        set = constelations;
        
    }

    public void printKeyPoints(ClusterSimplifiedMat small, MatchingObservedMat big) {
        Mat out = new Mat();
        Features2d.drawMatches(small.getImage(),
                small.getKeyPoints(),
                big.getImage(),
                big.getKeyPoints(),
                new MatOfDMatch(),
                out);
        Highgui.imwrite("./assets/"+set+"/simplifiedKeyPoints.png",out);
        out = new Mat();
       /* Features2d.drawMatches(small.getImage(), small.getDefaultKeyPoints(), big.getImage(), big.getDefaultKeyPoints(), new MatOfDMatch(), out);
        Highgui.imwrite("./assets/defaultKeyPoints.png",out);

        out = new Mat();
        Features2d.drawMatches(small.getImage(), big.getModelMatchingKeyPoints(), big.getImage(), big.getObslMatchingKeyPoints(), new MatOfDMatch(), out);
        Highgui.imwrite("./assets/matches.png",out);*/


    }
}
