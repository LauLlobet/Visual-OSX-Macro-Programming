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

    public MovementDetectorTSV(String id, Caller mc, PortPanelFactory portPanelFactory) {
        super(id, mc, portPanelFactory);
        recordingPanel = new JPanel();
        recordingPanel.setBackground(new Color(255,0,0,5));
        this.mainPanel.add(recordingPanel, BorderLayout.CENTER);
        //this.setTransparent();
    }

    private void setTransparent() {
        //this.setBackground(new Color(0,0,0,0));
    }

    /*@Override
    public void paint(Graphics g){
        super.paint(g);
    }*/


    public JPanel doGetRecordingPane() {
        return recordingPanel;
    }
}

