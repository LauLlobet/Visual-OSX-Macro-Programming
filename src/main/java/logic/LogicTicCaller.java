package logic;

import model.tsobject.ObjectTS;

import java.util.ArrayList;

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
    }

}
