package logic.imagematching.features.pixelbypixel;

/**
 * Created by quest on 13/4/16.
 */
public class ImageMatcher {

    public native double[] searchImageInImage(int[] observer,int ow, int oh, int[] model, int mw, int mh);

}
