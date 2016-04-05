package logic;

import org.junit.Assert;
import org.junit.Test;

import java.awt.*;

/**
 * Created by quest on 5/4/16.
 */
public class FeaturesScreenFinderTes {

    @Test
    public void testSingleScreen(){
        ImageFeaturesFinder iff = new ImageFeaturesFinder();
        iff.loadBigFromFile("./asssets/big.png");
        iff.loadSmallFromFile("./asssets/small.png");
        int num = 1;
        double now = System.currentTimeMillis();
        for(int i=0; i< num;i++) {
            Point xy = iff.getFeatureXYPositionEach2Sec();
        }
        System.out.println("->"+(1000/(System.currentTimeMillis()-now)));
        iff.print();
    }


}
