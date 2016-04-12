package logic;

import logic.imagematching.features.featuresImage.FeaturesMatcher;
import logic.imagematching.features.featuresImage.ClusterSimplifiedMat;
import logic.imagematching.features.featuresImage.MatchingObservedMat;
import logic.imagematching.features.featuresImage.util.ClusterColider;
import logic.imagematching.features.featuresImage.util.DoublePoint;
import logic.imagematching.features.featuresImage.util.FeatureSearchParams;
import org.junit.Test;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;

import java.util.Hashtable;

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


        Hashtable<String,DoublePoint> results = new Hashtable<String,DoublePoint>();


        results.put("1",new DoublePoint(0,0));
        results.put("2",new DoublePoint(320,152));
        results.put("3",new DoublePoint(247,98));
        results.put("4",new DoublePoint(0,0));
        results.put("5",new DoublePoint(0,0));

        for(int seti=1; seti<=5; seti++){

            String set =""+seti;

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

            FeatureSearchParams params = new FeatureSearchParams();
            params.matchingMatPrecision = 4;
            params.simplifiyingPrecision = 2;

            MatchingObservedMat big = new MatchingObservedMat(bigBiNf,params);
            ClusterSimplifiedMat small = new ClusterSimplifiedMat(smallBiNf,params);

            big.setImage(bigBiNf);
            small.setImage(smallBiNf);

            ClusterColider.reserveColisions();

            double now = System.currentTimeMillis();
            for(int i=0; i< singleIterations ;i++) {
                big.setImage(bigBiNf);
                small.setImage(smallBiNf);
                DoublePoint a = big.findOffset(small);
               // System.out.println("->"+a.toString());
                System.out.println("Set:"+set+" "+a.compare(results.get(set)));
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
           // System.out.println("->"+((System.currentTimeMillis()-now)/testTimeInmilliseconds));
            FeaturesMatcher fm = new FeaturesMatcher(set);
            fm.printKeyPoints(small,big);
           // big.printKeyPoints();
            //big.printMatches();
        }

    }


}
