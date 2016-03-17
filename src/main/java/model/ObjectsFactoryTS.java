package model;

import Constants.TSOConstants;
import logic.ConnectionsChecker;
import logic.IdGenerator;
import model.tsobject.ObjectTS;
import model.tsobject.tsobjectparts.InputConnectionHubTS;
import model.tsobject.tsobjectparts.OutputConnectionHubTS;
import model.tsobject.tsobjectparts.Port;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by quest on 17/3/16.
 */
public class ObjectsFactoryTS {

    ConnectionsChecker connectionChecker;
    IdGenerator idGenerator;
    public ObjectsFactoryTS(ConnectionsChecker cc, IdGenerator idGen) {
        this.connectionChecker = cc;
        this.idGenerator = idGen;
    }

    public ObjectTS setConectionHubs(ObjectTS obj) throws Throwable{
        InputConnectionHubTS input = new InputConnectionHubTS(this.connectionChecker);
        OutputConnectionHubTS output = new OutputConnectionHubTS(this.connectionChecker);
        obj.setInputsHub(input);
        obj.setOutputsHub(output);
        return obj;
    }

    public ObjectTS build(String type ) throws Throwable {
        ObjectTS newObj = null;

        if( type.startsWith(TSOConstants.DELAY_TSOBJID) ){
            newObj = new DelayTS();
            newObj = setConectionHubs(newObj);
            newObj.getOutputsHub().setPorts(generatePorts(Arrays.asList(
                    TSOConstants.MANY
            )));
            newObj.getInputsHub().setPorts(generatePorts(Arrays.asList(
                    TSOConstants.MANY,
                    TSOConstants.MINT
            )));
        }
        newObj.setId(idGenerator.getNextId(newObj));
        return newObj;
    }

    private ArrayList<Port> generatePorts(List<String> anymsg) {
        ArrayList<Port> ports = new ArrayList<Port>();
        for( String type : anymsg){
            ports.add(new Port(type));
        }
        return ports;
    }

}
