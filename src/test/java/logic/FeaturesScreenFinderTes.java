package logic;

import logic.features.FeaturesMatcher;
import logic.features.featuresImage.ClusterSimplifiedMat;
import logic.features.featuresImage.MatchingObservedMat;
import logic.features.featuresImage.util.ClusterColider;
import logic.features.featuresImage.util.DoublePoint;
import logic.features.featuresImage.util.FeatureSearchParams;
import logic.features.featuresImage.util.PointsEqualator;
import org.junit.Test;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;

/**
 * Created by quest on 5/4/16.
 */
public class FeaturesScreenFinderTes {

    private int started;
    private int finished;
    private boolean beeingUsed;

    public Mat loadFromFile(String path){
        return Highgui.imread(path);
    }


    static{ System.loadLibrary(Core.NATIVE_LIBRARY_NAME); }
    @Test
    public void testSingleScreen() throws Exception{
        String set ="realsimple";

        Mat smallBiNfi = Highgui.imread("./assets/"+set+"/small.png");
        Mat bigBiNfi = Highgui.imread("./assets/"+set+"/big.png");
        Mat smallBiNf = new Mat(); // = Highgui.imread("./assets/small.png");
        Mat bigBiNf = new Mat();// Highgui.imread("./assets/big.png");

        float ratio = 2.5f;

        Imgproc.resize(smallBiNfi,smallBiNf,new Size(smallBiNfi.size().width/ratio,smallBiNfi.size().height/ratio));
        Imgproc.resize(bigBiNfi,bigBiNf, new Size(bigBiNfi.size().width/ratio,bigBiNfi.size().height/ratio));


        float expectedPerOneTime = 0.1f;
        double testTimeInmilliseconds = 5000;
        int singleIterations = (int)(13 * testTimeInmilliseconds/1000);

        singleIterations = 1;

        beeingUsed = false;
        finished = 0;
        this.started = 0;

        FeatureSearchParams params = new FeatureSearchParams();
        params.matchingPercent = 59;
        params.nstars = 5;
        params.clusterContactDistance = 4;
        params.equalator = new PointsEqualator(2);

        MatchingObservedMat big = new MatchingObservedMat(bigBiNf,params);
        ClusterSimplifiedMat small = new ClusterSimplifiedMat(smallBiNf);

        big.setImage(bigBiNf);
        small.setImage(smallBiNf);

        ClusterColider.reserveColisions();

        double now = System.currentTimeMillis();
        for(int i=0; i< singleIterations ;i++) {
            big.setImage(bigBiNf);
            small.setImage(smallBiNf);
            DoublePoint a = big.findOffset(small);
            System.out.println("offset:"+a.toString());
           /* small.setImage(smallBiNf);
            big.findOffset(small);
            small.setImage(smallBiNf);
            big.findOffset(small);
            small.setImage(smallBiNf);
            big.findOffset(small);
            small.setImage(smallBiNf);
            big.findOffset(small);
            small.setImage(smallBiNf);
            big.findOffset(small);*/
        }
        System.out.println("->"+((System.currentTimeMillis()-now)/testTimeInmilliseconds));
        FeaturesMatcher fm = new FeaturesMatcher("CONSTELATIONS");
        fm.printKeyPoints(small,big);
        //big.printKeyPoints();
        big.printMatches();
    }


}
