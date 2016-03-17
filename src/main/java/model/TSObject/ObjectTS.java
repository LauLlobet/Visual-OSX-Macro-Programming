package model.tsobject;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import logic.*;
import model.tsobject.tsobjectparts.TSOConnection;
import model.tsobject.tsobjectparts.InputConnectionHubTS;
import model.tsobject.tsobjectparts.OutputConnectionHubTS;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.ArrayList;

/**
 * Created by quest on 16/3/16.
 */

@JsonPropertyOrder({ "id"})
public class ObjectTS {

    private int x;
    private int y;
    private int w;
    private int h;
    boolean registeredInMvc = false;

    private String id;
    private InputConnectionHubTS inputsHub;
    private OutputConnectionHubTS outputsHub;
    private IdGenerator idgen;

    private Caller caller;

    public ObjectTS(){
        //this.caller = caller;
    }

    public void connectTo(int outPort,String destinyId, int inPort) throws Throwable {
        TSOConnection conn = new TSOConnection(this.getId(),outPort,destinyId,inPort);
        this.getOutputsHub().connectTo(conn);
    }

    public OutputConnectionHubTS getOutputsHub() throws Throwable{
        return outputsHub;
    }

    public InputConnectionHubTS getInputsHub() throws Throwable{
        return inputsHub;
    }

    public void registerToMvc(Caller caller) throws Exception{
        this.caller = caller;
        caller.registerModelObject(this);
        registerAllFields();
    }

    private  void registerAllFields() throws Exception{
        for(PropertyDescriptor propertyDescriptor : Introspector.getBeanInfo(this.getClass(),new Object().getClass(),Introspector.USE_ALL_BEANINFO).getPropertyDescriptors()){
           try{
               propertyDescriptor.getWriteMethod().invoke(this,propertyDescriptor.getReadMethod().invoke(this));
           }catch( Exception e){
               System.out.println("ole");
           }
        }
        registeredInMvc = true;
    }

    //-------- NOLOGIC ----------   regular getters and setters  ----------------------

    public void doSetConectedToList(ArrayList<TSOConnection> conectedTo) throws Throwable {
        this.getOutputsHub().setConnectedToList(conectedTo);
    }

    public ArrayList<TSOConnection> doGetConectedToList() throws Throwable {
        return this.getOutputsHub().getConnectedToList();
    }

    public void setOutputsHub( OutputConnectionHubTS outputs) {
        outputsHub = outputs;
    }

    public void setInputsHub( InputConnectionHubTS inputs) {
        inputsHub = inputs;
    }

    public void setId(String id) {
        this.id = id;
    }


    public void setX(int x) {
        int old = getX();
        this.x = x;
        if(registeredInMvc){caller.callView(getId(),x,old);}
    }
    public void setY(int y)
    {
        int old = getY();
        this.y = y;
        if(registeredInMvc){caller.callView(getId(),y,old);}
    }
    public void setW(int w) {
        int old = getW();
        this.w = w;
        if(registeredInMvc){caller.callView(getId(),w,old);}
    }

    public void setH(int h) {
        int old = getH();
        this.h = h;
        if(registeredInMvc){caller.callView(getId(),h,old);};
    }
    

    public String getId() {
        return id;
    }

    public int getX() {
        return x;
    }


    public int getY() {
        return y;
    }

    public int getW() {
        return w;
    }
    public int getH() {
        return h;
    }

}
