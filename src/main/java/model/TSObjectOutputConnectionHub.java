package model;

import Constants.TSOConstants;
import logic.ConnectionsChecker;

public class TSObjectOutputConnectionHub extends TSObjectConnectionHub{

    public TSObjectOutputConnectionHub(ConnectionsChecker cc) throws Throwable {
        super(cc);
        type = TSOConstants.IDTSObjectOutputConnectionHub;
    }
    public void connectTo(TSOConnection newConn) throws  Throwable{
        this.connectionsChecker.ifAreConnectionPossibleRegisterThemElseThrow(newConn);
        this.connectedToList.add(newConn);
    }

}
