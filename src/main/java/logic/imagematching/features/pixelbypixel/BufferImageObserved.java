package logic.imagematching.features.pixelbypixel;

import logic.imagematching.features.featuresImage.util.DoublePoint;
import org.opencv.core.Mat;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

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
    BufferedImage fromImage;

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
        cstImg.fromImage = img;
        cstImg.rgbData = img.getRGB(0,0, cstImg.getWidth(), cstImg.getHeight(), null, 0,cstImg.getWidth());
        return cstImg;
    }

    public void saveInFile(){
        File outputfile = new File("assets/"+System.currentTimeMillis()+"image.png");
        try {
            ImageIO.write(fromImage, "png", outputfile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public DoublePoint startSearching() {
        DoublePoint dp = new DoublePoint(-1,-1);
        float maxMatchingPixels = 0;

        for(BufferImageObserved modelto0: models){
            modelto0.maxMatchingPixels = 0;
        }
        for (int x = 0; x < BufferImageObserved.this.getWidth(); x++) {
            for (int y = 0; y < BufferImageObserved.this.getHeight(); y++) {
                int catchedFirstColor = rgbData[(y*this.getWidth())+x];
                for( int i=0; i<models.length; i++){
                    BufferImageObserved model = models[i];

                    if(x == 1340 && y == 682){
                        int qqqq = 0;
                    }

                    float index = BufferImageObserved.this.compareOwnToModelAtPoint(x, y, model, catchedFirstColor);
                    if (index > 0) {
                        model.maxMatchingPixels = 1;
                        model.finalX = x;
                        model.finalY = y;
                    }
                }
            }
        }

           for(BufferImageObserved modelto0: models){
            if(modelto0.maxMatchingPixels == 0){
                modelto0.finalX = -1;
                modelto0.finalY = -1;
            }
        }
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

        for (int xo = 0; xo < model.getWidth(); xo++) {
            for (int yo = 0; yo < model.getHeight(); yo++) {
               if(xo==0 && yo ==97){
                   int ggg = 21;
               }
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

        int blue =  pixelMod & 255;
        int green = (pixelMod >> 8) & 255;
        int red =   (pixelMod >> 16) & 255;


        int oblue =  pixelObs & 255;
        int ogreen = (pixelObs >> 8) & 255;
        int ored =   (pixelObs >> 16) & 255;

        int pixdiff = Math.abs((blue-oblue)) + Math.abs((green-ogreen)) + Math.abs(red-ored);

        if( pixdiff <= 30){
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