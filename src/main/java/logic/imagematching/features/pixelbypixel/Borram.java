package logic.imagematching.features.pixelbypixel;

import logic.imagematching.features.featuresImage.util.DoublePoint;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by quest on 14/4/16.
 */
public class Borram extends BufferedImage {
    public static ArrayList<DoublePoint> resultsBuffer;
    int[] rgbData ;
    private DoublePoint dp;
    public Borram[] models;

    public float index = 0;
    public float maxMatchingPixels = 0;
    public int finalX = -1;
    public int finalY = -1;
    BufferedImage fromImage;



    public Borram(int i, int i1, int i2) {
        super(i, i1, i2);
        resetModelsList();
    }


    public void borrAM(){
        DoublePoint dp = new DoublePoint(-1,-1);
        float maxMatchingPixels = 0;

        for(Borram modelto0: models){
            modelto0.maxMatchingPixels = 0;
        }
        for (int x = 0; x < Borram.this.getWidth(); x++) {
            for (int y = 0; y < Borram.this.getHeight(); y++) {
                int catchedFirstColor = rgbData[(y*this.getWidth())+x];
                for( int i=0; i<models.length; i++){
                    Borram model = models[i];

                    if(x == 1340 && y == 682){
                        int qqqq = 0;
                    }

                    float index = Borram.this.compareOwnToModelAtPoint(x, y, model, catchedFirstColor);
                    if (index > 0) {
                        model.maxMatchingPixels = 1;
                        model.finalX = x;
                        model.finalY = y;
                    }
                }
            }
        }

        for(Borram modelto0: models){
            if(modelto0.maxMatchingPixels == 0){
                modelto0.finalX = -1;
                modelto0.finalY = -1;
            }
        }
    }

    private void resetModelsList() {
        models = new Borram[0];
    }

    private float compareOwnToModelAtPoint(int x, int y, Borram model, int catchedFirstPixelColor) {
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

    private float compareTwoPointsObsModel(int xobs, int yobs, int mx, int my, Borram model) {
        int pixelMod = model.rgbData[(my*model.getWidth())+mx];
        int pixelObs = rgbData[(yobs*this.getWidth())+xobs];

        int blue =  pixelMod & 255;
        int green = (pixelMod >> 8) & 255;
        int red =   (pixelMod >> 16) & 255;


        int oblue =  pixelObs & 255;
        int ogreen = (pixelObs >> 8) & 255;
        int ored =   (pixelObs >> 16) & 255;

        int pixdiff = Math.abs((blue-oblue)) + Math.abs((green-ogreen)) + Math.abs(red-ored);

        if( pixdiff <= 3){
            return 1;
        }
        return 0;
    }

    private boolean modelAndThisNotComparableAtThisPoint(int x, int y, Borram model) {
        if (x + model.getWidth() > getWidth() || y + model.getHeight() > getHeight()) {
            return true;
        }
        return false;
    }

}
