package model.tsobject.tsobjectparts;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;

/**
 * Created by quest on 16/3/16.
 */
public class Port {
    private String messageType;
    private ArrayList<String> buffer;

    public Port(){
        buffer = new ArrayList<String>();
    }
    public Port( String type){
        buffer = new ArrayList<String>();
        messageType = type;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public ArrayList<String> getBuffer() {
        return buffer;
    }

    public void setBuffer(ArrayList<String> buffer) {
        this.buffer = buffer;
    }


    @JsonIgnore
    public String getNextMessageFromBuffer() {
        return "";
    }
}
