package model.tsobject;

import logic.IdGenerator;
import model.tsobject.tsobjectparts.TSOConnection;
import model.tsobject.tsobjectparts.InputConnectionHubTS;
import model.tsobject.tsobjectparts.OutputConnectionHubTS;

import java.util.ArrayList;

/**
 * Created by quest on 16/3/16.
 */
public class ObjectTS {

    private int x;
    private int y;
    private int w;
    private int h;
    private String id;
    private InputConnectionHubTS inputsHub;
    private OutputConnectionHubTS outputsHub;
    private IdGenerator idgen;

    public void TSObject(){
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

    public String getId() {
        return id;
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
    }


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
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
