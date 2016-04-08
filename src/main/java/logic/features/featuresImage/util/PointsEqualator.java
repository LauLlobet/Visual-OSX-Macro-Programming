package logic.features.featuresImage.util;

/**
 * Created by quest on 6/4/16.
 */
public class PointsEqualator {

    public class DoubleComparator
    {
        private final double tolerance;

        public DoubleComparator(double tolerance){
            this.tolerance = tolerance;
        }
        public int compare(double x, double y)
        {
            if (x < y - tolerance)
            {
                return -1;
            }
            if (x > y + tolerance)
            {
                return 1;
            }
            return 0;
        }

    }

        private DoubleComparator comp;

        public PointsEqualator(double tolerance){
            comp = new DoubleComparator(tolerance);
        }
        public int compare(double[] a, double[] b)
        {
            if (comp.compare(a[0],b[0]) == 0 &&
                    comp.compare(a[1],b[1]) == 0 )
            {
                return 0;
            }
            return -1;
        }

}
