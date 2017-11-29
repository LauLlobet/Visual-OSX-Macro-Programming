package model;
import Constants.TSOConstants;
import logic.ConnectionsChecker;
import logic.IdGenerator;
import model.TSObject.ObjectTS;
import view.FixedTextObjectTSV;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ScreenPrinterTS extends FixedTextObjectTS {

    public String printValue;

    public ScreenPrinterTS() {
        super();
        type = TSOConstants.SCREENPRINTER_TSOBJID;
        printValue = "---";
    }

    @Override
    public void processTic(){
        try {
            String message = receiveMessageFromPortIfTrueFromTheLast(0,true);
            setPrintValue(message);
            setField1(message);
            flushReceivedMessagesInPort(0);
        } catch (Throwable throwable) {
        }
        super.processTic();
    }

    public void setPrintValue(String printValue){
        System.out.println("-------------PRINT VALUE---------------");
        System.out.println("---------   "+printValue+"      ----------");
        System.out.println("-----------------------------------------");
        this.printValue = printValue;
    }

    public String getPrintValue(){
        return  this.printValue;
    }



    public static ObjectTS createOne(IdGenerator idGenerator, ConnectionsChecker connectionChecker) throws Throwable{
        ObjectTS newObj;
        newObj = new ScreenPrinterTS();
        newObj.setId(idGenerator.getNextId(newObj));
        newObj = setConectionHubs(newObj, connectionChecker);

        newObj.getOutputsHub().setPorts(generateOutputPorts(new ArrayList(),newObj));
        newObj.getInputsHub().setPorts(generateInputPorts(Arrays.asList(
                TSOConstants.MANY
        )));
        newObj.setW(120);
        newObj.setH(60);
        newObj.setX(900);
        newObj.setY(400);
        return newObj;
    }

}
