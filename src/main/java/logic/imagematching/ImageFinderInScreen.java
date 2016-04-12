package logic.imagematching;

import logic.imagematching.features.featuresImage.util.DoublePoint;
import logic.imagematching.features.pixelbypixel.BufferImageObserved;
import view.UI.screencapturing.ScreenCapturer;
import view.UI.screencapturing.ScreenRegionsListener;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 * Created by quest on 12/4/16.
 */
public class ImageFinderInScreen implements ScreenRegionsListener {

    BufferImageObserved bigImage;
    private Hashtable<String, BufferImageObserved> resultsForId;
    private ArrayList<BufferImageObserved> smallImages;

    public ImageFinderInScreen(){
        resultsForId = new Hashtable<String, BufferImageObserved>();
        smallImages = new ArrayList<BufferImageObserved>();
    }

    public void registerASmallImage(BufferedImage bi, String id){
        if(resultsForId.containsKey(id)){
            BufferImageObserved removed = resultsForId.remove(id);
            smallImages.remove(removed);
        }
        BufferImageObserved small = BufferImageObserved.createFromImage(bi);
        smallImages.add(small);
        resultsForId.put(id,small);
    }

    public DoublePoint getLocationFromId(String id){
        DoublePoint dp;
        try{
            BufferImageObserved small = resultsForId.get(id);
            dp = new DoublePoint( small.finalX,small.finalY);
        }catch (Exception e){
            dp = new DoublePoint(-2,-2);
        }
        return dp;
    }

    public void registerNewBigImage(BufferedImage big){
        setBigImageFromBufferedImage(big);
        registerAllSmallImagesToBigImage(smallImages);
        searchAllSmallImagesInBigImage();
    }

    private void searchAllSmallImagesInBigImage() {
        bigImage.startSearching();
    }

    private void registerAllSmallImagesToBigImage(ArrayList<BufferImageObserved> smallImages) {
        for(BufferImageObserved smallImage : smallImages){
            bigImage.addBufferedImagesEqual(smallImage);
        }
    }

    public void setBigImageFromBufferedImage(BufferedImage bigImageBufferedImage) {
        bigImage = BufferImageObserved.createFromImage(bigImageBufferedImage);
    }

    @Override
    public void newCapture(BufferedImage capture) {
        registerNewBigImage(capture);
    }

    public void registerToCapturer(ScreenCapturer screenCapturer){
        screenCapturer.setFullScreenListener(this);

    }
}
