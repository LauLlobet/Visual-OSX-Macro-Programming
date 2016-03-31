package model;

import model.tsobject.ObjectTS;

/**
 * Created by quest on 30/3/16.
 */
public class BangDebouncer {
    private  double milliseconds;
    private  double lastBangMilliseconds = 0;

    private final int port;
    private final ObjectTS objectTS;

    public BangDebouncer(int milliseconds, int port, ObjectTS objectTS) {
        this.milliseconds = (double)milliseconds;
        this.port = port;
        this.objectTS = objectTS;
    }

    public void bang(){
        double now =  System.currentTimeMillis();
        if(lastBangMilliseconds + milliseconds < now){
            doBang();
            lastBangMilliseconds = now;
        }
    }

    private void doBang() {
        try {
            objectTS.getOutputsHub().getPorts().get(this.port).postMessage("bang");
            System.out.println("bang sent from movment detector"+System.currentTimeMillis());
        }catch(Throwable e) {
            System.out.println("rep countdown not connected");
        }
    }
}
