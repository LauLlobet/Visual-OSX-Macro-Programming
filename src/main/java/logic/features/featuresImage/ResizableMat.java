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
        //imageCopy = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
        //loadFromBufferedImageToOriginalVersion(img);
        //targetImg = new Mat();
        //setTargetSize(ratio);
    }

    public void setImage(Mat img){
        loadFromBufferedImageToOriginalVersion(img);
        setTargetSize(ratio);
        setNotValidated();
    }

    private void loadFromBufferedImageToOriginalVersion(BufferedImage image) {
        imageCopy.getGraphics().drawImage(image, 0, 0, null);
        byte[] data = ((DataBufferByte) imageCopy.getRaster().getDataBuffer()).getData();
        imageOriginal = new Mat(image.getHeight(),image.getWidth(), CvType.CV_8UC3);
        imageOriginal.put(0, 0, data);
    }

    public void setNotValidated(){
        validated = false;
    }

    public void setTargetSize(float ratio) {
        this.ratio = ratio;
        Size s = new Size(imageOriginal.size().width/ratio,imageOriginal.size().height/ratio);
        Imgproc.resize(imageOriginal,targetImg,s);
    }

    public Mat getImage() {
        return targetImg;
    }
}
