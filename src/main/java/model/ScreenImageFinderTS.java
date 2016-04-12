package model;
import Constants.TSOConstants;
import logic.ConnectionsChecker;
import logic.IdGenerator;
import model.tsobject.BangDebouncer;
import model.tsobject.ObjectTS;
import view.ScreenFeatureFinderTSV;
import view.UI.screencapturing.ScreenCapturer;
import view.UI.screencapturing.ScreenRegionsListener;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;


public class ScreenImageFinderTS extends ObjectTS implements ScreenRegionsListener {
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
    private String pictureToFindString;

    public ScreenImageFinderTS() {
        super();
        type = TSOConstants.SCREEN_FEATURES_FINDE;
    }

    @Override
    public void processTic() {
        postMessageToPort(0, "[" + foundX + "," + foundY + "]");
        if (pictureFound) {
            postMessageToPort(1, TSOConstants.BANG_STRING);
            pictureFound = false;
        }
        super.processTic();
    }


    public static ObjectTS createOne(IdGenerator idGenerator, ConnectionsChecker connectionChecker) throws Throwable {
        ObjectTS newObj;
        newObj = new ScreenImageFinderTS();
        newObj.setId(idGenerator.getNextId(newObj));
        newObj = setConectionHubs(newObj, connectionChecker);

        newObj.getInputsHub().setPorts(generateInputPorts(Arrays.asList(
                TSOConstants.MANY
        )));
        newObj.getOutputsHub().setPorts(generateOutputPorts(Arrays.asList(
                TSOConstants.MANY, TSOConstants.MBANG
        ), newObj));
        newObj.setW(100);
        newObj.setH(161);
        newObj.setX(900);
        newObj.setY(400);
        return newObj;
    }

    public void registerToCapturer(ScreenCapturer screenCapturer) {
        JPanel frame = ((ScreenFeatureFinderTSV) (caller.getView(this.getId()))).doGetRecordingPane();
        screenCapturer.addPanelAndListener(frame, this);

    }

    @Override
    public void newCapture(BufferedImage capture) {
        capturedImage = capture;
    }


    public void doTakePicture(String notused) {
        AssetsPersistanceAccessor.getInstance().removeAsset(pictureToFind);
        pictureToFind = capturedImage;
        String id = AssetsPersistanceAccessor.getInstance().addNewAssetToPersist(pictureToFind);
        setPictureToFind(id);
    }

    public void setPictureToFind(String npictureToFind) {
        this.pictureToFindString = npictureToFind;
        if(registeredInMvc){
            try {
                caller.shyncronizeMVCView(getId(),pictureToFindString,null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
    }

    public String getPictureToFind() {
        try {
            return AssetsPersistanceAccessor.getInstance().getIdKeyForPersistentAsset(pictureToFind);
        } catch (Exception e) {
            return TSOConstants.NOASSET;
        }
    }

}
