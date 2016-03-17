package model.tsobject;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import logic.Caller;
import logic.IdGenerator;
import logic.ModelCaller;
import logic.ViewCaller;
import model.tsobject.tsobjectparts.TSOConnection;
import model.tsobject.tsobjectparts.InputConnectionHubTS;
import model.tsobject.tsobjectparts.OutputConnectionHubTS;

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

    private String id;
    private InputConnectionHubTS inputsHub;
    private OutputConnectionHubTS outputsHub;
    private IdGenerator idgen;

    private Caller caller;

  /*  public ObjectTS(@JacksonInject final Caller caller){
        this.caller = caller;
    }*/

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


    //-------- NOLOGIC ----------   regular getters and setters  ----------------------

    public void setConectedToList(ArrayList<TSOConnection> conectedTo) throws Throwable {
        this.getOutputsHub().setConnectedToList(conectedTo);
    }

    public ArrayList<TSOConnection> getConectedToList() throws Throwable {
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
       // caller.registerModelObject(this);
    }


    public String getId() {
        return id;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
       // caller.callModel(this.getId(),10,20);
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }
}
