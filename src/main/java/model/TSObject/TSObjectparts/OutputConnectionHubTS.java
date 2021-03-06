package model.TSObject.TSObjectparts;

import Constants.TSOConstants;
import com.fasterxml.jackson.annotation.JacksonInject;
import logic.ConnectionsChecker;

import java.util.ArrayList;

public class OutputConnectionHubTS extends ConnectionHubTS {


    protected ArrayList<TSOConnection> connectedToList;
    public OutputConnectionHubTS(@JacksonInject("cc") final ConnectionsChecker cc) throws Throwable {
        super(cc);
        type = TSOConstants.IDTSObjectOutputConnectionHub;
        connectedToList = new ArrayList<TSOConnection>();
    }

    public void setParentId(String id){
        super.setParentId("[o]"+id);
    }

    public void connectTo(TSOConnection newConn) throws  Throwable{
        this.connectionsChecker.ifAreConnectionPossibleRegisterThemElseThrow(newConn);
        this.connectedToList.add(newConn);
    }
    public void setConnectedToList(ArrayList<TSOConnection> connections) throws Throwable{
        this.connectionsChecker.ifAreConnectionsPossibleRegisterThemElseThrow(connections);
        this.connectedToList = connections;
    }

    public ArrayList<TSOConnection> getConnectedToList() {
        return this.connectedToList;
    }

}
