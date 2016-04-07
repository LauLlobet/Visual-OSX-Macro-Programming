package logic.features.featuresImage;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.nio.Buffer;

/**
 * Created by quest on 7/4/16.
 */
public class ResizableMat {

    static{ System.loadLibrary(Core.NATIVE_LIBRARY_NAME); }

    protected Mat imageOriginal;
    protected Mat targetImg;
    protected float ratio;
    protected boolean validated = false;
    BufferedImage imageCopy;

    public ResizableMat(){
    }

    public void setImage(Mat img){
        targetImg = img;
        setNotValidated();
    }


    public void setNotValidated(){
        validated = false;
    }


    public Mat getImage() {
        return targetImg;
    }
}
