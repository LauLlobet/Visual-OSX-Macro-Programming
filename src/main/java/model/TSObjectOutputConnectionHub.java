package model;

import logic.ConnectionsChecker;

import java.util.ArrayList;

/**
 * Created by quest on 16/3/16.
 */
public class TSObjectOutputConnectionHub extends TSObjectConnectionHub{


    public void connectTo(String originId,int outPort, String destinyId,int inport) throws Exception{
        TSOConnection newConn = new TSOConnection(originId,outPort,destinyId,inport);
        this.connectionsChecker.put(newConn);
        this.connectedToList.add(newConn);
    }

}
