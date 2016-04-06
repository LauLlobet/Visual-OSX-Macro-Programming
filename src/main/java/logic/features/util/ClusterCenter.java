package logic.features.util;

import org.opencv.features2d.KeyPoint;

import java.util.ArrayList;

/**
 * Created by quest on 6/4/16.
 */
public class ClusterCenter {
    private final ArrayList<ClusterCenter> mostGeneralCenters;
    java.awt.Point point;
        int numOfPoints;
        ClusterCenter mergedClusterCenter;

        public ClusterCenter(double x, double y, int num, ArrayList<ClusterCenter> mostGeneralCenters) {
            point.setLocation(x,y);
            numOfPoints = num;
            this.mostGeneralCenters = mostGeneralCenters;
            mostGeneralCenters.add(this);
        }
        public ClusterCenter(KeyPoint p, ArrayList<ClusterCenter> _mostGeneralCenter) {
            point.setLocation(p.pt.x,(float)p.pt.y);
            numOfPoints = 1;
            this.mostGeneralCenters = _mostGeneralCenter;
            mostGeneralCenters.add(this);
        }

        private boolean iammostGeneralClusterCenter() {
            return mergedClusterCenter == null;
        }

        private ClusterCenter getMostGeneralClusterCenter() {
            if (iammostGeneralClusterCenter()) {
                return this;
            }
            return mergedClusterCenter.getMostGeneralClusterCenter();
        }

        public void mergeClusterCenter(ClusterCenter sideCLuster) {
            ClusterCenter a = this.getMostGeneralClusterCenter();
            ClusterCenter b = sideCLuster.getMostGeneralClusterCenter();

            double px = point.getX();
            double py = point.getY();
            double pxn = ((a.numOfPoints * a.point.getX()) + (b.numOfPoints * b.point.getX()) / (a.numOfPoints + b.numOfPoints);
            double pyn = ((a.numOfPoints * a.point.getY()) + (b.numOfPoints * b.point.getY()) / (a.numOfPoints + b.numOfPoints);

            ClusterCenter newClusterCenter = new ClusterCenter(pxn,pyn,a.numOfPoints+b.numOfPoints,mostGeneralCenters);

            mostGeneralCenters.remove(a);
            mostGeneralCenters.remove(b);
            a.setMergedClusterCenter(newClusterCenter);
            b.setMergedClusterCenter(newClusterCenter);

        }

        public void setMergedClusterCenter(ClusterCenter mergedClusterCenter) {
            this.mergedClusterCenter = mergedClusterCenter;
        }

        public void touches(ClusterCenter clusterCenter) {
            mergeClusterCenter(clusterCenter);
        }

}
