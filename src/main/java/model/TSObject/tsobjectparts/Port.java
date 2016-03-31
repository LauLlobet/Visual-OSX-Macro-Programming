package model.tsobject.tsobjectparts;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by quest on 16/3/16.
 */
public class Port {
    private String messageType;
    private LinkedList<String> buffer;
    private LinkedList<Double> activePort;

    private int beActiveTillPlus = 1000;

    public Port(){

        buffer = new LinkedList<String>();
    }

    public Port( String type){
        buffer = new LinkedList<String>();
        messageType = type;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public void postMessage(String string){
        this.buffer.add(string);
        this.activePort.removeAll(this.activePort);
        this.activePort.add((double)System.currentTimeMillis()+beActiveTillPlus);
    }

    public String readMessage(){
        return this.buffer.removeFirst();
    }

    public String readLastInMessageAndFlushTheRest(){
        String message = this.buffer.getLast();
        buffer.removeAll(buffer);
        return message;
    }

    public LinkedList<String> getBuffer() {
        return buffer;
    }

    public void setBuffer(LinkedList<String> buffer) {
        this.buffer = buffer;
    }


    @JsonIgnore
    public String getNextMessageFromBuffer() {
        return "";
    }

    public void addActivePortNotifier(LinkedList<Double> activePort) {
        this.activePort = activePort;
    }
}
