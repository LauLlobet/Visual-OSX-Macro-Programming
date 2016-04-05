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
        Point xy = iff.getFeatureXYPosition();
        Assert.assertEquals(xy, new Point(10,10));
    }


}
