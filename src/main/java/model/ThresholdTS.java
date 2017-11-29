package model;
import Constants.TSOConstants;
import logic.ConnectionsChecker;
import logic.IdGenerator;
import model.TSObject.BangDebouncer;
import model.TSObject.ObjectTS;

import java.util.Arrays;
import java.util.NoSuchElementException;


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
               // setMaxValue(inputValue);
            }
            setValue(inputValue);
        }catch(NoSuchElementException e){
            return;
        }catch (Throwable e) {
            e.printStackTrace();
        }
        super.processTic();
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
        if(registeredInMvc){
            try {
                caller.shyncronizeMVCView(getId(),value,null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
    }
    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
        if(registeredInMvc){
            try {
                caller.shyncronizeMVCView(getId(),maxValue,null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
    }

    public int getThreshold1() {
        return threshold1;
    }

    public void setThreshold1(int threshold1) {
        this.threshold1 = threshold1;
        if(registeredInMvc){
            try {
                caller.shyncronizeMVCView(getId(),threshold1,null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
    }


    public int getThreshold2() {
        return threshold2;
    }

    public void setThreshold2(int threshold2) {
        this.threshold2 = threshold2;
        if(registeredInMvc){
            try {
                caller.shyncronizeMVCView(getId(),threshold2,null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
    }


    public static ObjectTS createOne(IdGenerator idGenerator, ConnectionsChecker connectionChecker) throws Throwable{
        ObjectTS newObj;
        newObj = new ThresholdTS();
        newObj.setId(idGenerator.getNextId(newObj));
        newObj = setConectionHubs(newObj, connectionChecker);
        newObj.getOutputsHub().setPorts(generateOutputPorts(Arrays.asList(
                TSOConstants.MBANG,
                TSOConstants.MBANG,
                TSOConstants.MBANG
        ),newObj));
        newObj.getInputsHub().setPorts(generateInputPorts(Arrays.asList(
                TSOConstants.MINT
        )));

        newObj.setW(300);
        newObj.setH(100);
        newObj.setX(900);
        newObj.setY(400);
        return newObj;
    }
}
