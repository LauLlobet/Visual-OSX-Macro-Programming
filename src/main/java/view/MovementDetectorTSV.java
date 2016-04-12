package view;

import logic.Caller;
import view.UI.PortPanelFactory;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by quest on 30/3/16.
 */
public class MovementDetectorTSV extends ObjectTSV {

    private final JPanel recordingPanel;
    private int tolerance = 50;
    private int maxIndex;
    private long lastRefresh = 0;

    public MovementDetectorTSV(String id, Caller mc, PortPanelFactory portPanelFactory) {
        super(id, mc, portPanelFactory);
        recordingPanel = new JPanel();
    //    recordingPanel.setBackground(new Color(255, 0, 0, 5));
        this.mainPanel.add(recordingPanel, BorderLayout.CENTER);
        //this.setTransparent();
    }

    private void setTransparent() {
        this.setBackground(new Color(0,0,0,0));
    }

    /*@Override
    public void paint(Graphics g){
        super.paint(g);
    }*/


    public JPanel doGetRecordingPane() {
        return recordingPanel;
    }

    public void setMovementIndex(int movementIndex) throws Exception {
        if(movementIndex > maxIndex){
            this.maxIndex = movementIndex;
        }

        if(timePastQuarterOfSecond()){
            int maxindexvalue = 100;
            int color = 255 * (maxIndex / maxindexvalue);
            if(color > 255){
                color = 255;
            }
            this.setTransparent();
            recordingPanel.setBackground(new Color(color, 0, 0, 10));
            recordingPanel.invalidate();
            maxIndex = 0;
        }

    }

    private boolean timePastQuarterOfSecond() {
        if( this.lastRefresh + 1000 < System.currentTimeMillis() ){
            this.lastRefresh = System.currentTimeMillis();
            return true;
        }
        return false;
    }
}

