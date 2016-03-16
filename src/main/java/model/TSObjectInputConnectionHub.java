package model;

import Constants.TSOConstants;
import logic.ConnectionsChecker;

/**
 * Created by quest on 16/3/16.
 */
public class TSObjectInputConnectionHub extends TSObjectConnectionHub {

    public TSObjectInputConnectionHub(ConnectionsChecker cc){
        super(cc);
        type = TSOConstants.IDTSObjectInputConnectionHub;
    }
}
