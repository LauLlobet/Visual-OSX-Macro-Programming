package logic;

import model.TSObject;
import model.TSObjectConnectionHub;
import model.TSObjectInputConnectionHub;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * Created by quest on 16/3/16.
 */
public class ConnectionsChecker extends Hashtable<String,ArrayList<String>> {

    public Hashtable<String, TSObjectInputConnectionHub> connectionHubs;

    public ConnectionsChecker(){
        connectionHubs = new Hashtable<String, TSObjectInputConnectionHub>();
    }

    public void registerConnectionHub(String id,TSObjectInputConnectionHub tsoch) throws Exception{
        connectionHubs.put(id,tsoch);

    }
    public void rewriteConnections( String id, ArrayList<String> connections) {
        ArrayList<String> pastConnections = this.remove(id);
        try {
            this.put(id, connections);
            verifyIfConnectionsArePossible(id,connections);
        } catch (Exception e){
            System.out.println("Not possible to rewite connections");
            e.printStackTrace();
            this.put(id,pastConnections);
        }
    }

    public ArrayList<String> getConnections(String id){
        return this.get(id);
    }

    public void verifyIfConnectionsArePossible(String id, ArrayList<String> conectedTo) throws Exception {
        //look if thera are no repeated ports
        //look if output
    }

    public void areConnectionsPossible(TSObject hubOwner, TSObjectConnectionHub tsObjectConnectionHub, ArrayList<String> connectedToList) {
    }
}
