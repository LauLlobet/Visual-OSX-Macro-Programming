package model;
import Constants.TSOConstants;
import logic.ConnectionsChecker;
import logic.IdGenerator;
import model.tsobject.ObjectTS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ScreenPrinterTS extends ObjectTS {

    public String printValue;

    public ScreenPrinterTS() {
        super();
        type = TSOConstants.SCREENPRINTER_TSOBJID;
        printValue = "---";
    }

    @Override
    public void processTic(){
        try {
            LinkedList<String> buffer = getInputsHub().getPorts().get(0).getBuffer();
            if(buffer.size() == 0){
                return;
            }
            String message = buffer.removeFirst();
            setPrintValue(message);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
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

        newObj.getOutputsHub().setPorts(generatePorts(new ArrayList()));
        newObj.getInputsHub().setPorts(generatePorts(Arrays.asList(
                TSOConstants.MANY
        )));
        newObj.setW(120);
        newObj.setH(60);
        newObj.setX(900);
        newObj.setY(400);
        return newObj;
    }

}
