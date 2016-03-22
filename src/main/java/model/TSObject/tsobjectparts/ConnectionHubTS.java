package model.tsobject.tsobjectparts;

import Constants.TSOConstants;
import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import logic.ConnectionsChecker;

import java.io.IOException;
import java.util.ArrayList;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = InputConnectionHubTS.class, name = TSOConstants.IDTSObjectInputConnectionHub),
        @JsonSubTypes.Type(value = OutputConnectionHubTS.class, name = TSOConstants.IDTSObjectOutputConnectionHub) })
public abstract class ConnectionHubTS {

    protected ArrayList<Port> ports;
    protected ConnectionsChecker connectionsChecker;
    public String type;

    public ConnectionHubTS(@JacksonInject("cc") final ConnectionsChecker connectionsChecker) throws Throwable {
        this.connectionsChecker = connectionsChecker;
        this.connectionsChecker.registerConnectionHub(this);
        ports = new ArrayList<Port>();
    }

    @JsonIgnore
    public String getNextMessageFromBuffer(int i) throws Exception{
        return ports.get(i).getNextMessageFromBuffer();
    }

    public void setPorts(ArrayList<Port> ports) {
        this.ports = ports;
    }

    public ArrayList<Port> getPorts() {
        return  ports;
    }

    public void disconnectAll(){

    }

    public Object toJSON() {
        ObjectMapper om = new ObjectMapper();
        try {
            return om.writeValueAsString(this);
        } catch (IOException e) {
            e.printStackTrace();
            return "{}";
        }
    }
}
