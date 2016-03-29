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
}
