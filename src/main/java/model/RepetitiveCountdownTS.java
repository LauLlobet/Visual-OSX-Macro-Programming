package model;
import Constants.TSOConstants;
import logic.ConnectionsChecker;
import logic.IdGenerator;
import model.TSObject.ObjectTS;
import model.TSObject.TSObjectparts.InputConnectionHubTS;
import model.TSObject.TSObjectparts.OutputConnectionHubTS;
import model.TSObject.TSObjectparts.Port;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RepetitiveCountdownTS extends SingleNumericFieldObjectTS {

    private long lastBang = 0;
    private double countdownTime = 3000;

    public RepetitiveCountdownTS() {
        super();
        type = TSOConstants.REPETITIVECOUNTDOWN_TSOBJID;
        lastBang = System.currentTimeMillis();
    }

    @Override
    public void processTic(){
        countdownTime = intField1 < TSOConstants.FREQ_TICS ? 1000000000 : intField1;
        double now = System.currentTimeMillis();
        if( now - lastBang > countdownTime){
            lastBang = System.currentTimeMillis();
            bang();
        }
        super.processTic();
    }

    public void bang(){
        try {
            getOutputsHub().getPorts().get(0).postMessage(TSOConstants.BANG_STRING);
            System.out.println("rep sent");
        }catch(Throwable e) {
            System.out.println("rep countdown not connected");
        }
    }

    public static ObjectTS createOne(IdGenerator idGenerator, ConnectionsChecker connectionChecker) throws Throwable{
        ObjectTS newObj;
        newObj = new RepetitiveCountdownTS();
        newObj.setId(idGenerator.getNextId(newObj));
        newObj = setConectionHubs(newObj, connectionChecker);

        newObj.getOutputsHub().setPorts(generateOutputPorts(Arrays.asList(
                TSOConstants.MBANG
        ),newObj));
        newObj.getInputsHub().setPorts(generateInputPorts(Arrays.asList(
                TSOConstants.MINT
        )));
        newObj.setW(90);
        newObj.setH(50);
        newObj.setX(800);
        newObj.setY(300);
        return newObj;
    }
}
