package view.UI.connections;

import com.sun.awt.AWTUtilities;
import logic.Caller;
import logic.ConnectionsChecker;
import model.TSObject.TSObjectparts.TSOConnection;
import view.ObjectTSV;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
    private boolean dragging = false;
    private ConnectionsChecker connectionChecker;
    private ConnectionCableFactory connectionCableFactory;

    public ConectionDisplayer(Caller caller, ConnectionsChecker connectionChecker,ConnectionCableFactory connectionCableFactory){
        super();
        this.caller = caller;
        this.connectionChecker = connectionChecker;
        this.connectionCableFactory = connectionCableFactory;
        currentDraggingConnection = new ConnectionCable();
        this.setAlwaysOnTop( true );
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

        refreshWhile();
    }

    private void paintCurves(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
       if(dragging){
           currentDraggingConnection.draw(g2d);
       }
        drawAllEstablishedConnections(g2d);

    }

    private void drawAllEstablishedConnections(Graphics2D g2d) {
        for(TSOConnection conn: connectionChecker.getValidConnections()){
            ConnectionCable connectionCable =  connectionCableFactory.create(conn);
            connectionCable.draw(g2d);
        }
    }

    public void draggingConnection(int xOrig, int yOrig, int xMouse, int yMouse) {
        currentDraggingConnection.x1 = xOrig;
        currentDraggingConnection.y1 = yOrig;
        currentDraggingConnection.x6 = xMouse;
        currentDraggingConnection.y6 = yMouse;
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
            public void run() {
                ConectionDisplayer.this.toFront();
                ConectionDisplayer.this.repaint();
                Window w = KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusedWindow();
                Collection<ObjectTSV> list = caller.getViewsInArray();
                for(ObjectTSV windows : list){
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
            public void run() {
                ConectionDisplayer.this.toBack();
                ConectionDisplayer.this.repaint();
            }
        });
    }

    public void refreshWhile(){
        Thread myThread = new Thread(new Runnable() {
            public void run() {
                while(true) {
                    java.awt.EventQueue.invokeLater(new Runnable() {
                        public void run() {
                            ConectionDisplayer.this.repaint();
                        }
                    });
                    try {
                        Thread.sleep(40);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        myThread.start();
    }

    public void invalidateConnectionDisplayerWasAtFocusZNum2() {
        onFocusZNum2 = false;
    }

    public void setDragging(boolean dragging) {
        this.dragging = dragging;
        if(dragging == false) {
            panel.repaint();
        }
    }
}
