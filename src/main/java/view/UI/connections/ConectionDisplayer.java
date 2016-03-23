package view.UI.connections;

import com.sun.awt.AWTUtilities;
import logic.ConnectionsChecker;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Path2D;

/**
 * Created by quest on 23/3/16.
 */
public class ConectionDisplayer extends JFrame{
    float x1 = 100;
    float y1 = 200;
    float x2 = 200;
    float y2 = 200;
    float x3 = 300;
    float y3 = 100;
    float x4 = 400;
    float y4 = 100;

    JPanel panel;

    private ConnectionCable currentDraggingConnection;


    public ConectionDisplayer(){
        super();
        currentDraggingConnection = new ConnectionCable();
        panel = new JPanel()
        {
            protected void paintComponent ( Graphics g )
            {
                super.paintComponent(g);
                ConectionDisplayer.this.paintCurves(g);
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
        this.setFocusableWindowState(false);

        this.setUndecorated(true);
        this.setSize(java.awt.Toolkit.getDefaultToolkit().getScreenSize());
        //p.setSize(new Dimension(300,300));
        panel.setBackground(new Color(0, 0, 0, 0));

        this.add (panel);

        this.setBackground(new Color(0, 0, 0, 0));

        AWTUtilities.setWindowOpaque ( this, false );
        AWTUtilities.setWindowOpacity ( this, 0.5f );

        this.pack ();
        this.setLocationRelativeTo ( null );
        this.setVisible ( true );
    }

    private void paintCurves(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        currentDraggingConnection.draw(g2d);

    }

    public void draggingConnection(int xOrig, int yOrig, int xMouse, int yMouse) {
        currentDraggingConnection.x1 = xOrig;
        currentDraggingConnection.y1 = yOrig;
        currentDraggingConnection.x6 = xMouse;
        currentDraggingConnection.y6 = yMouse;
        currentDraggingConnection.setShouldBePainted(true);
        panel.repaint();

    }

    public boolean isNotInFront(){
        return !this.isFocusableWindow();
    }
    public void bringToFront(){
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                ConectionDisplayer.this.toBack();
                ConectionDisplayer.this.repaint();
            }
        });
    }

}
