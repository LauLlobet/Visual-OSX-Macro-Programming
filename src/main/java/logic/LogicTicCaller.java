package logic;

import Constants.TSOConstants;
import model.TSObject.ObjectTS;
import model.TSObject.TSObjectparts.OutputConnectionHubTS;
import model.TSObject.TSObjectparts.Port;
import model.TSObject.TSObjectparts.TSOConnection;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by quest on 29/3/16.
 */
public class LogicTicCaller {
    private final Caller caller;

    public LogicTicCaller(Caller caller){
        this.caller = caller;
        launch();
    }

    public void launch(){
        Thread myThread = new Thread(new Runnable() {

            public void run() {
                while(true) {
                    LogicTicCaller.this.doTic();
                    try {
                        Thread.sleep(TSOConstants.FREQ_TICS);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        myThread.start();
    }

    private void doTic() {
        doCommunicateBuffersInAllConnections();
        doTicLogicInAllObjects();
    }

    private void doTicLogicInAllObjects() {
        ArrayList<ObjectTS> list = caller.getModelsInArray();
        for(ObjectTS obj : list){
            obj.processTic();
        }
    }

    private void doCommunicateBuffersInAllConnections() {
        ArrayList<ObjectTS> list = caller.getModelsInArray();
        for(ObjectTS obj : list){
            try {
                OutputConnectionHubTS out = obj.getOutputsHub();
                doCommunicateHub(out);
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
    }

    private void doCommunicateHub(OutputConnectionHubTS out) {
        ArrayList<TSOConnection> conns = out.getConnectedToList();
        for(TSOConnection conn: conns ){
            try {
                doCommuniateConnections(conn);
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
    }

    private void doCommuniateConnections(TSOConnection conn) throws Throwable {
        ObjectTS output = caller.getModel(conn.getOriginId());
        ObjectTS input = caller.getModel(conn.getDestinyId());
        Port outBuffer = output.getOutputsHub().getPorts().get(conn.getOutPort());
        Port inBuffer = input.getInputsHub().getPorts().get(conn.getInPort());
        while(!outBuffer.doIsEmpty()){
            inBuffer.add(outBuffer.removeFirst());
        }
    }

}
