package logic;

import model.tsobject.ObjectTS;
import view.UI.PortPanelFactory;
import view.VObjectTS;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;

/**
 * Created by quest on 17/3/16.
 */
public class Caller {

    Hashtable<String,ObjectTS> modelHash;
    Hashtable<String, VObjectTS> viewHash;
    PortPanelFactory portPanelFactory;

    public Caller(PortPanelFactory portPanelFactory) {
        modelHash = new Hashtable<String, ObjectTS>();
        viewHash = new Hashtable<String, VObjectTS>();
        this.portPanelFactory = portPanelFactory;
    }

    public void registerModelObject(ObjectTS model){
        modelHash.put(model.getId(),model);
        viewHash.put(model.getId(),new VObjectTS(model.getId(),this, this.portPanelFactory));
    }
    public void registerViewObject(VObjectTS view){
        viewHash.put(view.getId(),view);
    }


    public void shynchronizeMVCModel(String id, Object x, Object old) { //throws Exception{
        if(x.equals(old)){
            System.out.println("stoping loop trying to update model");
            return;
        }
        ObjectTS obj = modelHash.get(id);
        if(obj == null){
            //throw new Exception("View not connected to model");
            System.out.println("View not connected to model");
        } else {
            callFunction(obj, x);
        }
    }

    public void shyncronizeMVCView(String id, Object x, Object old) {
        if(x.equals(old)){
            System.out.println("stoping loop trying to update view");
            return;
        }
        VObjectTS obj = viewHash.get(id);
        callFunction(obj,x);
    }

    private void callFunction(Object obj, Object x) {
        StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
        StackTraceElement e = stacktrace[3];//maybe this number needs to be corrected
        String methodName = e.getMethodName();
        Method method;
        try {
            method = obj.getClass().getMethod(methodName, int.class);
            method.invoke(obj,x);
        } catch (Exception q) {
            try {
                method = obj.getClass().getMethod(methodName, x.getClass());
                method.invoke(obj,x);
            }catch (Exception b){
                q.printStackTrace();
                b.printStackTrace();
            }
        }
    }

    public ArrayList<ObjectTS> getModelsInArray() {
        return new ArrayList(this.modelHash.values());
    }

    public void removeTSObject(ObjectTS objectTS) {
        modelHash.remove(objectTS.getId());
        VObjectTS removed = viewHash.remove(objectTS.getId());
        removed.dispose();
    }

    public int size() {
        return modelHash.size();
    }

    public VObjectTS getView(String id) {
        return viewHash.get(id);
    }

    public ObjectTS getModel(String id) {
        return modelHash.get(id);
    }

    public Collection<VObjectTS> getViews() {
        return viewHash.values();
    }

    public Collection<VObjectTS> getViewsInArray() {
        return viewHash.values();
    }
}
