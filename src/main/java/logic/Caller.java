package logic;

import model.tsobject.ObjectTS;
import view.VObjectTS;

import java.util.Hashtable;

/**
 * Created by quest on 17/3/16.
 */
public class Caller {

    Hashtable<String,ObjectTS> modelHash;
    Hashtable<String, VObjectTS> viewHash;

    public Caller() {
        modelHash = new Hashtable<String, ObjectTS>();
        viewHash = new Hashtable<String, VObjectTS>();
    }

    public void registerModelObject(ObjectTS model){
        modelHash.put(model.getId(),model);
    }
    public void registerViewObject(VObjectTS view){
        viewHash.put(view.getId(),view);
    }

    public void callModel(String id, int x, int old) {
        callModel(id,new Integer(x),new Integer(old));
    }

    public void callView(String id, int x, int old) {
        callView(id,new Integer(x),new Integer(old));
    }

    public void callModel(String id, Object x, Object old) {
        if(x.equals(old)){
            return;
        }
        ObjectTS obj = modelHash.get(id);
        callFunction(obj,x);
    }

    public void callView(String id, Object x, Object old) {
        if(x.equals(old)){
            return;
        }
        VObjectTS obj = viewHash.get(id);
        callFunction(obj,x);
    }

    private void callFunction(Object obj, Object x) {
        StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
        StackTraceElement e = stacktrace[2];//maybe this number needs to be corrected
        String methodName = e.getMethodName();
        System.out.println("called:"+methodName);
    }
}
