package view.UI.connections;

import com.sun.awt.AWTUtilities;
import logic.Caller;
import logic.ConnectionsChecker;
import view.VObjectTS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.util.Collection;

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

    private Caller caller;
    private boolean onFocusZNum2 = false;

    public ConectionDisplayer(Caller caller){
        super();
        this.caller = caller;
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

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ConectionDisplayer.this.bringToBack();
            }
        });
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
        if(!onFocusZNum2){
            this.bringToBackOfAppWindows();
            onFocusZNum2 = true;
        }
    }

    public boolean isNotInFront(){
        return !this.isFocusableWindow();
    }

    public void bringToBackOfAppWindows(){
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                ConectionDisplayer.this.toFront();
                ConectionDisplayer.this.repaint();
                Window w = KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusedWindow();
                Collection<VObjectTS> list = caller.getViewsInArray();
                for(VObjectTS windows : list){
                    windows.toFront();
                    windows.repaint();
                }
                w.toFront();
                w.repaint();
            }
        });
    }

    public void bringToBack(){
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                ConectionDisplayer.this.toBack();
                ConectionDisplayer.this.repaint();
            }
        });
    }

    public void invalidateConnectionDisplayerWasAtFocusZNum2() {
        onFocusZNum2 = false;
    }
}
