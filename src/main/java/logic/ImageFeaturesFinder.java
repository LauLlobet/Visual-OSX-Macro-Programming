package logic;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.opencv.core.*;
import org.opencv.features2d.*;
import org.opencv.highgui.Highgui;


/**
 * Created by quest on 5/4/16.
 */
public class ImageFeaturesFinder {
    BufferedImage big;
    private BufferedImage small;

    static{ System.loadLibrary(Core.NATIVE_LIBRARY_NAME); }



    public BufferedImage loadFromFile(String s) {
        File img = new File(s);
        BufferedImage buffImg =
                new BufferedImage(240, 240, BufferedImage.TYPE_INT_ARGB);
        try {
            buffImg = ImageIO.read(img );
        }
        catch (IOException e) { }
        return buffImg;
    }

    public void loadBigFromFile(String s) {
        big = loadFromFile(s);
    }

    public void loadSmallFromFile(String s) {
        small = loadFromFile(s);
    }

    public Point getFeatureXYPosition() {

        Mat modelImage = Highgui.imread("./assets/big.png");
        Mat observedImage = Highgui.imread("./assets/small.png");

        Mat homography = null;

        FeatureDetector fast = FeatureDetector.create(FeatureDetector.FAST);
        MatOfKeyPoint modelKeyPoints = new MatOfKeyPoint();
        MatOfKeyPoint observedKeyPoints = new MatOfKeyPoint();
        Mat indices;

        DescriptorExtractor descriptor =  DescriptorExtractor.create(DescriptorExtractor.BRIEF);
        Mat mask;
        int k = 2;
        double uniquenessThreshold = 0.8;

        fast.detect(modelImage,modelKeyPoints);
        Mat modelDescriptors = new Mat();
        descriptor.compute(modelImage, modelKeyPoints, modelDescriptors);

        fast.detect(observedImage,observedKeyPoints);
        Mat observedDescriptors = new Mat();
        descriptor.compute(observedImage, observedKeyPoints, observedDescriptors);
        DescriptorMatcher matcher = DescriptorMatcher.create(DescriptorMatcher.FLANNBASED);
        MatOfDMatch matches = new MatOfDMatch();
        matcher.match(modelImage, observedDescriptors,matches);

        ArrayList<DMatch> myList = matches.toList();

        Iterator itr = myList.iterator();
        while(itr.hasNext())
        {
            DMatch element = (DMatch) itr.next();
            // your code here
        }

        //Draw the matched keypoints
        Image<Bgr, Byte> result = Features2d.drawMatches(modelImage, modelKeyPoints, observedImage, observedKeyPoints,
                indices, new Bgr(255, 255, 255), new Bgr(255, 255, 255), mask, Features2DToolbox.KeypointDrawType.DEFAULT);

        drawMatches(Mat img1, MatOfKeyPoint keypoints1, Mat img2, MatOfKeyPoint keypoints2, MatOfDMatch matches1to2, Mat outImg, Scalar matchColor, Scalar singlePointColor, MatOfByte matchesMask, int flags)


        #region draw the projected region on the image
        if (homography != null)
        {  //draw a rectangle along the projected model
            Rectangle rect = modelImage.ROI;
            PointF[] pts = new PointF[] {
                    new PointF(rect.Left, rect.Bottom),
                    new PointF(rect.Right, rect.Bottom),
                    new PointF(rect.Right, rect.Top),
                    new PointF(rect.Left, rect.Top)};
            homography.ProjectPoints(pts);

            result.DrawPolyline(Array.ConvertAll<PointF, Point>(pts, Point.Round), true, new Bgr(Color.Red), 5);
        }

        return new Point(10,10);
    }

    /*

    public static Image<Bgr, Byte> Draw(Image<Gray, Byte> modelImage, Image<Gray, byte> observedImage)
{
   HomographyMatrix homography = null;

   FastDetector fastCPU = new FastDetector(10, true);
   VectorOfKeyPoint modelKeyPoints;
   VectorOfKeyPoint observedKeyPoints;
   Matrix<int> indices;

   BriefDescriptorExtractor descriptor = new BriefDescriptorExtractor();

   Matrix<byte> mask;
   int k = 2;
   double uniquenessThreshold = 0.8;

   //extract features from the object image
   modelKeyPoints = fastCPU.DetectKeyPointsRaw(modelImage, null);
   Matrix<Byte> modelDescriptors = descriptor.ComputeDescriptorsRaw(modelImage, null, modelKeyPoints);

   // extract features from the observed image
   observedKeyPoints = fastCPU.DetectKeyPointsRaw(observedImage, null);
   Matrix<Byte> observedDescriptors = descriptor.ComputeDescriptorsRaw(observedImage, null, observedKeyPoints);
   BruteForceMatcher<Byte> matcher = new BruteForceMatcher<Byte>(DistanceType.L2);
   matcher.Add(modelDescriptors);

   indices = new Matrix<int>(observedDescriptors.Rows, k);
   using (Matrix<float> dist = new Matrix<float>(observedDescriptors.Rows, k))
   {
      matcher.KnnMatch(observedDescriptors, indices, dist, k, null);
      mask = new Matrix<byte>(dist.Rows, 1);
      mask.SetValue(255);
      Features2DToolbox.VoteForUniqueness(dist, uniquenessThreshold, mask);
   }

   int nonZeroCount = CvInvoke.cvCountNonZero(mask);
   if (nonZeroCount >= 4)
   {
      nonZeroCount = Features2DToolbox.VoteForSizeAndOrientation(modelKeyPoints, observedKeyPoints, indices, mask, 1.5, 20);
      if (nonZeroCount >= 4)
         homography = Features2DToolbox.GetHomographyMatrixFromMatchedFeatures(
         modelKeyPoints, observedKeyPoints, indices, mask, 2);
   }

   //Draw the matched keypoints
   Image<Bgr, Byte> result = Features2DToolbox.DrawMatches(modelImage, modelKeyPoints, observedImage, observedKeyPoints,
      indices, new Bgr(255, 255, 255), new Bgr(255, 255, 255), mask, Features2DToolbox.KeypointDrawType.DEFAULT);

   #region draw the projected region on the image
   if (homography != null)
   {  //draw a rectangle along the projected model
      Rectangle rect = modelImage.ROI;
      PointF[] pts = new PointF[] {
         new PointF(rect.Left, rect.Bottom),
         new PointF(rect.Right, rect.Bottom),
         new PointF(rect.Right, rect.Top),
         new PointF(rect.Left, rect.Top)};
         homography.ProjectPoints(pts);

      result.DrawPolyline(Array.ConvertAll<PointF, Point>(pts, Point.Round), true, new Bgr(Color.Red), 5);
   }
   #endregion

   return result;
}
     */
}
