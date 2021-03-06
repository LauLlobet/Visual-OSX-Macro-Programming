package view.UI.screencapturing;

import Constants.TSOConstants;
import logic.Caller;
import model.TSObject.ObjectTS;
import view.ObjectTSV;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by quest on 21/3/16.
 */
public class ScreenCapturer implements Runnable {
    private Robot robot;
    BufferedImage image;
    ArrayList<ListenerAndPanel> listeners;
    private ScreenRegionsListener fullScreenListener;


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

    public void setFullScreenListener(ScreenRegionsListener srl) {
        fullScreenListener = srl;
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
                System.out.println("capturing"+System.currentTimeMillis());
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void deliverScreenCaptureRegions() {
        for(ListenerAndPanel listener : listeners){
            try{
                final BufferedImage dst = getBufferedImage(listener.panel);
                listener.listener.newCapture(dst);
            } catch (Exception e){
                int pp = 3;
            }
        }
        if(fullScreenListener != null){
            fullScreenListener.newCapture(image);
        }
    }

    private BufferedImage getBufferedImage(JPanel listener) throws IllegalComponentStateException {
        int x = (int)listener.getLocationOnScreen().getX();
        int y = (int)listener.getLocationOnScreen().getY();
        int w = (int)listener.getSize().getWidth();
        int h = (int)listener.getSize().getHeight();
        final BufferedImage dst = image.getSubimage(x,y,w,h);
        return dst;
    }
}


