package logic;

import model.tsobject.ObjectTS;
import view.UI.connections.ConectionDisplayer;

/**
 * Created by quest on 22/3/16.
 */
public class BewteenWindowsConnectionMaker {
    Caller caller;
    private ConectionDisplayer conectionDisplayer;

    public BewteenWindowsConnectionMaker(){
    }

    public void setCaller(Caller c, ConectionDisplayer conectionDisplayer){
        this.caller = c;
        this.conectionDisplayer = conectionDisplayer;
    }
    public void connect(String out, String in) {;
        if(! (out.startsWith("o") && in.startsWith("i"))) {
            System.out.println("pfffff only outputs to inputs");
            return;
        }
        System.out.println("Connecting :"+out+" to "+in);
        ObjectTS toOut = getObject(out);
        int portOut =  getPort(out);
        int portIn = getPort(in);
        try {
            toOut.connectTo(portOut,getEmbeddedId(in),portIn);
        } catch (Throwable throwable) {
            System.out.println("pfffff incompatible types");
        }
    }

    private int getPort(String in) {
        return Integer.parseInt(in.substring(2,3));
    }

    private ObjectTS getObject(String id) {
        return caller.getModel(getEmbeddedId(id));
    }

    private String getEmbeddedId(String oid){
        return oid.substring(4);
    }



    public void draggingOver(int xOrig, int yOrig, int xMouse, int yMouse) {
        this.conectionDisplayer.setDragging(true);
        this.conectionDisplayer.draggingConnection(xOrig,yOrig,xMouse,yMouse);
    }

    public void draggingEnded() {
        this.conectionDisplayer.setDragging(false);
        this.conectionDisplayer.invalidateConnectionDisplayerWasAtFocusZNum2();
    }
}


