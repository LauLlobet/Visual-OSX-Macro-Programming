package model.tsobject;

import Constants.TSOConstants;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import logic.*;
import model.DelayTS;
import model.SwitchTS;
import model.tsobject.tsobjectparts.Port;
import model.tsobject.tsobjectparts.TSOConnection;
import model.tsobject.tsobjectparts.InputConnectionHubTS;
import model.tsobject.tsobjectparts.OutputConnectionHubTS;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by quest on 16/3/16.
 */

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = DelayTS.class, name = TSOConstants.DELAY_TSOBJID),
        @JsonSubTypes.Type(value = SwitchTS.class, name = TSOConstants.SWITCH_TSOBJID) })
@JsonPropertyOrder({ "id"})
public class ObjectTS {

    private int x;
    private int y;
    private int w;
    private int h;
    public String type;
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
        registeredInMvc = true;
        caller.registerModelObject(this);
        registerAllFields();
    }

    private  void registerAllFields() throws Exception{
        for(PropertyDescriptor propertyDescriptor : Introspector.getBeanInfo(this.getClass(),new Object().getClass(),Introspector.USE_ALL_BEANINFO).getPropertyDescriptors()){
           try{
               propertyDescriptor.getWriteMethod().invoke(this,propertyDescriptor.getReadMethod().invoke(this));
           }catch( Exception e){
               System.out.println("oletuu");
           }
        }
    }

    public void processTic(){

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
        if(registeredInMvc){caller.shyncronizeMVCView(getId(),outputs.toJSON(),null);}
    }

    public void setInputsHub( InputConnectionHubTS inputs) {
        inputsHub = inputs;
        if(registeredInMvc){caller.shyncronizeMVCView(getId(),inputs.toJSON(),null);}
    }

    public void setId(String id) {
        this.id = id;
    }


    public void setX(int x) {
        int old = getX();
        this.x = x;
        if(registeredInMvc){caller.shyncronizeMVCView(getId(),x,null);}
    }
    public void setY(int y)
    {
        int old = getY();
        this.y = y;
        if(registeredInMvc){caller.shyncronizeMVCView(getId(),y,null);}
    }
    public void setW(int w) {
        int old = getW();
        this.w = w;
        if(registeredInMvc){caller.shyncronizeMVCView(getId(),w,null);}
    }

    public void setH(int h) {
        int old = getH();
        this.h = h;
        if(registeredInMvc){caller.shyncronizeMVCView(getId(),h,null);};
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

    public void destroy() {
        this.outputsHub.disconnectAll();
        this.inputsHub.disconnectAll();
        this.caller.removeTSObject(this);
    }



    // --------------- factory details

    protected static ObjectTS setConectionHubs(ObjectTS obj, ConnectionsChecker cc) throws Throwable{
        InputConnectionHubTS input = new InputConnectionHubTS(cc);
        OutputConnectionHubTS output = new OutputConnectionHubTS(cc);
        input.setParentId(obj.getId());
        output.setParentId(obj.getId());
        obj.setInputsHub(input);
        obj.setOutputsHub(output);
        return obj;
    }
    protected static ArrayList<Port> generatePorts(List<String> anymsg) {
        ArrayList<Port> ports = new ArrayList<Port>();
        for( String type : anymsg){
            ports.add(new Port(type));
        }
        return ports;
    }
}
