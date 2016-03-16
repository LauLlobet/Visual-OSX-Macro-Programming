package model;

import logic.ConnectionsChecker;
import logic.IdGenerator;

import java.util.ArrayList;

/**
 * Created by quest on 16/3/16.
 */
public class TSObject {

    private int x;
    private int y;
    private int w;
    private int h;
    private String id;
    private TSObjectInputConnectionHub inputsHub;
    private TSObjectOutputConnectionHub outputsHub;
    private IdGenerator idgen;
    ConnectionsChecker connectionsChecker;

    public void TSObject(IdGenerator idgen, ConnectionsChecker connectionsChecker){
        this.idgen = idgen;
        this.connectionsChecker = connectionsChecker;
    }

    public void connectTo(int outPort,String destinyId, int inPort) throws Throwable {
        TSOConnection conn = new TSOConnection(this.getId(),outPort,destinyId,inPort);
        this.getOutputsHub().connectTo(conn);
    }

    public TSObjectOutputConnectionHub getOutputsHub() throws Throwable{
        if(outputsHub == null){
            outputsHub = new TSObjectOutputConnectionHub(connectionsChecker);
        }
        return outputsHub;
    }

    public TSObjectInputConnectionHub getInputsHub(){
        if(inputsHub == null){
            inputsHub = new TSObjectInputConnectionHub(connectionsChecker);
        }
        return inputsHub;
    }

    public String getId() {
        if(id == null){
            id = idgen.getNextId(this);
        }
        return id;
    }


    //-------- NOLOGIC ----------   regular getters and setters  ----------------------

    public void setConectedToList(ArrayList<TSOConnection> conectedTo) throws Throwable {
        this.getOutputsHub().setConnectedToList(conectedTo);
    }

    public ArrayList<TSOConnection> getConectedToList() throws Throwable {
        return this.getOutputsHub().getConnectedToList();
    }

    public void setOutputsHub( TSObjectOutputConnectionHub outputs) {
        outputsHub = outputs;
    }

    public void setInputsHub( TSObjectInputConnectionHub inputs) {
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
