package view.UI;

import Constants.TSOConstants;
import logic.BewteenWindowsConnectionMaker;
import view.UI.connections.ConnectionCableFactory;

import javax.swing.*;
import java.awt.*;

/**
 * Created by quest on 22/3/16.
 */
public class PortPanelFactory {
    private final ConnectionCableFactory connectionsCableFactory;
    BewteenWindowsConnectionMaker bwcm;
    JFrame frame;

    public PortPanelFactory(BewteenWindowsConnectionMaker bwcm , ConnectionCableFactory connectionsCableFactory){
        this.bwcm = bwcm;
        this.connectionsCableFactory = connectionsCableFactory;
    }

    public PortPanel create(String type, String _outin, String _id, int numPort, JFrame frame) {
        this.frame = frame;
        String id = createID(_id,_outin,numPort);
        PortPanel panel = createPortPanel(id);
        connectionsCableFactory.addViewPortPanelToList(panel);
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

    public static String createID(String id, String outin, int numPort) {
        return outin+"["+numPort+"]"+id;
    }

    private PortPanel doSetMBANG(PortPanel panel) {

        panel.setBackground(new Color(255,0,0));
        return panel;
    }

    private PortPanel doSetMANY(PortPanel panel) {
        panel.setBackground(new Color(255,255,0));
        return panel;
    }

    private PortPanel doSetMINT(PortPanel panel) {
        panel.setBackground(new Color(0,0,255));
        return panel;
    }

    private PortPanel createPortPanel(String id) {
        PortPanel panel = new PortPanel(this.bwcm,id);
        panel.setSize(new Dimension(20,20));
        panel.setMaximumSize(panel.getSize());
        panel.setBackground(new Color(0,0,0));
        return panel;
    }




}
