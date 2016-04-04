package model;
import Constants.TSOConstants;
import logic.ConnectionsChecker;
import logic.IdGenerator;
import model.tsobject.BangDebouncer;
import model.tsobject.ObjectTS;
import view.MovementDetectorTSV;
import view.UI.screencapturing.ScreenCapturer;
import view.UI.screencapturing.ScreenRegionsListener;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;


public class ScreenFeaturesFinderTS extends ObjectTS implements ScreenRegionsListener {
    int tolerance;

    BufferedImage pictureToFind;
    BufferedImage ignoreInPicture;
    BufferedImage distinctInPicture;
    ArrayList<BufferedImage> distinctPicturesFound;
    BufferedImage capturedImage;

    int foundX = 0;
    int foundY = 0;
    boolean pictureFound = false;

    private int movementIndex;
    private BangDebouncer debouncer;

    public ScreenFeaturesFinderTS() {
        super();
        type = TSOConstants.SCREEN_FEATURES_FINDE;
    }

    @Override
    public void processTic(){
        postMessageToPort(0,"["+foundX+","+foundY+"]");
        if(pictureFound){
            postMessageToPort(1,TSOConstants.BANG_STRING);
            pictureFound = false;
        }
        super.processTic();
    }


    public static ObjectTS createOne(IdGenerator idGenerator, ConnectionsChecker connectionChecker) throws Throwable{
        ObjectTS newObj;
        newObj = new ScreenFeaturesFinderTS();
        newObj.setId(idGenerator.getNextId(newObj));
        newObj = setConectionHubs(newObj, connectionChecker);

        newObj.getInputsHub().setPorts(generateInputPorts(Arrays.asList(
                TSOConstants.MANY
        )));
        newObj.getOutputsHub().setPorts(generateOutputPorts(Arrays.asList(
                TSOConstants.MANY,TSOConstants.MBANG
        ),newObj));
        newObj.setW(100);
        newObj.setH(161);
        newObj.setX(900);
        newObj.setY(400);
        return newObj;
    }

    public void registerToCapturer(ScreenCapturer screenCapturer) {
        JPanel frame = ((MovementDetectorTSV)(caller.getView(this.getId()))).doGetRecordingPane();
        screenCapturer.addPanelAndListener(frame,this);

    }

    @Override
    public void newCapture(BufferedImage capture) {
        capturedImage = capture;
    }
}
