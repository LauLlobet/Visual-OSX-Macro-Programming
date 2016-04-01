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
import sun.awt.image.ImageWatched;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.LinkedList;
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

    protected int x;
    protected int y;
    protected int w;
    protected int h;
    public String type;
    protected boolean registeredInMvc = false;

    private String id;
    private InputConnectionHubTS inputsHub;
    private OutputConnectionHubTS outputsHub;
    private IdGenerator idgen;

    private ArrayList<LinkedList<Double>> activePorts;

    protected Caller caller;
    private Boolean toBack = false;

    public ObjectTS(){
        //this.caller = caller;
        activePorts = new ArrayList<LinkedList<Double>>();
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
        for(PropertyDescriptor propertyDescriptor : Introspector.getBeanInfo(this.getClass()).getPropertyDescriptors()){
           try{
               propertyDescriptor.getWriteMethod().invoke(this,propertyDescriptor.getReadMethod().invoke(this));
           }catch( Exception e){
               if(!propertyDescriptor.getReadMethod().getName().startsWith("getClass")) {
                   e.printStackTrace();
                   System.out.println("oletuu");
               }
           }
        }
    }

    public void processTic(){
        this.setActivePorts(this.getActivePorts());
    }

    public boolean haveIReceivedABang(int port){

        try {
            String message = receiveMessageFromPortIfTrueFromTheLast(port,false);
            if(TSOConstants.BANG_STRING.equals(message)){
                return true;
            }else{
                returnReceivedMessageToBuffer(port,message,false);
                return false;
            }
        } catch (Throwable throwable) {
            return false;
        }
    }

    public String receiveMessageFromPortIfTrueFromTheLast(int port, boolean flush) throws Throwable{
        try {
            Port portbuffer = getInputsHub().getPorts().get(port);
            String message = "";
            if(flush){
                message = portbuffer.removeLast();
            }else{
                message = portbuffer.removeFirst();
            }
            return message;
        } catch (InofensiveException e){
            throw e;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            throw  throwable;
        }
    }

    public void returnReceivedMessageToBuffer(int portnum, String message, boolean flush) throws Throwable{
        Port port = getInputsHub().getPorts().get(portnum);
        if(flush){
            port.addLast(message);
        }else{
            port.addFirst(message);
        }

    }

    public void flushReceivedMessagesInPort(int portnum)throws Throwable{
        Port port = getInputsHub().getPorts().get(portnum);
        port.removeAll();
    }

    protected void postMessageToPort(int i, String s) {
        try {
            this.getOutputsHub().getPorts().get(i).postMessage(s);
        }catch(Throwable e) {
            System.out.println("port threw an exception");
        }
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
        if(registeredInMvc){
            try {
                caller.shyncronizeMVCView(getId(),outputs.toJSON(),null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void setInputsHub( InputConnectionHubTS inputs) {
        inputsHub = inputs;
        if(registeredInMvc){
            try {
                caller.shyncronizeMVCView(getId(),inputs.toJSON(),null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void setId(String id) {
        this.id = id;
    }


    public void setX(int x) {
        this.x = x;
        if(registeredInMvc){
            try {
                caller.shyncronizeMVCView(getId(),x,null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void setY(int y)
    {
        this.y = y;
        if(registeredInMvc){
            try {
                caller.shyncronizeMVCView(getId(),y,null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void setW(int w) {
        this.w = w;
        if(registeredInMvc){
            try {
                caller.shyncronizeMVCView(getId(),w,null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void setH(int h) {
        this.h = h;
        if(registeredInMvc){
            try {
                caller.shyncronizeMVCView(getId(),h,null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
    }

    public void setToBack(Boolean toBack) {
        this.toBack = toBack;
        if(registeredInMvc){
            try {
                caller.shyncronizeMVCView(getId(),toBack,null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public ArrayList<LinkedList<Double>> getActivePorts() {
        return activePorts;
    }

    public void setActivePorts(ArrayList<LinkedList<Double>> activePorts) {
        this.activePorts = activePorts;
        if(registeredInMvc){
            try {
                caller.shyncronizeMVCView(getId(),activePorts,null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
    }

    public Boolean getToBack() {
        return this.toBack;
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
    protected static ArrayList<Port> generateOutputPorts(List<String> anymsg,ObjectTS obj) {
        ArrayList<Port> ports = new ArrayList<Port>();
        int i = 0;
        for( String type : anymsg){
            Port p = new Port(type);
            LinkedList<Double> activatedPort = new LinkedList<Double>();
            activatedPort.add((double)0);
            obj.getActivePorts().add(activatedPort);
            p.addActivePortNotifier(activatedPort);
            ports.add(p);
            i++;
        }
        obj.setActivePorts(obj.getActivePorts());
        return ports;
    }

    protected static ArrayList<Port> generateInputPorts(List<String> anymsg) {
        ArrayList<Port> ports = new ArrayList<Port>();
        for( String type : anymsg){
            ports.add(new Port(type));
        }
        return ports;
    }


}
