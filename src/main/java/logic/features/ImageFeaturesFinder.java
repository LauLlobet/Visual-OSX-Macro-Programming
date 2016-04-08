package logic.features;

import javax.imageio.ImageIO;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

import logic.features.featuresImage.util.FeatureSearchParams;
import org.opencv.core.*;
import org.opencv.features2d.*;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;


/**
 * Created by quest on 5/4/16.
 */
public class ImageFeaturesFinder {
    BufferedImage big;
    private BufferedImage small;

    static{ System.loadLibrary(Core.NATIVE_LIBRARY_NAME); }

    Mat observedImagebig = Highgui.imread("./assets/big.png");
    Mat modelImagebig = Highgui.imread("./assets/small.png");
    Mat modelImage = Highgui.imread("./assets/small.png");
    Mat observedImage = Highgui.imread("./assets/big.png");
    MatOfKeyPoint modelKeyPoints = new MatOfKeyPoint();
    MatOfKeyPoint observedKeyPoints = new MatOfKeyPoint();
    Mat modelDescriptors = new Mat();
    Mat observedDescriptors = new Mat();
    DescriptorExtractor descriptor =  DescriptorExtractor.create(DescriptorExtractor.SIFT);
    DescriptorMatcher matcher = DescriptorMatcher.create(DescriptorMatcher.BRUTEFORCE);
    DescriptorMatcher fastmatcher = DescriptorMatcher.create(DescriptorMatcher.BRUTEFORCE);
    FeatureDetector fast = FeatureDetector.create(FeatureDetector.FAST);
    MatOfDMatch matches = new MatOfDMatch();
    private MatOfKeyPoint matchingObservedKeyPoints = new MatOfKeyPoint();
    private MatOfKeyPoint matchingModelKeyPoints = new MatOfKeyPoint();


    public BufferedImage loadFromFile(String s,float ratio) {
        Imgproc.resize(modelImagebig,modelImage,new Size(modelImagebig.size().width/ratio,modelImagebig.size().height/ratio));
        Imgproc.resize(observedImagebig,observedImage, new Size(observedImagebig.size().width/ratio,observedImagebig.size().height/ratio));

        File img = new File(s);
        BufferedImage buffImg =
                new BufferedImage(240, 240, BufferedImage.TYPE_INT_ARGB);
        try {
            buffImg = ImageIO.read(img );
        }
        catch (IOException e) { }
        return buffImg;
    }

    public void loadBigFromFile(String s, float ratio) {
        big = loadFromFile(s, ratio);
    }

    public void loadSmallFromFile(String s, float ratio) {
        small = loadFromFile(s, ratio);
    }

    public Point getFeatureXYPositionEach2Sec() {

        fast.detect(modelImage,modelKeyPoints);
        descriptor.compute(modelImage, modelKeyPoints, modelDescriptors);

        fast.detect(observedImage,observedKeyPoints);
        descriptor.compute(observedImage, observedKeyPoints, observedDescriptors);

        matcher.match(modelDescriptors, observedDescriptors,matches);


        return new Point(10,10);
    }


    public Point getFeatureXYsameSize(FeatureSearchParams featureSearchParams) {
        fast.detect(modelImage,modelKeyPoints);
        fast.detect(observedImage,observedKeyPoints);
        KeyPointsComparer kpcomp = new KeyPointsComparer(featureSearchParams);
        kpcomp.findOffsetBetweenImages(modelImage,
                observedImage,
                modelKeyPoints,
                observedKeyPoints,
                matchingModelKeyPoints,
                matchingObservedKeyPoints);

        return new Point(10,10);
    }

    public void print() {
        Mat outImage = new Mat();
        //Draw the matched keypoints
        Features2d.drawMatches(modelImage, modelKeyPoints, observedImage, observedKeyPoints, matches, outImage);
        Highgui.imwrite("./assets/result2.png",outImage);
    }

    public void printQuick() {
        Mat outImage = new Mat();
        //Draw the matched keypoints
        Features2d.drawMatches(modelImage, matchingModelKeyPoints, observedImage, matchingObservedKeyPoints, matches, outImage);
        Highgui.imwrite("./assets/result2.png",outImage);

        Features2d.drawMatches(modelImage, modelKeyPoints, observedImage, observedKeyPoints, matches, outImage);
        Highgui.imwrite("./assets/keyPoints2.png",outImage);
    }

    public void iterateMatches(){
        java.util.List myList = matches.toList();

        Iterator itr = myList.iterator();
        while(itr.hasNext())
        {
            DMatch element = (DMatch) itr.next();
            // your code here
        }
    }

}
