package model;
import Constants.TSOConstants;
import logic.ConnectionsChecker;
import logic.IdGenerator;
import model.tsobject.ObjectTS;
import view.MovementDetectorTSV;
import view.UI.screencapturing.ScreenCapturer;
import view.UI.screencapturing.ScreenRegionsListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;


public class MovementDetectorTS extends ObjectTS implements ScreenRegionsListener {
    int tolerance;

    BufferedImage previousImage;
    private int movementIndex;
    private BangDebouncer debouncer;

    public MovementDetectorTS() {
        super();
        type = TSOConstants.MOVEMENT_DETECTOR_TSOBJID;
        debouncer = new BangDebouncer(1500,0,this);
    }

    @Override
    public void processTic(){
        if(this.movementIndex > tolerance*1000){
            bang();
        }

    }

    public void bang(){
        debouncer.bang();
    }


    public static ObjectTS createOne(IdGenerator idGenerator, ConnectionsChecker connectionChecker) throws Throwable{
        ObjectTS newObj;
        newObj = new MovementDetectorTS();
        newObj.setId(idGenerator.getNextId(newObj));
        newObj = setConectionHubs(newObj, connectionChecker);

        newObj.getOutputsHub().setPorts(generatePorts(Arrays.asList(
                TSOConstants.MBANG
        )));
        newObj.getInputsHub().setPorts(generatePorts(Arrays.asList(
                TSOConstants.MINT
        )));
        newObj.setW(100);
        newObj.setH(161);
        newObj.setX(900);
        newObj.setY(400);
        return newObj;
    }

    public int getTolerance() {
        return tolerance;
    }

    public void setTolerance(int tolerance) {
        this.tolerance = tolerance;
        if(registeredInMvc){caller.shyncronizeMVCView(getId(),tolerance,null);};
    }

    public void registerToCapturer(ScreenCapturer screenCapturer) {
        JPanel frame = ((MovementDetectorTSV)(caller.getView(this.getId()))).doGetRecordingPane();
        screenCapturer.addPanelAndListener(frame,this);

    }

    public int bufferedImagesEqual(BufferedImage img1, BufferedImage img2) {
        int acumulatedDiference = 0;
        if (img1.getWidth() == img2.getWidth() && img1.getHeight() == img2.getHeight()) {
            for (int x = 0; x < img1.getWidth(); x++) {
                for (int y = 0; y < img1.getHeight(); y++) {
                    Color c = new Color(img1.getRGB(x,y));
                    int green = c.getGreen();
                    int blue = c.getBlue();
                    Color c2 = new Color(img2.getRGB(x,y));
                    int green2 = c2.getGreen();
                    int blue2 = c2.getBlue();
                    acumulatedDiference += (Math.abs(green - green2) + Math.abs(blue - blue2));
                }
            }
        } else {
            return -1;
        }
        return acumulatedDiference;
    }

    @Override
    public void newCapture(BufferedImage capture) {
        if(previousImage == null){
            previousImage = capture;
            return;
        }
        this.movementIndex = bufferedImagesEqual(previousImage,capture);
        previousImage = capture;

    }
}
