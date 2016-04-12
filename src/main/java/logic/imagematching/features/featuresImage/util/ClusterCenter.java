package logic.imagematching.features.featuresImage.util;

import org.opencv.features2d.KeyPoint;

import java.util.ArrayList;

/**
 * Created by quest on 6/4/16.
 */
public class ClusterCenter {
    private final ArrayList<ClusterCenter> mostGeneralCenters;
    public DoublePoint point;
    int numOfPoints;
    ClusterCenter mergedClusterCenter;
    ClusterCenter lastKnownMergedClusterCenter;
    int id;

    public ClusterCenter(double x, double y, int num, ArrayList<ClusterCenter> mostGeneralCenters) {
           id = ClusterColider.getNextSequence();
           point = new DoublePoint(x,y);
            numOfPoints = num;
            this.mostGeneralCenters = mostGeneralCenters;
            mostGeneralCenters.add(this);
        }
        public ClusterCenter(KeyPoint p, ArrayList<ClusterCenter> _mostGeneralCenter) {
            id = ClusterColider.getNextSequence();
            point = new DoublePoint(p.pt.x,p.pt.y);
            numOfPoints = 1;
            this.mostGeneralCenters = _mostGeneralCenter;
            mostGeneralCenters.add(this);
        }

    public ClusterCenter(double x, double y, int num, ArrayList<ClusterCenter> mostGeneralCenters, int id) {
        this.id = id;
        point = new DoublePoint(x,y);
        numOfPoints = num;
        this.mostGeneralCenters = mostGeneralCenters;
        mostGeneralCenters.add(this);
    }

    public boolean iammostGeneralClusterCenter() {
            return mergedClusterCenter == null;
        }

        private ClusterCenter getMostGeneralClusterCenter() {
            if (iammostGeneralClusterCenter()) {
                return this;
            }
            if (lastKnownMergedClusterCenter != null && lastKnownMergedClusterCenter.iammostGeneralClusterCenter()){
                return lastKnownMergedClusterCenter;
            }
            ClusterCenter cc = mergedClusterCenter.getMostGeneralClusterCenter();
            lastKnownMergedClusterCenter = cc;
            return cc;
        }

        public void mergeClusterCenter(ClusterCenter sideCLuster) {

            //a merges b
            ClusterCenter a = this.getMostGeneralClusterCenter();
            ClusterCenter b = sideCLuster.getMostGeneralClusterCenter();

            if(b == a || b.alreadyColided(a) || a.alreadyColided(b) ){
                return;
            }


            double pxn = ((a.numOfPoints * a.point.getX()) + (b.numOfPoints * b.point.getX() )) / (a.numOfPoints + b.numOfPoints);
            double pyn = ((a.numOfPoints * a.point.getY()) + (b.numOfPoints * b.point.getY() )) / (a.numOfPoints + b.numOfPoints);



            ClusterCenter newClusterCenter = new ClusterCenter(pxn,pyn,a.numOfPoints+b.numOfPoints,mostGeneralCenters,a.id);
            mostGeneralCenters.remove(a);
            mostGeneralCenters.remove(b);
            a.setMergedClusterCenter(newClusterCenter);
            b.setMergedClusterCenter(newClusterCenter);

            ClusterColider.doColideTargetWithSource(a.id,b.id);

            b.id = a.id;
        }

    private boolean alreadyColided(ClusterCenter a) {
            return ClusterColider.areColided(this.id,a.id);
    }

    public void setMergedClusterCenter(ClusterCenter mergedClusterCenter) {
            this.mergedClusterCenter = mergedClusterCenter;
        }

    public void touches(ClusterCenter clusterCenter) {
        mergeClusterCenter(clusterCenter);
    }

}
