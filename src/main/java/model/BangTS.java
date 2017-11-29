package model;
import Constants.TSOConstants;
import logic.ConnectionsChecker;
import logic.IdGenerator;
import model.TSObject.InofensiveException;
import model.TSObject.ObjectTS;

import java.util.ArrayList;
import java.util.Arrays;

public class BangTS extends ObjectTS {

    public BangTS() {
        super();
        type = TSOConstants.BANG_TSOBJID;
    }

    public void doSendBang(String s){
        try {
            postMessageToPort(0,TSOConstants.BANG_STRING);
        }catch(Throwable e) {
            e.printStackTrace();
        }
    }

    @Override
    public void processTic(){
        try {
            String message = receiveMessageFromPortIfTrueFromTheLast(0,false);
            doSendBang("");
        } catch (InofensiveException e){

        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        super.processTic();
    }

    public static ObjectTS createOne(IdGenerator idGenerator, ConnectionsChecker connectionChecker) throws Throwable {
        ObjectTS newObj;
        newObj = new BangTS();
        newObj.setId(idGenerator.getNextId(newObj));
        newObj = setConectionHubs(newObj, connectionChecker);
        newObj.getInputsHub().setPorts(generateInputPorts(Arrays.asList(
                TSOConstants.MANY
        )));
        newObj.getOutputsHub().setPorts(generateOutputPorts(Arrays.asList(
                TSOConstants.MBANG
        ),newObj));
        newObj.setW(30);
        newObj.setH(30);
        newObj.setX(800);
        newObj.setY(600);
        return newObj;
    }
}
