package logic;

import logic.features.featuresImage.util.Constelation;
import logic.features.featuresImage.util.FeatureSearchParams;
import logic.features.featuresImage.util.PointsEqualator;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by quest on 6/4/16.
 */
public class ConstelationTest {

    public double[] createPoint(double x, double y){
        double[] pos = new double[2];
        pos[0]=x;
        pos[1]=y;
        return pos;
    }




    @Test
    public void TestCompartor(){

        PointsEqualator const1 = new PointsEqualator(10);

        assertEquals(0,const1.compare(createPoint(10,10),createPoint(10,10)));

    }

}