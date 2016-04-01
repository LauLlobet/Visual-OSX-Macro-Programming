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
    private final JPanel borderPanel;
    private int tolerance = 50;

    public MovementDetectorTSV(String id, Caller mc, PortPanelFactory portPanelFactory) {
        super(id, mc, portPanelFactory);

        recordingPanel = new JPanel();
        recordingPanel.setBackground(new Color(255,0,0,5));
        borderPanel = new JPanel();
        borderPanel.setLayout(new BorderLayout());

        this.getContentPane().add(borderPanel,BorderLayout.CENTER);


        final JSlider slider = new JSlider();
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider s = (JSlider) e.getSource();
                MovementDetectorTSV.this.setTolerance(s.getValue());
            }
        });
        slider.setPreferredSize(new Dimension(100,20));

        JPanel sliderpanel = new JPanel();
        sliderpanel.setLayout(new BorderLayout());

        borderPanel.add(recordingPanel,BorderLayout.NORTH);
        sliderpanel.add(slider,BorderLayout.CENTER);
        sliderpanel.setBackground(new Color(200,200,200));
        borderPanel.add(sliderpanel,BorderLayout.SOUTH);
        this.setTransparent();
    }

    private void setTransparent() {
        this.setBackground(new Color(0,0,0,0));
    }

    @Override
    public void paint(Graphics g){
        super.paint(g);
    }

    public void setTolerance(int ntolerance) {
        if(ntolerance != tolerance && modelCaller != null) {
            tolerance = ntolerance;
            try {
                modelCaller.shynchronizeMVCModel(getId(), tolerance, null);  // set model
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public JPanel doGetRecordingPane() {
        return recordingPanel;
    }
}

