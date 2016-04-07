package logic.features.featuresImage;

import org.opencv.core.MatOfKeyPoint;
import org.opencv.features2d.DescriptorExtractor;
import org.opencv.features2d.DescriptorMatcher;
import org.opencv.features2d.FeatureDetector;

import java.awt.image.BufferedImage;

/**
 * Created by quest on 7/4/16.
 */
public class FeaturesMat extends ResizableMat {

    DescriptorExtractor descriptorFinder;
    DescriptorMatcher matchFinder;
    FeatureDetector featureFinder = FeatureDetector.create(FeatureDetector.FAST);
    MatOfKeyPoint keyPoints = new MatOfKeyPoint();


    public FeaturesMat(BufferedImage img, float ratio) {
        super(img, ratio);
    }

    public void setFeatureFinder(int finder){ //FeatureDetector.FAST
        featureFinder = FeatureDetector.create(finder);
    }

    public void setNonConstellationMatcher(int type){ // DescriptorMatcher.BRUTEFORCE
        matchFinder = DescriptorMatcher.create(type);
    }

    public void setDescriptorFinder(int type){ // SIFT
        descriptorFinder =  DescriptorExtractor.create(type);
    }

    public void  extractKeyPoints(){
        featureFinder.detect(targetImg,keyPoints);
    }


    public MatOfKeyPoint getKeyPoints() {
        return keyPoints;
    }
}
