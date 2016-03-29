package logic;

import Constants.TSOConstants;
import model.tsobject.tsobjectparts.*;

import java.util.ArrayList;
import java.util.Hashtable;

public class ConnectionsChecker extends Hashtable<String,ArrayList<TSOConnection>> {

    public Hashtable<String, ConnectionHubTS> connectionHubs;
    private ArrayList<TSOConnection> validConnections;
    private ArrayList<TSOConnection> toCheckLaterWhenAllLoadedList;

    public ConnectionsChecker(){
        connectionHubs = new Hashtable<String, ConnectionHubTS>();
        validConnections = new ArrayList<TSOConnection>();
        toCheckLaterWhenAllLoadedList = new ArrayList<TSOConnection>();
    }

    public void registerConnectionHub( String id,ConnectionHubTS tsoch){
        connectionHubs.put(id,tsoch);
    }



    public ArrayList<TSOConnection> getConnections(String id){
        return this.get(id);
    }

    public void ifAreConnectionsPossibleRegisterThemElseThrow(ArrayList<TSOConnection> newConns) throws Throwable{
        ArrayList<TSOConnection> oldConnections = new ArrayList<TSOConnection>(validConnections);
        try{
            for(TSOConnection conn : newConns){
                throwIfNotAllowed(conn);
                validConnections.add(conn);
            }
            return;
        }catch( Throwable e){
            System.out.println("Unrolling connection set");
            validConnections.clear();
            validConnections.addAll(oldConnections);
            throw e;
        }
    }

    public void ifAreConnectionPossibleRegisterThemElseThrow(TSOConnection newConn) throws Throwable {
            if(pointsToNonExisiting(newConn)){
                toCheckLaterWhenAllLoadedList.add(newConn);
            }
            throwIfNotAllowed(newConn);
            validConnections.add(newConn);
    }

    private boolean pointsToNonExisiting(TSOConnection newConn) {
        return false;
    }

    private void throwIfNotAllowed(TSOConnection newConn) throws Exception {
        InputConnectionHubTS input = getInput(newConn);
        OutputConnectionHubTS output = getOutput(newConn);
        throwIfportsHaveNoCompatibleMessageType(input,output,newConn);
        throwIfBuffersAreNotEmpty(input,output);
        throwIfTheresADeadLock(input,output);

    }

    private void throwIfTheresADeadLock(InputConnectionHubTS input, OutputConnectionHubTS output) {

    }

    private void throwIfBuffersAreNotEmpty(InputConnectionHubTS input, OutputConnectionHubTS output) {

    }

    private void throwIfportsHaveNoCompatibleMessageType(InputConnectionHubTS input, OutputConnectionHubTS output,TSOConnection newConn) throws Exception {
        Port inputP = input.getPorts().get(newConn.getInPort());
        Port outputP = output.getPorts().get(newConn.getOutPort());
        String typeInput = inputP.getMessageType();
        String typeOutput = outputP.getMessageType();
        if(typeInput.startsWith(TSOConstants.MANY))
            return;
        if(typeInput.startsWith(typeOutput)) {
            return;
        }
        System.out.println("incompatible types of port");
        throw new Exception("incompatible types of port");

    }

    private OutputConnectionHubTS getOutput(TSOConnection newConn) {
        return (OutputConnectionHubTS) connectionHubs.get("[o]"+newConn.getOriginId());
    }

    private InputConnectionHubTS getInput(TSOConnection newConn) {
        return (InputConnectionHubTS) connectionHubs.get("[i]"+newConn.getDestinyId());
    }



    public ArrayList<TSOConnection> getValidConnections() {
        return validConnections;
    }

}
