package model;
import Constants.TSOConstants;
import logic.ConnectionsChecker;
import logic.IdGenerator;
import logic.Keyboard;
import model.tsobject.ObjectTS;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class TextPrinterTS extends FixedTextObjectTS {

    public String printValue;
    Keyboard keyboard;
    public TextPrinterTS() {
        super();
        type = TSOConstants.TEXTPRINTER_TSOBJID;
        printValue = "---";
        try{
            keyboard = new Keyboard();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void processTic(){
        try {
            String message = receiveMessageFromPortIfTrueFromTheLast(0,true);
            writeDown(message);
            setField1(message);
            flushReceivedMessagesInPort(0);
        } catch (Throwable throwable) {
        }
        super.processTic();
    }

    public void writeDown(String printValue){
        keyboard.type(printValue);

    }




    public static ObjectTS createOne(IdGenerator idGenerator, ConnectionsChecker connectionChecker) throws Throwable{
        ObjectTS newObj;
        newObj = new TextPrinterTS();
        newObj.setId(idGenerator.getNextId(newObj));
        newObj = setConectionHubs(newObj, connectionChecker);

        newObj.getOutputsHub().setPorts(generateOutputPorts(new ArrayList(),newObj));
        newObj.getInputsHub().setPorts(generateInputPorts(Arrays.asList(
                TSOConstants.MANY
        )));
        newObj.setW(90);
        newObj.setH(60);
        newObj.setX(900);
        newObj.setY(400);
        return newObj;
    }

}
