package logic.imagematching.features.featuresImage;

import logic.imagematching.features.featuresImage.util.Constelation;
import logic.imagematching.features.featuresImage.util.DoublePoint;
import logic.imagematching.features.featuresImage.util.FeatureSearchParams;
import net.sf.javaml.core.kdtree.KDTree;
import org.opencv.core.Mat;
import org.opencv.core.MatOfKeyPoint;
import org.opencv.features2d.KeyPoint;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * Created by quest on 7/4/16.
 */


public class MatchingObservedMat extends ClusterSimplifiedMat {

    private Hashtable<KeyPoint,Constelation> ownConstelations;
    KeyPoint[] modelKeyPoints;
    KeyPoint[] observedKeyPoints;
    ArrayList<KeyPoint> modelKPMatched = new ArrayList<KeyPoint>();
    ArrayList<KeyPoint> observerdKPMatched = new ArrayList<KeyPoint>();
    ClusterImage observedSimplifiedKeyPointsPrecisionIsClusterWidth;
    int version = 0;

    public MatchingObservedMat(Mat img, FeatureSearchParams params) {
        super(img,params);
        observedSimplifiedKeyPointsPrecisionIsClusterWidth = new ClusterImage(img.width(),img.height());
    }
    @Override
    public void setImage(Mat img){
        super.setImage(img);
        observedKeyPoints = this.getKeyPoints().toArray();
        observedSimplifiedKeyPointsPrecisionIsClusterWidth.processClusters(observedKeyPoints,params.matchingMatPrecision, img);
    }

    private void fillClusterImage() {
    }

    public DoublePoint findOffset(ClusterSimplifiedMat otherImage){
        modelKeyPoints = otherImage.getKeyPoints().toArray();
        KeyPoint[] bestMatch = new KeyPoint[2];
        float bestMatchIndex = 0;
        for(KeyPoint modelKPoint: modelKeyPoints){
            for(KeyPoint observedPointPosibleCenterOfModelConstelation : observedKeyPoints){
                Constelation oneOfmodelsConstelations = new Constelation(createPoint(modelKPoint.pt.x, modelKPoint.pt.y),
                        modelKeyPoints);

                if(observedPointPosibleCenterOfModelConstelation.pt.x == 325
                        && observedPointPosibleCenterOfModelConstelation.pt.y == 161
                        && modelKPoint.pt.x == 8
                        && modelKPoint.pt.y == 9){
                    int a =2;
                }

               /* if(observedPointPosibleCenterOfModelConstelation.pt.x == 281
                        && observedPointPosibleCenterOfModelConstelation.pt.y == 78
                        && modelKPoint.pt.x == 15
                        && modelKPoint.pt.y == 8){
                    int a =2;
                }*/

                double[] color = otherImage.targetImg.get((int)modelKPoint.pt.y+5,(int)modelKPoint.pt.x);

                float matchingIndex = oneOfmodelsConstelations.doesItMatch(observedPointPosibleCenterOfModelConstelation,
                        observedSimplifiedKeyPointsPrecisionIsClusterWidth,color);
                if(bestMatchIndex < matchingIndex){
                    bestMatch[0]=modelKPoint;
                    bestMatch[1]=observedPointPosibleCenterOfModelConstelation;
                    bestMatchIndex = matchingIndex;
                }
               /* if(matchingIndex >= 0.8 && matchingIndex <= 1){// && observedPointPosibleCenterOfModelConstelation.pt.x > 316
                        //&& observedPointPosibleCenterOfModelConstelation.pt.x < 320){
                    DoublePoint doub = new DoublePoint(observedPointPosibleCenterOfModelConstelation.pt.x,
                            observedPointPosibleCenterOfModelConstelation.pt.y);

                    System.out.println("---------------------- bingo V"+matchingIndex+" "+doub);
                    System.out.println("off:"+new DoublePoint(observedPointPosibleCenterOfModelConstelation.pt.x - modelKPoint.pt.x,
                            observedPointPosibleCenterOfModelConstelation.pt.y - modelKPoint.pt.y));
                    System.out.println("mod:"+new DoublePoint( modelKPoint.pt.x,
                             modelKPoint.pt.y));
                    System.out.println("obs:"+new DoublePoint(observedPointPosibleCenterOfModelConstelation.pt.x
                            ,observedPointPosibleCenterOfModelConstelation.pt.y));

                }*/
            }
        }
        try{
            return new DoublePoint(bestMatch[0].pt.x - bestMatch[1].pt.x,
                    bestMatch[0].pt.y - bestMatch[1].pt.y);
        }catch (Exception e){
            return null;
        }
    }

    public void printMatches(){

        int i =0;
        for(KeyPoint k: modelKPMatched){
            printKeyPoint(k);
            System.out.println("matchesV");
            printKeyPoint(observerdKPMatched.get(i));
            System.out.println("------");
            i++;
        }
        printKeyPoints(modelKeyPoints);
        System.out.println("ObservedKeyPoints:"+observedKeyPoints.length);
        printKeyPoints(observedKeyPoints);

    }

    public void printKeyPoints(){
        System.out.println("ModelKeyPoints:"+modelKeyPoints.length);
        printKeyPoints(modelKeyPoints);
        System.out.println("ObservedKeyPoints:"+observedKeyPoints.length);
        printKeyPoints(observedKeyPoints);

    }
    public void printCaracteristics(){
        System.out.println("ModelKeyPoints:"+modelKeyPoints.length);
        System.out.println("ObservedKeyPoints:"+observedKeyPoints.length);
        System.out.println("NumMatches:"+modelKPMatched.size());
        System.out.println("ObserverdKPMatched:"+observerdKPMatched.size());
    }
    private void printKeyPoints(KeyPoint[] modelKeyPoints) {
        for(KeyPoint k : modelKeyPoints){
            printKeyPoint(k);
        }
    }
    public void printKeyPoint(KeyPoint k){
        System.out.println("const1.addOwnPoint(createPoint("+k.pt.x+","+k.pt.y+"));");
    }

    private void createKDTree(KDTree tree, KeyPoint[] keypoints) {
        double[] point = new double[2];
        for(KeyPoint k : keypoints){
                point[0] = k.pt.x;
                point[1] = k.pt.y;
            try{
                tree.insert(point,k);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public double[] createPoint(double x, double y){
        double[] pos = new double[2];
        pos[0]=x;
        pos[1]=y;
        return pos;
    }

    public double[] createPoint(ArrayList<Double> a){
        double[] pos = new double[2];
        pos[0]=a.get(0);
        pos[1]=a.get(1);
        return pos;
    }


    public MatOfKeyPoint getModelMatchingKeyPoints() {
        MatOfKeyPoint ans = new MatOfKeyPoint();
        ans.fromList(modelKPMatched);
        return ans;
    }

    public MatOfKeyPoint getObslMatchingKeyPoints() {
        MatOfKeyPoint ans = new MatOfKeyPoint();
        ans.fromList(observerdKPMatched);
        return ans;
    }
}
