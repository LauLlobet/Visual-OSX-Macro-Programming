package view.UI;

import jdk.nashorn.internal.codegen.CompilerConstants;
import logic.Caller;
import model.tsobject.ObjectTS;

/**
 * Created by quest on 22/3/16.
 */
public class BewteenWindowsConnectionMaker {
    Caller caller;

    BewteenWindowsConnectionMaker(){
    }

    public void setCaller(Caller c){
        this.caller = c;
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
}


