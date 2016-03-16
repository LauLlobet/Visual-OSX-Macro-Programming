package model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import logic.ConnectionsChecker;

import java.util.ArrayList;

/**
 * Created by quest on 16/3/16.
 */
public class TSObjectConnectionHub {

    protected ArrayList<TSOConnection> connectedToList;
    private boolean isRegisteredToAConnectionsChecker;
    protected ArrayList<Port> ports;
    protected TSObject hubOwner;
    protected ConnectionsChecker connectionsChecker;

    public TSObjectConnectionHub() {
        isRegisteredToAConnectionsChecker = false;
    }

    public void setConnectedToList(ArrayList<TSOConnection> connectedToList) throws Exception{
        this.connectionsChecker.areConnectionsPossible(hubOwner,this,connectedToList);
        this.connectedToList = connectedToList;
    }

    public ArrayList<TSOConnection> getConnectedToList() {
        return this.connectedToList;
    }

    public void setPorts(ArrayList<Port> ports) {
        this.ports = ports;
    }

    public ArrayList<Port> getPorts() {
        return  ports;
    }

    @JsonIgnore
    public String getNextMessageFromBuffer(int i) throws Exception{
        return ports.get(i).getNextMessageFromBuffer();
    }

    public boolean isNotRegisteredToAConnectionsChecker() {
        return !isRegisteredToAConnectionsChecker;
    }

    public void registerToAConnectionsChecker(ConnectionsChecker connectionsChecker,TSObject owner) {
        this.connectionsChecker = connectionsChecker;
        this.hubOwner = owner;
        isRegisteredToAConnectionsChecker = true;
    }
}
