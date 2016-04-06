package logic;

import org.junit.Assert;
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
        iff.loadBigFromFile("./asssets/big.png",2);
        iff.loadSmallFromFile("./asssets/small.png",2);
        int num = 1;//7 * 25 * 10;
        double now = System.currentTimeMillis();


        FeatureSearchParams featureSearchParams = new FeatureSearchParams();
        featureSearchParams.matchingPercent = 50;
        featureSearchParams.nstars = 10;
        featureSearchParams.precision = 5; //px
        featureSearchParams.spongeLimit = 10;

        for(int i=0; i< num;i++) {
            Point xy = iff.getFeatureXYsameSize(featureSearchParams);
        }
        System.out.println("->"+((System.currentTimeMillis()-now)/10000));
        iff.printQuick();
    }


}
