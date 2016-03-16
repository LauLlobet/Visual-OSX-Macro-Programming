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

    public void connectTo(String destinyId, String portId) throws Exception {
        this.getOutputsHub().connectTo(destinyId,portId);
    }
    public void setConectedToList(ArrayList<String> conectedTo) throws Exception {
        this.getOutputsHub().setConnectedToList(conectedTo);
    }
    public ArrayList<String> getConectedToList() {
        return this.getOutputsHub().getConnectedToList();
    }

    public TSObjectOutputConnectionHub getOutputsHub(){
        if(outputsHub == null){
            outputsHub = new TSObjectOutputConnectionHub();
        }
        return outputsHub;
    }

    public void setOutputsHub( TSObjectOutputConnectionHub outputs) {
        outputsHub = outputs;
        if(outputsHub.isNotRegisteredToAConnectionsChecker()){
            outputsHub.registerToAConnectionsChecker(connectionsChecker);
        }
    }


    public TSObjectInputConnectionHub getInputsHub(){
        if(inputsHub == null){
            inputsHub = new TSObjectInputConnectionHub();
        }
        return inputsHub;
    }

    public void setInputsHub( TSObjectInputConnectionHub inputs) {
        inputsHub = inputs;
        if(inputsHub.isNotRegisteredToAConnectionsChecker()){
            inputsHub.registerToAConnectionsChecker(connectionsChecker);
        }
    }

    public String getId() {
        if(id == null){
            id = idgen.getNextId(this);
        }
        return id;
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
