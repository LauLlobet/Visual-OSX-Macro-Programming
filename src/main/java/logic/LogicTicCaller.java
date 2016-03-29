package logic;

import model.tsobject.ObjectTS;
import model.tsobject.tsobjectparts.OutputConnectionHubTS;
import model.tsobject.tsobjectparts.TSOConnection;
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
            @Override
            public void run() {
                while(true) {
                    LogicTicCaller.this.doTic();
                    try {
                        Thread.sleep(40);
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
        LinkedList<String> outBuffer = output.getOutputsHub().getPorts().get(conn.getOutPort()).getBuffer();
        LinkedList<String> inBuffer = input.getInputsHub().getPorts().get(conn.getInPort()).getBuffer();
        while(!outBuffer.isEmpty()){
            inBuffer.add(outBuffer.removeFirst());
        }
    }

}
