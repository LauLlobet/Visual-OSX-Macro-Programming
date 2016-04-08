package logic.features;

import logic.features.featuresImage.util.ClusterCenter;
import org.opencv.features2d.KeyPoint;

import java.util.ArrayList;

/**
 * Created by quest on 6/4/16.
 */
    public class ClusterImage {
    private final int width;
    ArrayList<ClusterCenter> mostGeneralCenters = new ArrayList<ClusterCenter>();
    private double version = 0;
    private int height;

    public class ClusterImagePixel {
        KPointAndCluster xpy;
        double version;
        public ClusterImagePixel(){
            version = -1;
        }
        public void touchedBy(KPointAndCluster touching, double version){
            if(this.version != version){
                xpy = touching;
                this.version = version;
                return;
            }
            xpy.clusterCenter.touches(touching.clusterCenter);
        }
    }

    public class KPointAndCluster{
        KeyPoint point;
        ClusterCenter clusterCenter;
        public KPointAndCluster(KeyPoint p){
            clusterCenter = new ClusterCenter(p,mostGeneralCenters);
            point = p;
        }
    }

    ClusterImagePixel[][] clusterImagePixel;

    public ClusterImage(int w ,int h){
        this.width = w;
        this.height = h;
        allocateImage(w+1, h+1);
        mostGeneralCenters = new ArrayList<ClusterCenter>();
    }

    private void processClusters(KeyPoint[] points ,int margin){
        mostGeneralCenters.clear();
        for(KeyPoint point: points){
            KPointAndCluster p = new KPointAndCluster(point);
            for(int x = -margin; x<margin ; x++) {
                for (int y = -margin; y < margin; y++) {
                    int xp = (int) point.pt.x +x;
                    int yp = (int) point.pt.y +y;
                    if(xp >=0 && yp>=0 && xp < width && yp < height){
                        clusterImagePixel[xp][yp].touchedBy(p, version);
                    }
                }
            }
        }
        version ++;
    }

    public ArrayList<KeyPoint> groupKeyPointsInSingleOnes(KeyPoint[] points , int margin){
        processClusters(points,margin);
        return extractMostGeneralCenters();
    }

    private ArrayList<KeyPoint> extractMostGeneralCenters() {
        ArrayList<KeyPoint> result = new ArrayList<KeyPoint>();
        for(ClusterCenter cc: mostGeneralCenters){
            result.add(new KeyPoint((float)cc.point.getX(),(float)cc.point.getY(),10));
        }
        return result;
    }

    private void allocateImage(int w, int h) {
        clusterImagePixel = new ClusterImagePixel[w][h];
        for (int i=0; i<w; i++ ){
            for (int j=0; j<h; j++ ){
                clusterImagePixel[i][j]= new ClusterImagePixel();
            }
        }
    }
}
