package logic.imagematching.features.pixelbypixel;

import logic.imagematching.features.featuresImage.util.DoublePoint;
import org.junit.Test;

/**
 * Created by quest on 11/4/16.
 */
public class BufferImageObservedTest {

    //@Test
   /* public void performanceTest(){

        int seti = 81;

        String set =""+seti;
        BufferImageObserved big = BufferImageObserved.createBufferImageObserved("./assets/"+set+"/big.png");

        BufferImageObserved small1 = BufferImageObserved.createBufferImageObserved("./assets/"+6+"/small.png");
        BufferImageObserved small2 = BufferImageObserved.createBufferImageObserved("./assets/"+7+"/small.png");
        BufferImageObserved small3 = BufferImageObserved.createBufferImageObserved("./assets/"+8+"/small.png");
        BufferImageObserved small4 = BufferImageObserved.createBufferImageObserved("./assets/"+81+"/small.png");

        ArrayList<BufferImageObserved> results = new ArrayList<BufferImageObserved>();

        results.add(small1);
        results.add(small2);
        results.add(small3);
        results.add(small4);


        double testTimeInmilliseconds = 200;
        int singleIterations = (int)(13 * testTimeInmilliseconds/1000);




        singleIterations = 1;

        double now = System.currentTimeMillis();
        for(int i=0; i< singleIterations ;i++) {
            big.addBufferedImagesEqual(small1);
            big.addBufferedImagesEqual(small2);
            big.addBufferedImagesEqual(small3);
            big.addBufferedImagesEqual(small4);
            big.addBufferedImagesEqual(small1);
            big.startSearching();
        }

        System.out.println("->"+((System.currentTimeMillis()-now)/testTimeInmilliseconds));
        for(BufferImageObserved model: results){
            DoublePoint dp = new DoublePoint( model.finalX,model.finalY);
            System.out.println(dp.toString());
        }


    }*/


    @Test
    public void performanceTest(){

        int seti = 10;

        String set =""+seti;
        BufferImageObserved big = BufferImageObserved.createBufferImageObserved("./assets/"+set+"/big.png");
        BufferImageObserved small4 = BufferImageObserved.createBufferImageObserved("./assets/"+set+"/small.png");

        double testTimeInmilliseconds = 200;
        int singleIterations = (int)(13 * testTimeInmilliseconds/1000);
        singleIterations = 1;

        double now = System.currentTimeMillis();
        for(int i=0; i< singleIterations ;i++) {
            big.addBufferedImagesEqual(small4);
            big.startSearching();
        }

        System.out.println("->"+((System.currentTimeMillis()-now)/testTimeInmilliseconds));

        DoublePoint dp = new DoublePoint( small4.finalX,small4.finalY);
        System.out.println(dp.toString());


    }



}