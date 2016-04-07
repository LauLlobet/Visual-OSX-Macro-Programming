package logic;

import logic.features.FeaturesMatcher;
import logic.features.featuresImage.ClusterSimplifiedMat;
import org.junit.Test;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by quest on 5/4/16.
 */
public class FeaturesScreenFinderTes {

    private int started;
    private int finished;

    public Mat loadFromFile(String path){
        return Highgui.imread(path);
    }


    static{ System.loadLibrary(Core.NATIVE_LIBRARY_NAME); }
    @Test
    public void testSingleScreen() throws Exception{
        Mat smallBiNf = Highgui.imread("./assets/small.png");
        Mat bigBiNf = Highgui.imread("./assets/big.png");

        final Mat bigBi = smallBiNf;
        int amountOfAssets = 7;

        float expectedPerOneTime = 0.1f;
        double testTimeInmilliseconds = 10000;
        int singleIterations =  (int)(25 * testTimeInmilliseconds/1000);

        finished = 0;
        this.started = 0;

        double now = System.currentTimeMillis();
        long millisPerCalc = (long)(expectedPerOneTime * testTimeInmilliseconds) / (singleIterations * amountOfAssets);
        for(int i=0; i< singleIterations * amountOfAssets;i++) {
            Thread.sleep(millisPerCalc);
            Thread one = new Thread() {
                public void run() {
                    final ClusterSimplifiedMat big = new ClusterSimplifiedMat(bigBi);
                    big.setImage(bigBi);
                    finished++;
                }
            };
            started++;
            one.start();
        }
        while (finished < this.started){
            //System.out.print( finished  +" " +this.started);
            int a;
        }
        /*
        for(int i=0; i< singleIterations*amountOfAssets;i++) {
            Thread one = new Thread() {
                public void run() {
                    try {
                        final ClusterSimplifiedMat small = new ClusterSimplifiedMat(smallBi,2.5f);
                        small.setImage(smallBi);
                    } catch(Exception v) {
                        System.out.println(v);
                    }
                }
            };
             one.start();
        }*/
        System.out.println("->"+((System.currentTimeMillis()-now)/testTimeInmilliseconds));
        FeaturesMatcher fm = new FeaturesMatcher("CONSTELATIONS");
        //fm.printKeyPoints(small,big);
    }


}
