package logic;

import model.tsobject.ObjectTS;
import view.UI.connections.ConectionDisplayer;

/**
 * Created by quest on 22/3/16.
 */
public class BewteenWindowsConnectionMaker {
    Caller caller;
    private ConectionDisplayer conectionDisplayer;
    private boolean isDragging = false;

    public BewteenWindowsConnectionMaker(){
    }

    public void setCaller(Caller c){
        this.caller = c;
        conectionDisplayer = new ConectionDisplayer(caller);
    }
    public void connect(String out, String in) {;
        if(! (out.startsWith("o") && in.startsWith("i"))) {
            System.out.println("pfffff only outputs to inputs");
            return;
        }
        System.out.println("Connecting :"+out+" to "+in);
        ObjectTS toOut = getObject(in);
        int portOut =  getPort(in);
        int portIn = getPort(in);
        try {
            toOut.connectTo(portOut,out,portIn);
        } catch (Throwable throwable) {
            System.out.println("pfffff incompatible types");
            throwable.printStackTrace();
        }

    }

    private int getPort(String in) {
        return Integer.parseInt(in.substring(2,3));
    }

    private ObjectTS getObject(String id) {
        return caller.getModel(id.substring(4));
    }

    public void draggingOver(int xOrig, int yOrig, int xMouse, int yMouse) {

        this.conectionDisplayer.draggingConnection(xOrig,yOrig,xMouse,yMouse);
    }

    public void draggingEnded() {
        this.isDragging = false;
        this.conectionDisplayer.invalidateConnectionDisplayerWasAtFocusZNum2();
    }
}


