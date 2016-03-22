package view.UI;

import Constants.TSOConstants;

import javax.swing.*;
import java.awt.*;

/**
 * Created by quest on 22/3/16.
 */
public class PortPanelFactory {
    BewteenWindowsConnectionMaker bwcm;
    JFrame frame;

    public PortPanelFactory(BewteenWindowsConnectionMaker bwcm ){
        this.bwcm = bwcm;
    }

    public JPanel create(String type,String id, JFrame frame) {
        this.frame = frame;
        JPanel panel = createPortPanel(id);
        if(type.startsWith(TSOConstants.MINT)){
            return doSetMINT(panel);
        }
        if(type.startsWith(TSOConstants.MANY)){
            return doSetMANY(panel);
        }
        if(type.startsWith(TSOConstants.MBANG)){
            return doSetMBANG(panel);
        }
        if(type.startsWith(TSOConstants.MBANG + TSOConstants.MINT)){
            JPanel p = doSetMINT(panel);
            return doSetMBANG(panel);
        }
        return panel;
    }

    private JPanel doSetMBANG(JPanel panel) {

        panel.setBackground(new Color(255,0,0,50));
        return panel;
    }

    private JPanel doSetMANY(JPanel panel) {
        panel.setBackground(new Color(255,255,0,50));
        return panel;
    }

    private JPanel doSetMINT(JPanel panel) {
        panel.setBackground(new Color(0,0,255,50));
        return panel;
    }

    private JPanel createPortPanel(String id) {
        JPanel panel = new PortPanel(this.bwcm,id);
        panel.setSize(new Dimension(20,20));
        panel.setMaximumSize(panel.getSize());
        panel.setBackground(new Color(0,0,0,255));
        return panel;
    }




}
