package logic;

import logic.imagematching.features.pixelbypixel.BufferImageObserved;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 * Created by quest on 12/4/16.
 */
public class ImageFinderInScreen {

    BufferImageObserved bigImage;
    private Hashtable<String, BufferImageObserved> resultsForId;
    private ArrayList<BufferImageObserved> smallImages;


    public void registerNewBigImage(BufferedImage big){
        setBigImageFromBufferedImage(big);
        registerAllSmallImagesToBigImage(smallImages);
        searchAllSmallImagesInBigImage();
    }

    private void searchAllSmallImagesInBigImage() {

    }

    private void registerAllSmallImagesToBigImage(ArrayList<BufferImageObserved> smallImages) {

    }

    public void setBigImageFromBufferedImage(BufferedImage bigImageFromBufferedImage) {
    }
}
