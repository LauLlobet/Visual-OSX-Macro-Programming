package logic.imagematching.features.pixelbypixel;

import jni.JniSample;
import logic.HelloWorld;
import logic.imagematching.features.featuresImage.util.DoublePoint;
import org.opencv.core.Core;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
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


    ImageMatcher js = new ImageMatcher();

    static {
        System.loadLibrary("ImageMatcher");
    }

    public BufferImageObserved(int i, int i1, int i2) {
        super(i, i1, i2);
        resetModelsList();
    }

    private void resetModelsList() {
        models = new BufferImageObserved[0];
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

    public void addBufferedImagesEqual(BufferImageObserved model) {
        ArrayList<BufferImageObserved> a =  new ArrayList(Arrays.asList(models));
        a.add(model);
        models =a.toArray(new BufferImageObserved[models.length+1]);
    }

    public void startSearching() {
        int[] observed = this.rgbData;

        for(int i=0; i< models.length ; i++){
            int[] model = this.models[i].rgbData;
            js.searchImageInImage(observed,this.getWidth(),this.getHeight(),models[0].rgbData,models[i].getWidth(),models[i].getHeight());
        }
        resetModelsList();
    }
}