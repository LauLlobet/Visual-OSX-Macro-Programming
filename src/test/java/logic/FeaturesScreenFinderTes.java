package logic;

import logic.features.util.FeatureSearchParams;
import logic.features.ImageFeaturesFinder;
import org.junit.Test;

import java.awt.*;

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

    @Test
    public void testSingleScreen(){
        ImageFeaturesFinder iff = new ImageFeaturesFinder();
        iff.loadBigFromFile("./asssets/big.png",2.5f);
        iff.loadSmallFromFile("./asssets/small.png",2.5f);
        int num = 7 * 25 * 10;
        double now = System.currentTimeMillis();


        FeatureSearchParams featureSearchParams = new FeatureSearchParams();
        featureSearchParams.matchingPercent = 5;
        featureSearchParams.nstars = 20;
        featureSearchParams.precision = 3; //px

        for(int i=0; i< num;i++) {
            Point xy = iff.getFeatureXYsameSize(featureSearchParams);
        }
        System.out.println("->"+((System.currentTimeMillis()-now)/10000));
        iff.printQuick();
    }


}
