package model;

import Constants.TSOConstants;
import logic.ConnectionsChecker;
import logic.IdGenerator;
import model.TSObject.InofensiveException;
import model.TSObject.*;
import view.SingleFieldObjectTSV;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * Created by quest on 1/4/16.
 */
public class TextMessageTS extends SingleFieldObjectTS {

    int i = 0;

    public TextMessageTS(){
        super();
        type = TSOConstants.THRESHOLD_TSOBJID;
    }
    @Override
    public void processTic(){

        try{
            String message = receiveMessageFromPortIfTrueFromTheLast(1,true);
            setField1(message);
        }catch (Throwable e){

        }
        if(haveIReceivedABang(0)) {
            String out = field1.replace("\\\\i\\\\",""+i);
            out = out.replace("\\\\r\\\\",""+(int)(Math.floor(Math.random()*10000)));
            postMessageToPort(0,out);
            i++;
        }
        super.processTic();
    }

    @Override
    public void setField1(String field1) throws Exception{
        this.field1 = field1;
        if(registeredInMvc){caller.shyncronizeMVCView(getId(),field1,null);};

    }


    public static ObjectTS createOne(IdGenerator idGenerator, ConnectionsChecker connectionChecker) throws Throwable{
        ObjectTS newObj;
        newObj = new TextMessageTS();
        newObj.setId(idGenerator.getNextId(newObj));
        newObj = setConectionHubs(newObj, connectionChecker);
        newObj.getInputsHub().setPorts(generateInputPorts(Arrays.asList(
                TSOConstants.MBANG, TSOConstants.MANY
        )));
        newObj.getOutputsHub().setPorts(generateOutputPorts(Arrays.asList(
                TSOConstants.MANY
        ),newObj));
        newObj.setW(200);
        newObj.setH(40);
        newObj.setX(400);
        newObj.setY(600);
        return newObj;
    }
}
