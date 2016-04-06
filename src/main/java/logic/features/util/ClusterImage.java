package logic.features.util;

import org.opencv.features2d.KeyPoint;
import sun.awt.resources.awt;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by quest on 6/4/16.
 */
    public class ClusterImage {
    ArrayList<ClusterCenter> mostGeneralCenters = new ArrayList<ClusterCenter>();
    private double version = 0;

    public class ClusterImagePixel {
        KPointAndCluster xpy;
        double version;
        public ClusterImagePixel(){
            version = 0;
        }
        public void touchedBy(KPointAndCluster touching, double version){
            if(this.version != version){
                xpy = touching;
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

    public ClusterImage(int w ,int h,int margin){
        allocateImage(w, h);
        mostGeneralCenters = new ArrayList<ClusterCenter>();
    }

    public void processClusters(KeyPoint[] points ,int margin){
        mostGeneralCenters.clear();
        for(KeyPoint point: points){
            KPointAndCluster p = new KPointAndCluster(point);
            int x = (int)point.pt.x;
            int y = (int)point.pt.y;
            clusterImagePixel[x][y].touchedBy(p,version);
        }
        version ++;
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
