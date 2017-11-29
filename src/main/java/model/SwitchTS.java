package model;

import Constants.TSOConstants;
import model.TSObject.ObjectTS;

/**
 * Created by quest on 18/3/16.
 */
public class SwitchTS extends ObjectTS {

    public SwitchTS() {
        super();
        type = TSOConstants.SWITCH_TSOBJID;
    }
}
