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


    @Test
    public void TestSimilarity1(){

        FeatureSearchParams params = new FeatureSearchParams();
        params.nstars = 4;
        params.clusterContactDistance = 1;
        params.matchingPercent = 100;
        params.equalator = new PointsEqualator(0);

        Constelation const1 = new Constelation(createPoint(0,0),params);
        Constelation const2 = new Constelation(createPoint(100,100),params);

        const1.addOwnPoint(createPoint(10,10));
        const1.addOwnPoint(createPoint(-10,20));
        const1.addOwnPoint(createPoint(10,-30));
        const1.addOwnPoint(createPoint(80,-30));

        const2.addOwnPoint(createPoint(100+10,100+10));
        const2.addOwnPoint(createPoint(100-10,100+20));
        const2.addOwnPoint(createPoint(100+10,100-30));
        const2.addOwnPoint(createPoint(100+80,100-30));

        assertEquals(4/4,const1.doesItMatch(const2),0);

    }

    @Test
    public void TestRealSmall(){

        FeatureSearchParams params = new FeatureSearchParams();
        params.nstars = 5;
        params.clusterContactDistance = 1;
        params.matchingPercent = 100;
        params.equalator = new PointsEqualator(1);

        Constelation const1 = new Constelation(createPoint(23,8),params);
        Constelation const2 = new Constelation(createPoint(57,9.5f),params);

        const1.addOwnPoint(createPoint(15.0,8.0));
        //const1.addOwnPoint(createPoint(23.0,8.0));//
        const1.addOwnPoint(createPoint(8.0,9.0));
        const1.addOwnPoint(createPoint(38.0,8.5));
        const1.addOwnPoint(createPoint(51.0,10.0));
        const1.addOwnPoint(createPoint(66.71428680419922,8.142857551574707));

        const2.addOwnPoint(createPoint(34.0,3.0));
        //const2.addOwnPoint(createPoint(9.0,6.0));
        const2.addOwnPoint(createPoint(14.0,6.0));
        const2.addOwnPoint(createPoint(49.0,9.5));
       // const2.addOwnPoint(createPoint(57.0,9.5));//
        const2.addOwnPoint(createPoint(71.0,9.5));
        const2.addOwnPoint(createPoint(99.85713958740234,9.285714149475098));

        const1.debug = true;

        assertEquals(4/4,const1.doesItMatch(const2),0);

    }


    @Test
    public void TestSimilarity2(){

        /*Constelation const1 = new Constelation(createPoint(0,0),4);
        Constelation const2 = new Constelation(createPoint(100,100),4);

        const1.addOwnPoint(createPoint(10,10));
        const1.addOwnPoint(createPoint(-10,20));
        const1.addOwnPoint(createPoint(10,-30));
        const1.addOwnPoint(createPoint(80,-30));

        const2.addOwnPoint(createPoint(999,100+10));
        const2.addOwnPoint(createPoint(100-10,100+20));
        const2.addOwnPoint(createPoint(100+10,100-30));
        const2.addOwnPoint(createPoint(100+80,100-30));

        assertEquals(3/4,const1.doesItMatch(const2),0);*/

    }


    @Test
    public void TestSimilarity3(){

       /* Constelation const1 = new Constelation(createPoint(0,0),4);
        Constelation const2 = new Constelation(createPoint(100,100),4);

        const1.addOwnPoint(createPoint(10,10));
        const1.addOwnPoint(createPoint(-10,20));
        const1.addOwnPoint(createPoint(10,-30));
        const1.addOwnPoint(createPoint(80,-30));

        const2.addOwnPoint(createPoint(999,100+10+9));
        const2.addOwnPoint(createPoint(100-10-3,100+20+8));
        const2.addOwnPoint(createPoint(100+10-4,100-30+7));
        const2.addOwnPoint(createPoint(100+80-5,100-30-6));

        assertEquals(3/4,const1.doesItMatch(const2),0);*/

    }

}