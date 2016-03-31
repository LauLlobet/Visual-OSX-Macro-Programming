package view.UI;

import com.sun.awt.AWTUtilities;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Path2D;

/**
 * Created by quest on 23/3/16.
 */
public class ConectionDisplayer extends JFrame{
    float x1 = 100;
    float y1 = 100;
    float x2 = 200;
    float y2 = 200;
    float x3 = 300;
    float y3 = 100;
    float x4 = 400;
    float y4 = 200;

    private Path2D.Double path = new Path2D.Double();

    public ConectionDisplayer(){
        super();
        JPanel p = new JPanel()
        {
            protected void paintComponent ( Graphics g )
            {
                super.paintComponent(g);
                g.setColor ( Color.BLACK );
                g.fillRect ( 0, getHeight ()/4 / 2 - 10, getWidth ()/4, 20 );
                g.fillRect ( getWidth ()/4 / 2 - 10, 0, 20, getHeight ()/4 );
                ConectionDisplayer.this.paintCurve(g);
            }

            @Override
            public Dimension getPreferredSize() {

                return java.awt.Toolkit.getDefaultToolkit().getScreenSize();
            }

            public boolean contains (int x, int y )
            {
                return false;
            }
        };


        this.setUndecorated(true);
        this.setSize(java.awt.Toolkit.getDefaultToolkit().getScreenSize());
        p.setSize(new Dimension(300,300));
        p.setBackground(new Color(0, 0, 0, 0));

        this.add (p);

        this.setBackground(new Color(0, 0, 0, 0));

        AWTUtilities.setWindowOpaque ( this, false );
        AWTUtilities.setWindowOpacity ( this, 0.5f );

        this.pack ();
        this.setLocationRelativeTo ( null );
        this.setVisible ( true );
    }

    private void paintCurve(Graphics g) {


        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        double dt = Math.PI / 180;
        int w = getWidth() / 2;
        int h = getHeight() / 2;
        path.reset();
        path.moveTo(0, 0);
        path.lineTo(600, 700);
        g2d.setColor(Color.blue);
        g2d.draw(path);


        /*
        float cx1a = x1 + (x2 - x1) / 3;
        float cy1a = y1 + (y2 - y1) / 3;
        float cx1b = x2 - (x3 - x1) / 3;
        float cy1b = y2 - (y3 - y1) / 3;
        float cx2a = x2 + (x3 - x1) / 3;
        float cy2a = y2 + (y3 - y1) / 3;
        float cx2b = x3 - (x3 - x2) / 3;
        float cy2b = y3 - (y3 - y2) / 3;

        Path2D.Double path1 = new Path2D.Double();
        path1.moveTo(x1, y1);
        path1.lineTo(x3,y3);
       // path1.curveTo(cx1a, cy1a, cx1b, cy1b, x2, y2);
       // path1.curveTo(cx2a, cy2a, cx2b, cy2b, x3, y3);
        g.draw(path1);*/
    }

    public void draggingConnection(int xOrig, int yOrig, int xMouse, int yMouse) {
        if(isNotInFront()){
            bringToFront();
        }

    }

    public boolean isNotInFront(){
        return !this.isFocusableWindow();
    }
    public void bringToFront(){
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                ConectionDisplayer.this.toFront();
                ConectionDisplayer.this.repaint();
            }
        });
    }

}
