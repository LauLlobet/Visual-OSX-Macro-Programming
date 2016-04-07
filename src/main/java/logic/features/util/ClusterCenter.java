package logic.features.util;

import org.opencv.features2d.KeyPoint;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by quest on 6/4/16.
 */
public class ClusterCenter {
    private final ArrayList<ClusterCenter> mostGeneralCenters;
    java.awt.Point point;
        int numOfPoints;
        ClusterCenter mergedClusterCenter;
    private Set colidedWidth;

    public ClusterCenter(double x, double y, int num, ArrayList<ClusterCenter> mostGeneralCenters) {
            point = new Point((int)x,(int)y);
            numOfPoints = num;
            this.mostGeneralCenters = mostGeneralCenters;
            mostGeneralCenters.add(this);
            colidedWidth = new HashSet();
        }
        public ClusterCenter(KeyPoint p, ArrayList<ClusterCenter> _mostGeneralCenter) {
            point = new Point((int)p.pt.x,(int)p.pt.y);
            numOfPoints = 1;
            this.mostGeneralCenters = _mostGeneralCenter;
            mostGeneralCenters.add(this);
            colidedWidth = new HashSet();
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

            if(b.alreadyColided(a) || a.alreadyColided(b) || b == a){
                return;
            }

            double px = point.getX();
            double py = point.getY();
            double pxn = ((a.numOfPoints * a.point.getX()) + (b.numOfPoints * b.point.getX() )) / (a.numOfPoints + b.numOfPoints);
            double pyn = ((a.numOfPoints * a.point.getY()) + (b.numOfPoints * b.point.getY() )) / (a.numOfPoints + b.numOfPoints);

            ClusterCenter newClusterCenter = new ClusterCenter(pxn,pyn,a.numOfPoints+b.numOfPoints,mostGeneralCenters);

            mostGeneralCenters.remove(a);
            mostGeneralCenters.remove(b);
            a.setMergedClusterCenter(newClusterCenter);
            b.setMergedClusterCenter(newClusterCenter);

            newClusterCenter.colidedWidth.addAll(b.colidedWidth);
            newClusterCenter.colidedWidth.addAll(a.colidedWidth);
            newClusterCenter.colidedWidth.add(b);
            newClusterCenter.colidedWidth.add(a);
        }

    private boolean alreadyColided(ClusterCenter a) {
        return colidedWidth.contains(a);
    }

    public void setMergedClusterCenter(ClusterCenter mergedClusterCenter) {
            this.mergedClusterCenter = mergedClusterCenter;
        }

        public void touches(ClusterCenter clusterCenter) {
            mergeClusterCenter(clusterCenter);
        }

}
