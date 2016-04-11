package logic.features;

import logic.features.featuresImage.util.ClusterCenter;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
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
        public boolean hasContent(double newVersion){
            if(this.version == newVersion){
                return true;
            }
            return false;
        }

        public KPointAndCluster getKeyPoint(double v) {
            if(!hasContent(v)){
                return null;
            }
            return xpy;
        }
    }

    public class KPointAndCluster{
        private KeyPoint point;
        private ClusterCenter clusterCenter;

        public double[] getColor() {
            return color;
        }

        public void setColor(double[] color) {
            this.color = color;
        }

        private double[] color;
        public KPointAndCluster(KeyPoint p,double[] color){
            clusterCenter = new ClusterCenter(p,mostGeneralCenters);
            point = p;
            this.color = color;
        }
    }

    ClusterImagePixel[][]  clusterImagePixel;

    public ClusterImage(int w ,int h){
        this.width = w;
        this.height = h;
        allocateImage(w+1, h+1);
        mostGeneralCenters = new ArrayList<ClusterCenter>();
    }

    public void processClusters(KeyPoint[] points ,int margin,Mat img){
        mostGeneralCenters.clear();
        for(KeyPoint point: points){
            double[] color = img.get((int)point.pt.y+5,(int)point.pt.x);
            KPointAndCluster p = new KPointAndCluster(point, color);
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

    public float doColides(double[] suposedPoint, double[] suposedPointColor) {
        if (suposedPoint[0] < 0 || suposedPoint[1] < 0 || suposedPoint[0] > width || suposedPoint[1] > height) {
            return 0;
        }
        KPointAndCluster kpc = clusterImagePixel[(int) suposedPoint[0]][(int) suposedPoint[1]].getKeyPoint(version - 1);
        int i = 0;
        double colorsum = 0;
        if (kpc == null) {
            return 0;
        }
        try {
            for (double c : kpc.getColor()) {
                colorsum = colorsum + Math.abs(suposedPointColor[i] - c);
            }
            float max_diference = 255 * 3;
            return (max_diference - (float) colorsum) / max_diference;
        } catch (Exception e){
            int t=2;
        }
        return 0;
    }

    public ArrayList<KeyPoint> groupKeyPointsInSingleOnes(KeyPoint[] points , int margin,Mat img){
        processClusters(points,margin, img);
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
