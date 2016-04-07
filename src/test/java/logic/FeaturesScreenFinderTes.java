package logic;

import logic.features.FeaturesMatcher;
import logic.features.featuresImage.ClusterSimplifiedMat;
import org.junit.Test;
import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by quest on 5/4/16.
 */
public class FeaturesScreenFinderTes {

    /*@Test
    public void testSingleScreen(){
        ImageFeaturesFinder iff = new ImageFeaturesFinder();
        iff.loadBigFromFile("./asssets/big.png",3);
        iff.loadSmallFromFile("./asssets/small.png",3);
        int num = 1;
        double now = System.currentTimeMillis();
        for(int i=0; i< num;i++) {
            Point xy = iff.getFeatureXYPositionEach2Sec();
        }
        System.out.println("->"+(1000/(System.currentTimeMillis()-now)));
        iff.print();
    }*/

    public BufferedImage loadFromFile(String path){
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(path));
        } catch (IOException e) {

        }
        return img;
    }


    @Test
    public void testSingleScreen(){
        final BufferedImage smallBi = loadFromFile("assets/small.png");
        final BufferedImage bigBi = loadFromFile("assets/big.png");

        int amountOfAssets = 7;

        float expectedPerOneTime = 0.99f;
        double testTimeInmilliseconds = 10000;
        int singleIterations =  (int)(25 * testTimeInmilliseconds/1000);

        double now = System.currentTimeMillis();
        long millisPerCalc = (long)(expectedPerOneTime * testTimeInmilliseconds) / (singleIterations * amountOfAssets);
        for(int i=0; i< singleIterations * amountOfAssets;i++) {
            try {
                Thread.sleep(50);//millisPerCalc);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Thread one = new Thread() {
                public void run() {
                    try {
                        final ClusterSimplifiedMat big = new ClusterSimplifiedMat(smallBi,2.5f);
                        big.setImage(bigBi);
                    } catch(Exception v) {
                        System.out.println(v);
                    }
                }
            };
            one.start();
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
