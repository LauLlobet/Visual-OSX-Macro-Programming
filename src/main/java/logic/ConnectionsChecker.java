package logic;

import model.TSOConnection;
import model.TSObjectConnectionHub;
import java.util.ArrayList;
import java.util.Hashtable;

public class ConnectionsChecker extends Hashtable<String,ArrayList<TSOConnection>> {

    public Hashtable<String, TSObjectConnectionHub> connectionHubs;
    private ArrayList<TSOConnection> validConnections;
    private ArrayList<TSOConnection> toCheckLaterWhenAllLoadedList;

    public ConnectionsChecker(){
        connectionHubs = new Hashtable<String, TSObjectConnectionHub>();
    }

    public void registerConnectionHub(TSObjectConnectionHub tsoch) throws Exception{
        //connectionHubs.add(tsoch);
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
            throwIfportsHaveNoCompatibleMessageType(newConn);
            throwIfportsAreNotEmpty(newConn);
            throwIfBuffersAreNotEmpty(newConn);
            throwIfTheresADeadLock(newConn);

    }

    private void throwIfTheresADeadLock(TSOConnection newConn) {

    }

    private void throwIfBuffersAreNotEmpty(TSOConnection newConn) {

    }

    private void throwIfportsAreNotEmpty(TSOConnection newConn) {

    }

    private void throwIfportsHaveNoCompatibleMessageType(TSOConnection newConn) {
    }

    private void throwIfNotComplete(){

    }
}
