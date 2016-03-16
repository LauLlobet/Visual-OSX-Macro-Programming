package model;

import Constants.TSOConstants;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import logic.ConnectionsChecker;

import java.util.ArrayList;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = TSObjectInputConnectionHub.class, name = TSOConstants.IDTSObjectInputConnectionHub),
        @JsonSubTypes.Type(value = TSObjectOutputConnectionHub.class, name = TSOConstants.IDTSObjectOutputConnectionHub) })
public class TSObjectConnectionHub {

    protected ArrayList<TSOConnection> connectedToList;
    private boolean isRegisteredToAConnectionsChecker;
    protected ArrayList<Port> ports;
    protected String ownerID;
    protected ConnectionsChecker connectionsChecker;
    public String type = TSOConstants.IDTSObjectConnectionHub;

    public TSObjectConnectionHub(ConnectionsChecker connectionsChecker) throws Throwable {
        this.connectionsChecker = connectionsChecker;
        this.connectionsChecker.registerConnectionHub(this);
    }

    @JsonIgnore
    public String getNextMessageFromBuffer(int i) throws Exception{
        return ports.get(i).getNextMessageFromBuffer();
    }

    public void setConnectedToList(ArrayList<TSOConnection> connections) throws Throwable{
        this.connectionsChecker.ifAreConnectionsPossibleRegisterThemElseThrow(connections);
        this.connectedToList = connections;
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
}
