package model;
import Constants.TSOConstants;
import com.fasterxml.jackson.annotation.JacksonInject;
import logic.Caller;
import logic.ConnectionsChecker;
import logic.IdGenerator;
import model.TSObject.InofensiveException;
import model.TSObject.ObjectTS;

import java.util.Arrays;

public class DelayTS extends SingleNumericFieldObjectTS {

    private String lastMessage = "";
    private long lastMessageTime = 0;
    private double countdownTime = 3000;
    public DelayTS() {
        super();
        type = TSOConstants.DELAY_TSOBJID;
        lastMessageTime = System.currentTimeMillis();
    }

    @Override
    public void processTic(){
        storeIfReceived();
        sendIfTimeIsOver();
        super.processTic();
    }

    private void storeIfReceived() {
        try {
            lastMessage = receiveMessageFromPortIfTrueFromTheLast(0,true);
            lastMessageTime = System.currentTimeMillis();
        } catch (InofensiveException ex){

        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    private void sendIfTimeIsOver() {
        countdownTime = intField1 < TSOConstants.FREQ_TICS ? 1000000000 : intField1;
        double now = System.currentTimeMillis();
        if( now - lastMessageTime > countdownTime && lastMessage.length()!= 0){
            postMessageToPort(0,lastMessage);
            lastMessage = "";
        }
    }

    public static ObjectTS createOne(IdGenerator idGenerator, ConnectionsChecker connectionChecker) throws Throwable{
        ObjectTS newObj;
        newObj = new DelayTS();
        newObj.setId(idGenerator.getNextId(newObj));
        newObj = setConectionHubs(newObj, connectionChecker);

        newObj.getInputsHub().setPorts(generateInputPorts(Arrays.asList(
                TSOConstants.MANY
        )));
        newObj.getOutputsHub().setPorts(generateOutputPorts(Arrays.asList(
                TSOConstants.MANY
        ),newObj));

        newObj.setW(40);
        newObj.setH(40);
        newObj.setX(800);
        newObj.setY(300);
        return newObj;
    }
}
