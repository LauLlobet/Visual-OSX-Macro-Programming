package logic;

import model.tsobject.ObjectTS;
import view.InvalidFieldException;
import view.UI.PortPanelFactory;
import view.ObjectTSV;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;

/**
 * Created by quest on 17/3/16.
 */
public class Caller {

    Hashtable<String,ObjectTS> modelHash;
    Hashtable<String, ObjectTSV> viewHash;
    PortPanelFactory portPanelFactory;

    public Caller(PortPanelFactory portPanelFactory) {
        modelHash = new Hashtable<String, ObjectTS>();
        viewHash = new Hashtable<String, ObjectTSV>();
        this.portPanelFactory = portPanelFactory;
    }

    public void registerModelObject(ObjectTS model){
        modelHash.put(model.getId(),model);
        viewHash.put(model.getId(),createViewFromModel(model));
    }

    private ObjectTSV createViewFromModel(ObjectTS model) {
        try {
            return makeVObjectTs(model.getClass().getName(),model);
        } catch (Exception e) {
            System.out.println("Could not instance class:"+model.getClass().getName()+"V");
            return new ObjectTSV(model.getId(),this, this.portPanelFactory);
        }
    }

    public ObjectTSV makeVObjectTs(String classname, ObjectTS model) throws Exception{
        Class c = Class.forName(classname.replace("model","view")+"V");
        ObjectTSV product = (ObjectTSV)c.getDeclaredConstructor(String.class,Caller.class,PortPanelFactory.class).newInstance(model.getId(),this, this.portPanelFactory);
        return product;
    }

    public void registerViewObject(ObjectTSV view){
        viewHash.put(view.getId(),view);
    }


    public void shynchronizeMVCModel(String id, Object x, Object old) throws Exception{
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

    public void shyncronizeMVCView(String id, Object x, Object old)throws  Exception {
        if(x.equals(old)){
            System.out.println("stoping loop trying to update view");
            return;
        }
        ObjectTSV obj = viewHash.get(id);
        callFunction(obj,x);
    }

    private void callFunction(Object obj, Object x) throws Exception {
        StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
        StackTraceElement e = stacktrace[3];//maybe this number needs to be corrected
        String methodName = e.getMethodName();
        Method method;
        try {
            method = obj.getClass().getMethod(methodName, int.class);
            method.invoke(obj,x);
        } catch (Exception q) {
            if( q.getCause() instanceof InvalidFieldException){
                throw (InvalidFieldException)q.getCause();
            }
            try {
                method = obj.getClass().getMethod(methodName, x.getClass());
                method.invoke(obj, x);
            }catch (Exception b){
                if( b.getCause() instanceof InvalidFieldException){
                    throw (InvalidFieldException)b.getCause();
                }
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
        ObjectTSV removed = viewHash.remove(objectTS.getId());
        removed.dispose();
    }

    public int size() {
        return modelHash.size();
    }

    public ObjectTSV getView(String id) {
        return viewHash.get(id);
    }

    public ObjectTS getModel(String id) {
        return modelHash.get(id);
    }

    public Collection<ObjectTSV> getViews() {
        return viewHash.values();
    }

    public Collection<ObjectTSV> getViewsInArray() {
        return viewHash.values();
    }
}
