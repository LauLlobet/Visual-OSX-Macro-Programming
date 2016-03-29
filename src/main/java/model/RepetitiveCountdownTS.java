package model;
import Constants.TSOConstants;
import logic.ConnectionsChecker;
import logic.IdGenerator;
import model.tsobject.ObjectTS;
import model.tsobject.tsobjectparts.InputConnectionHubTS;
import model.tsobject.tsobjectparts.OutputConnectionHubTS;
import model.tsobject.tsobjectparts.Port;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RepetitiveCountdownTS extends ObjectTS {

    private long lastBang = 0;
    private double countdownTime = 3000;

    public RepetitiveCountdownTS() {
        super();
        type = TSOConstants.REPETITIVECOUNTDOWN_TSOBJID;
        lastBang = System.currentTimeMillis();
    }

    @Override
    public void processTic(){
        double now = System.currentTimeMillis();
        if(lastBang - now > countdownTime){
            lastBang = System.currentTimeMillis();
            bang();
        }

    }

    public void bang(){
        try {
            getOutputsHub().getPorts().get(0).postMessage("bang");
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

        newObj.getOutputsHub().setPorts(generatePorts(Arrays.asList(
                TSOConstants.MBANG
        )));
        newObj.getInputsHub().setPorts(generatePorts(Arrays.asList(
                TSOConstants.MINT
        )));
        newObj.setW(90);
        newObj.setH(50);
        newObj.setX(800);
        newObj.setY(300);
        return newObj;
    }
}
