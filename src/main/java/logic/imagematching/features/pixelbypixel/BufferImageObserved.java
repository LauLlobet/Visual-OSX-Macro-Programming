package logic.imagematching.features.pixelbypixel;

import logic.imagematching.features.featuresImage.util.DoublePoint;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;

/**
 * Created by quest on 11/4/16.
 */

public class BufferImageObserved extends BufferedImage {
    public static ArrayList<DoublePoint> resultsBuffer;

    int[] rgbData ;
    private DoublePoint dp;
    public BufferImageObserved[] models;

    public float index = 0;
    public float maxMatchingPixels = 0;
    public int finalX = -1;
    public int finalY = -1;

    public BufferImageObserved(int i, int i1, int i2) {
        super(i, i1, i2);
        resetModelsList();
    }

    public static BufferImageObserved createBufferImageObserved(String filepath){
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(filepath));
            return createFromImage(img);
        } catch (IOException e) {
            return null;
        }
    }

    public static BufferImageObserved createFromImage(BufferedImage img) {
        final  BufferImageObserved cstImg = new BufferImageObserved(
                img.getWidth(),
                img.getHeight(), img.
                getType());

        cstImg.rgbData = img.getRGB(0,0, cstImg.getWidth(), cstImg.getHeight(), null, 0,cstImg.getWidth());
        return cstImg;
    }

    public DoublePoint startSearching() {
        DoublePoint dp = new DoublePoint(-1,-1);
        int finalX = -1;
        int finalY = -1;
        float maxMatchingPixels = 0;
        for (int x = 0; x < BufferImageObserved.this.getWidth(); x++) {
            for (int y = 0; y < BufferImageObserved.this.getHeight(); y++) {
                int catchedFirstColor = rgbData[(y*this.getWidth())+x];
                for( int i=0; i<models.length; i++){
                    BufferImageObserved model = models[i];
                    float index = BufferImageObserved.this.compareOwnToModelAtPoint(x, y, model, catchedFirstColor);
                    if (index > model.maxMatchingPixels) {
                        model.maxMatchingPixels = index;
                        model.finalX = x;
                        model.finalY = y;
                    }
                }
            }
        }
        dp.setX(finalX);
        dp.setY(finalY);
        resetModelsList();
        return dp;
    }

    private void resetModelsList() {
        models = new BufferImageObserved[0];
    }

    private float compareOwnToModelAtPoint(int x, int y, BufferImageObserved model, int catchedFirstPixelColor) {
        int numberOfMatchingPixels = 0;
        if (modelAndThisNotComparableAtThisPoint(x, y, model)) {
            return 0;
        }
        int pixelMod = model.rgbData[(0*model.getWidth())+0];
        if( catchedFirstPixelColor != pixelMod){
            return 0;
        }

        for (int xo = 1; xo < model.getWidth(); xo++) {
            for (int yo = 0; yo < model.getHeight(); yo++) {
                float inc =  compareTwoPointsObsModel(x + xo, y +yo, xo, yo, model);
                if(inc == 0){
                    return 0;
                }
            }
        }
        return 1;
    }

    private float compareTwoPointsObsModel(int xobs, int yobs, int mx, int my, BufferImageObserved model) {
        int pixelMod = model.rgbData[(my*model.getWidth())+mx];
        int pixelObs = rgbData[(yobs*this.getWidth())+xobs];
        if( pixelObs == pixelMod){
            return 1;
        }
        return 0;
    }

    private boolean modelAndThisNotComparableAtThisPoint(int x, int y, BufferImageObserved model) {
        if (x + model.getWidth() > getWidth() || y + model.getHeight() > getHeight()) {
            return true;
        }
        return false;
    }

    public void addBufferedImagesEqual(BufferImageObserved model) {
        ArrayList<BufferImageObserved> a =  new ArrayList(Arrays.asList(models));
        a.add(model);
        models =a.toArray(new BufferImageObserved[models.length+1]);
    }

}