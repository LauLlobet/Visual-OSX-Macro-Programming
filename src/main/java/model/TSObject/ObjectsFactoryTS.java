package model.TSObject;

import Constants.TSOConstants;
import com.fasterxml.jackson.databind.InjectableValues;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import logic.Caller;
import logic.ConnectionsChecker;
import logic.IdGenerator;
import model.*;
import model.TSObject.TSObjectparts.InputConnectionHubTS;
import model.TSObject.TSObjectparts.OutputConnectionHubTS;
import model.TSObject.TSObjectparts.Port;
import view.ObjectTSV;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.*;

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
        input.setParentId(obj.getId());
        output.setParentId(obj.getId());
        obj.setInputsHub(input);
        obj.setOutputsHub(output);
        return obj;
    }

    public ObjectTS build(String type ) throws Throwable {
        ObjectTS newObj = null;

        if( type.startsWith(TSOConstants.DELAY_TSOBJID) ){
            newObj = DelayTS.createOne(this.idGenerator,this.connectionChecker);
        }
        if( type.startsWith(TSOConstants.SWITCH_TSOBJID) ){
            newObj = createSwitchTS();
        }
        if( type.startsWith(TSOConstants.REPETITIVECOUNTDOWN_TSOBJID) ){
            newObj = RepetitiveCountdownTS.createOne(this.idGenerator,this.connectionChecker);
        }
        if( type.startsWith(TSOConstants.SCREENPRINTER_TSOBJID) ){
            newObj = ScreenPrinterTS.createOne(this.idGenerator,this.connectionChecker);
        }
        if( type.startsWith(TSOConstants.CLICKER_TSOBJID) ){
            newObj = ClickerTS.createOne(this.idGenerator,this.connectionChecker);
        }
        if( type.startsWith(TSOConstants.MOVEMENT_DETECTOR_TSOBJID) ){
            newObj = MovementDetectorTS.createOne(this.idGenerator,this.connectionChecker);
        }
        if( type.startsWith(TSOConstants.THRESHOLD_TSOBJID) ){
            newObj = ThresholdTS.createOne(this.idGenerator,this.connectionChecker);
        }
        if( type.startsWith(TSOConstants.TEXT_MESSAGE_TSOBJID) ){
            newObj = TextMessageTS.createOne(this.idGenerator,this.connectionChecker);
        }
        if( type.startsWith(TSOConstants.BANG_TSOBJID) ){
            newObj = BangTS.createOne(this.idGenerator,this.connectionChecker);
        }
        if( type.startsWith(TSOConstants.TEXTPRINTER_TSOBJID) ){
            newObj = TextPrinterTS.createOne(this.idGenerator,this.connectionChecker);
        }
        if( type.startsWith(TSOConstants.SCREEN_FEATURES_FINDE) ){
            newObj = ScreenImageFinderTS.createOne(this.idGenerator,this.connectionChecker);
        }
        newObj.registerToMvc(caller);
        refreshUiAfter300Ms();
        return newObj;
    }

    private ObjectTS createSwitchTS() throws Throwable {
        ObjectTS newObj;
        newObj = new SwitchTS();
        newObj.setId(idGenerator.getNextId(newObj));
        newObj = setConectionHubs(newObj);
        newObj.getOutputsHub().setPorts(generatePorts(Arrays.asList(
                TSOConstants.MANY,
                TSOConstants.MBANG
        )));
        newObj.getInputsHub().setPorts(generatePorts(Arrays.asList(
                TSOConstants.MANY,
                TSOConstants.MANY
        )));
        newObj.setW(90);
        newObj.setH(60);
        newObj.setX(800);
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

///--------------------------------- SERIALIZATION

    public void storeAllModelsInFile(String file) throws Exception{
        String string = getStringsFromModel();
        System.out.println(string);
        writeIntoFile(string,file);
    }

    private void writeIntoFile(String string, String file) throws Exception {
        PrintStream out = new PrintStream(new FileOutputStream(file));
        out.print(string);
    }


    private String getStringsFromModel() throws Exception{
        ArrayList<ObjectTS> list = this.caller.getModelsInArray();
        String modelJson = serializeModel(list);
        return modelJson;

    }

    private String serializeModel(ArrayList<ObjectTS> list) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        String jsonInString = mapper.writeValueAsString(list);
        return jsonInString;
    }

    //// -_----------------- read

    public void loadFromFile(String file) throws Exception{
        String json =  readJsonFromFile(file);
        ObjectTS[] arrayList = deserializeObjectsTs(json);
        for(ObjectTS obj : arrayList){
            obj.registerToMvc(caller);
        }
        refreshUiAfter300Ms();
    }

    private void refreshUiAfter300Ms() {
        class Refresher implements Runnable {
            private final Collection<ObjectTSV> views;

            public Refresher(Collection<ObjectTSV> e){
                this.views = e;
            }
            public void run() {
                try{
                    Thread.sleep(300);
                    for(ObjectTSV view : views){
                        view.resetSize();
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }

            }
        }
        Thread myThread = new Thread(new Refresher(this.caller.getViews()));
        myThread.start();
    }

    private String readJsonFromFile(String file) throws Exception{
        String content = new Scanner(new File(file)).useDelimiter("\\Z").next();
        return content;
    }

    private ObjectTS[] deserializeObjectsTs(String jsonString) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        final InjectableValues.Std injectableValues = new InjectableValues.Std();
        injectableValues.addValue("cc", this.connectionChecker);
        injectableValues.addValue("caller", this.caller);
        mapper.setInjectableValues(injectableValues);
        ObjectTS[] result = mapper.readValue(jsonString,ObjectTS[].class);
        return result;
    }

}
