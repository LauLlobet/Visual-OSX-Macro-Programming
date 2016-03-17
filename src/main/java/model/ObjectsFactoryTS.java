package model;

import Constants.TSOConstants;
import IO.GlobalKeyListener;
import com.fasterxml.jackson.databind.InjectableValues;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import logic.Caller;
import logic.ConnectionsChecker;
import logic.IdGenerator;
import model.tsobject.ObjectTS;
import model.tsobject.tsobjectparts.InputConnectionHubTS;
import model.tsobject.tsobjectparts.OutputConnectionHubTS;
import model.tsobject.tsobjectparts.Port;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by quest on 17/3/16.
 */
public class ObjectsFactoryTS {

    private final Caller caller;
    ConnectionsChecker connectionChecker;
    IdGenerator idGenerator;
    public ObjectsFactoryTS(ConnectionsChecker cc, IdGenerator idGen, Caller caller) {
        this.connectionChecker = cc;
        this.idGenerator = idGen;
        this.caller = caller;
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
        newObj.registerToMvc(caller);
        newObj.setW(100);
        newObj.setH(161);
        newObj.setX(300);
        newObj.setY(300);
        return newObj;
    }

    private ArrayList<Port> generatePorts(List<String> anymsg) {
        ArrayList<Port> ports = new ArrayList<Port>();
        for( String type : anymsg){
            ports.add(new Port(type));
        }
        return ports;
    }

    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        final InjectableValues.Std injectableValues = new InjectableValues.Std();

        Caller c = new Caller();
        ConnectionsChecker cc = new ConnectionsChecker();
        injectableValues.addValue("cc", cc);
        injectableValues.addValue("caller", c);

        mapper.setInjectableValues(injectableValues);

        IdGenerator idGen = new IdGenerator("TestSet");
        ObjectsFactoryTS of = new ObjectsFactoryTS(cc, idGen, new Caller());


        try {

            DelayTS dts = (DelayTS) of.build(TSOConstants.DELAY_TSOBJID);

            dts = (DelayTS) of.build(TSOConstants.DELAY_TSOBJID);

            dts.setX(500);
            String jsonInString = mapper.writeValueAsString(dts);
            System.out.println(jsonInString);
            ObjectTS result = mapper.readValue(jsonInString, ObjectTS.class);
            System.out.println(result.getClass().getName());

        }catch (Throwable e){
            e.printStackTrace();
        }
    }

}
