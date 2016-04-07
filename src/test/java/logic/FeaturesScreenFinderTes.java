package logic;

import logic.features.FeaturesMatcher;
import logic.features.featuresImage.ClusterSimplifiedMat;
import logic.features.util.ClusterColider;
import org.junit.Test;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.SynchronousQueue;

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
        String set ="real";

        Mat smallBiNfi = Highgui.imread("./assets/"+set+"/small.png");
        Mat bigBiNfi = Highgui.imread("./assets/"+set+"/big.png");
        Mat smallBiNf = new Mat(); // = Highgui.imread("./assets/small.png");
        Mat bigBiNf = new Mat();// Highgui.imread("./assets/big.png");

        float ratio = 2.5f;

        Imgproc.resize(smallBiNfi,smallBiNf,new Size(smallBiNfi.size().width/ratio,smallBiNfi.size().height/ratio));
        Imgproc.resize(bigBiNfi,bigBiNf, new Size(bigBiNfi.size().width/ratio,bigBiNfi.size().height/ratio));


        float expectedPerOneTime = 0.1f;
        double testTimeInmilliseconds = 500;
        int singleIterations = (int)(13 * testTimeInmilliseconds/1000);

        //singleIterations = 1;

        beeingUsed = false;
        finished = 0;
        this.started = 0;


        ClusterSimplifiedMat big = new ClusterSimplifiedMat(bigBiNf);
        ClusterSimplifiedMat small = new ClusterSimplifiedMat(smallBiNf);

        big.setImage(bigBiNf);
        small.setImage(smallBiNf);

        ClusterColider.reserveColisions();

        double now = System.currentTimeMillis();
        for(int i=0; i< singleIterations ;i++) {
            big.setImage(bigBiNf);
            small.setImage(smallBiNf);
            small.setImage(smallBiNf);
            small.setImage(smallBiNf);
            small.setImage(smallBiNf);
            small.setImage(smallBiNf);
            small.setImage(smallBiNf);
        }
        System.out.println("->"+((System.currentTimeMillis()-now)/testTimeInmilliseconds));
        FeaturesMatcher fm = new FeaturesMatcher("CONSTELATIONS");
        fm.printKeyPoints(small,big);
    }


}
