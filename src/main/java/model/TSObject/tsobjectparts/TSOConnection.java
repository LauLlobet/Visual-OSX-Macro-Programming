package model.tsobject.tsobjectparts;

import view.UI.PortPanel;
import view.UI.PortPanelFactory;

import javax.swing.*;

/**
 * Created by quest on 16/3/16.
 */
public class TSOConnection {
    private String originId;
    private int outPort;
    private String destinyId;
    private int inPort;

    public int portNum = 10;

    public TSOConnection(String originId, int outPort, String destinyId, int inPort) {
        this.originId = originId;
        this.outPort = outPort;
        this.destinyId = destinyId;
        this.inPort = inPort;
    }

    public int getInPort() {
        return inPort;
    }

    public void setInPort(int inPort) {
        this.inPort = inPort;
    }


    public String getOriginId() {
        return originId;
    }

    public void setOriginId(String originId) {
        this.originId = originId;
    }

    public int getOutPort() {
        return outPort;
    }

    public void setOutPort(int outPort) {
        this.outPort = outPort;
    }

    public String getDestinyId() {
        return destinyId;
    }

    public void setDestinyId(String destinyId) {
        this.destinyId = destinyId;
    }

    public String doGetOriginViewIdFromConnection() {
        return PortPanelFactory.createID(this.originId,"o",this.outPort);
    }
    public String doGetDestinyViewIdFromConnection() {
        return PortPanelFactory.createID(this.destinyId,"i",this.inPort);
    }
}
