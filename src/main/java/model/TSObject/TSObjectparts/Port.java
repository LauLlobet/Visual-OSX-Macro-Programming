package model.TSObject.TSObjectparts;

import com.fasterxml.jackson.annotation.JsonIgnore;
import model.TSObject.InofensiveException;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by quest on 16/3/16.
 */
public class Port {
    private String messageType;
    private LinkedList<String> buffer;
    private LinkedList<Double> activePort;

    private int beActiveTillPlus = 100;

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

    public String readLastInMessageAndFlushTheRest() throws Exception{
        String message = this.buffer.getLast();
        buffer.removeAll(buffer);
        return message;
    }

    public void doSetBuffer(LinkedList<String> buffer) {
        this.buffer = buffer;
    }


    @JsonIgnore
    public String getNextMessageFromBuffer() {
        return "";
    }

    public void addActivePortNotifier(LinkedList<Double> activePort) {
        this.activePort = activePort;
    }

    public String removeLast() throws InofensiveException{
        if(buffer.size() == 0){
            throw new InofensiveException();
        }
        return buffer.removeLast();
    }

    public String removeFirst() throws InofensiveException {
        if(buffer.size() == 0){
            throw new InofensiveException();
        }
        return buffer.removeFirst();
    }

    public void addLast(String message) {
        buffer.addLast(message);
    }

    public void addFirst(String message) {
        buffer.addFirst(message);
    }

    public void removeAll() {
        buffer.removeAll(buffer);
    }

    public boolean doIsEmpty() {
        return buffer.isEmpty();
    }

    public void add(String s) {
        buffer.add(s);
    }
}
