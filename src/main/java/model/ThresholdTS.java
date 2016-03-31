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
import java.awt.image.BufferedImage;
import java.util.Arrays;


public class ThresholdTS extends ObjectTS {
    private int threshold2;
    private int threshold1;
    private int maxValue;
    private int value;
    private BangDebouncer dbouncer0;
    private BangDebouncer dbouncer1;
    private BangDebouncer dbouncer2;



    public ThresholdTS() {
        super();
        type = TSOConstants.THRESHOLD_TSOBJID;
        dbouncer0 = new BangDebouncer(1000,0,this);
        dbouncer1 = new BangDebouncer(1000,1,this);
        dbouncer2 = new BangDebouncer(1000,2,this);
    }



    @Override
    public void processTic(){
        try {

            String message = this.getInputsHub().getPorts().get(0).readLastInMessageAndFlushTheRest();
            Integer inputValue = Integer.parseInt(message);
            if(threshold2<threshold1){
                if(inputValue<threshold2){
                    dbouncer0.bang();
                }else if(inputValue < threshold1){
                    dbouncer1.bang();
                }else{
                    dbouncer2.bang();
                }
            }else{
                if(inputValue<threshold1){
                    dbouncer0.bang();
                }else if(inputValue < threshold2){
                    dbouncer1.bang();
                }else{
                    dbouncer2.bang();
                }
            }
            if(maxValue < inputValue){
                setMaxValue(inputValue);
            }
            setValue(inputValue);
        }catch(Throwable e) {
            System.out.println("rep countdown not connected");
        }
        super.processTic();
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
        if(registeredInMvc){caller.shyncronizeMVCView(getId(),value,null);};
    }
    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
        if(registeredInMvc){caller.shyncronizeMVCView(getId(),maxValue,null);};
    }

    public int getThreshold1() {
        return threshold1;
    }

    public void setThreshold1(int threshold1) {
        this.threshold1 = threshold1;
        if(registeredInMvc){caller.shyncronizeMVCView(getId(),threshold1,null);};
    }


    public int getThreshold2() {
        return threshold2;
    }

    public void setThreshold2(int threshold2) {
        this.threshold2 = threshold2;
        if(registeredInMvc){caller.shyncronizeMVCView(getId(),threshold2,null);};
    }


    public static ObjectTS createOne(IdGenerator idGenerator, ConnectionsChecker connectionChecker) throws Throwable{
        ObjectTS newObj;
        newObj = new ThresholdTS();
        newObj.setId(idGenerator.getNextId(newObj));
        newObj = setConectionHubs(newObj, connectionChecker);
        newObj.getOutputsHub().setPorts(generateOutputPorts(Arrays.asList(
                TSOConstants.MINT
        ),newObj));
        newObj.getOutputsHub().setPorts(generateInputPorts(Arrays.asList(
                TSOConstants.MBANG,
                TSOConstants.MBANG,
                TSOConstants.MBANG
                )));

        newObj.setW(100);
        newObj.setH(161);
        newObj.setX(900);
        newObj.setY(400);
        return newObj;
    }
}
