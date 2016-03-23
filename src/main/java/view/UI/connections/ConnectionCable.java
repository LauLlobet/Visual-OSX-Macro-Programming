package view.UI.connections;

import java.awt.*;
import java.awt.geom.Path2D;

/**
 * Created by quest on 23/3/16.
 */
public class ConnectionCable {


    private Path2D.Double path = new Path2D.Double();

    public float x1;
    private float x2;
    private float x3;
    private float x4;
    private float x5;
    public float x6;

    public float y1;
    private float y2;
    private float y3;
    private float y4;
    private float y5;
    public float y6;

    int distanceFromPlug = 40;
    int overtureFactor = 7;
    double proportionSet = 0.3;
    double proportionRoof = 0.2;
    
    public void setShouldBePainted(boolean shouldBePainted) {
        this.shouldBePainted = shouldBePainted;
    }

    boolean shouldBePainted = false;

    public void draw(Graphics2D g2d) {
        g2d.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.red);
        g2d.setStroke(new BasicStroke(8));
        path.reset();

        float skew = (int)((x4 - x1) * proportionSet);
        float miniskew = (int)(skew * proportionRoof);

        x2 = x1 + miniskew;
        y2 = y1 + distanceFromPlug;

        x3 = x1 + skew;
        y3 = y2;


        x4 = x6 - skew;
        y4 = y6 - distanceFromPlug;

        x5 = x6 - miniskew;
        y5 = y4;



        float cx1a = x1 + (x2 - x1) / overtureFactor;
        float cy1a = y1 + (y2 - y1) / overtureFactor;

        float cx1b = x2 - (x3 - x1) / overtureFactor;
        float cy1b = y2 - (y3 - y1) / overtureFactor;
        float cx2a = x2 + (x3 - x1) / overtureFactor;
        float cy2a = y2 + (y3 - y1) / overtureFactor;

        float cx2b = x3 - (x4 - x2) / overtureFactor;
        float cy2b = y3 - (y4 - y2) / overtureFactor;
        float cx3a = x3 + (x4 - x2) / overtureFactor;
        float cy3a = y3 + (y4 - y2) / overtureFactor;

        float cx3b = x4 - (x5 - x3) / overtureFactor;
        float cy3b = y4 - (y5 - y3) / overtureFactor;
        float cx4a = x4 + (x5 - x3) / overtureFactor;
        float cy4a = y4 + (y5 - y3) / overtureFactor;

        float cx4b = x5 - (x6 - x4) / overtureFactor;
        float cy4b = y5 - (y6 - y4) / overtureFactor;
        float cx5a = x5 + (x6 - x4) / overtureFactor;
        float cy5a = y5 + (y6 - y4) / overtureFactor;

        float cx5b = x6 - (x6 - x5) / overtureFactor;
        float cy5b = y6 - (y6 - y5) / overtureFactor;


        Path2D.Double path1 = new Path2D.Double();
        path1.moveTo(x1, y1);
        path1.curveTo(cx1a, cy1a, cx1b, cy1b, x2, y2);
        path1.curveTo(cx2a, cy2a, cx2b, cy2b, x3, y3);
        path1.curveTo(cx3a, cy3a, cx3b, cy3b, x4, y4);
        path1.curveTo(cx4a, cy4a, cx4b, cy4b, x5, y5);
        path1.curveTo(cx5a, cy5a, cx5b, cy5b, x6, y6);
        g2d.draw(path1);
    }
}
