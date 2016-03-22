package view.UI;

import Constants.TSOConstants;
import view.VObjectTS;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by quest on 21/3/16.
 */
class ScreenCapturer implements Runnable {
    private Robot robot;
    BufferedImage image;
    ArrayList<ListenerAndPanel> listeners;

    public class ListenerAndPanel {
        public JPanel panel;
        public ScreenRegionsListener listener;
        public ListenerAndPanel(JPanel panel, ScreenRegionsListener listener){
            this.panel = panel;
            this.listener = listener;
        }
    }

    public ScreenCapturer() throws Exception {
        robot = new Robot();
        listeners = new ArrayList<ListenerAndPanel>();
    }

    public void addPanelAndListener(JPanel panel, ScreenRegionsListener listener) {
        listeners.add(new ListenerAndPanel(panel,listener));
    }

    public void removeListener(JPanel panel) {
        for (ListenerAndPanel lp : listeners){
            if(lp.panel.equals(panel)){
                listeners.remove(lp);
            }
        }
    }

    public void run() {
        try{
            while(true) {
                Thread.sleep(TSOConstants.FREQ_SCREEN_CAPTURE_MS);
                image = robot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
                deliverScreenCaptureRegions();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void deliverScreenCaptureRegions() {
        for(ListenerAndPanel listener : listeners){
            final BufferedImage dst = getBufferedImage(listener.panel);
            listener.listener.newCapture(dst);
        }
    }

    private BufferedImage getBufferedImage(JPanel listener) {
        int x = (int)listener.getLocationOnScreen().getX();
        int y = (int)listener.getLocationOnScreen().getY();
        int w = (int)listener.getSize().getWidth();
        int h = (int)listener.getSize().getHeight();
        final BufferedImage dst = image.getSubimage(x,y,w,h);
        return dst;
    }
}


