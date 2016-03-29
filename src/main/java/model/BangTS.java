package model;
import Constants.TSOConstants;
import model.tsobject.ObjectTS;

public class BangTS extends ObjectTS {

    public BangTS() {
        super();
        type = TSOConstants.BANG_TSOBJID;
    }

    public void bang(){
        try {
            getOutputsHub().getPorts().get(0).postMessage("bang");
        }catch(Throwable e) {
            e.printStackTrace();
        }
    }

}
