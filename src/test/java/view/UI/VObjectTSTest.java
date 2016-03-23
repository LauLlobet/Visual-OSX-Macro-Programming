package view.UI;

import Constants.TSOConstants;
import logic.BewteenWindowsConnectionMaker;
import logic.Caller;
import logic.ConnectionsChecker;
import logic.IdGenerator;
import model.ObjectsFactoryTS;
import model.SwitchTS;
import model.tsobject.ObjectTS;
import model.tsobject.tsobjectparts.InputConnectionHubTS;
import model.tsobject.tsobjectparts.OutputConnectionHubTS;
import model.tsobject.tsobjectparts.Port;
import org.junit.Test;
import view.VObjectTS;

import java.awt.*;
import java.util.*;

/**
 * Created by quest on 15/3/16.
 */
public class VObjectTSTest {


    //@Test
    public void testSetConnections() throws Exception {

        BewteenWindowsConnectionMaker bwcm = new BewteenWindowsConnectionMaker();
        PortPanelFactory ppf = new PortPanelFactory(bwcm);
        Caller caller = new Caller(ppf);
        bwcm.setCaller(caller);

        VObjectTS obj = new VObjectTS("hola",caller,ppf);
        obj.setX(100);
        obj.setY(100);

        String inputHubString = "{\"inputsHub\" : {\n" +
                "    \"type\" : \"IDTSObjectInputConnectionHub\",\n" +
                "    \"ports\" : [ {\n" +
                "      \"messageType\" : \"MANY\",\n" +
                "      \"buffer\" : [ ]\n" +
                "    }, {\n" +
                "      \"messageType\" : \"MINT\",\n" +
                "      \"buffer\" : [ ]\n" +
                "    } ],\n" +
                "    \"type\" : \"IDTSObjectInputConnectionHub\"\n" +
                "  } }";

        String outputHubString = "{  \"outputsHub\" : {\n" +
                "    \"type\" : \"IDTSObjectOutputConnectionHub\",\n" +
                "    \"ports\" : [ {\n" +
                "      \"messageType\" : \"MANY\",\n" +
                "      \"buffer\" : [ ]\n" +
                "    } ],\n" +
                "    \"type\" : \"IDTSObjectOutputConnectionHub\",\n" +
                "    \"connectedToList\" : [ ]\n" +
                "  } }";

        obj.setInputsHub(inputHubString);
        obj.setOutputsHub(outputHubString);

        Thread.sleep(10000);
    }



    //@Test
    public void testSetConnectionFromModel() throws Throwable {

       /* Caller c = new Caller();
        IdGenerator idg = new IdGenerator("testing");
        ConnectionsChecker cc = new ConnectionsChecker();
        ObjectsFactoryTS of = new ObjectsFactoryTS(cc,idg,c);
        ObjectTS ob = of.build(TSOConstants.DELAY_TSOBJID);

        Thread.sleep(10000);*/
    }


    @Test
    public void testSetConnectionFromMouseEvents() throws Throwable {

        BewteenWindowsConnectionMaker bwcm = new BewteenWindowsConnectionMaker();
        PortPanelFactory ppf = new PortPanelFactory(bwcm);
        Caller c = new Caller(ppf);
        bwcm.setCaller(c);
        IdGenerator idg = new IdGenerator("testing");
        ConnectionsChecker cc = new ConnectionsChecker();
        ObjectsFactoryTS of = new ObjectsFactoryTS(cc,idg,c);

        ObjectTS ob = of.build(TSOConstants.DELAY_TSOBJID);
        ObjectTS ob2 = of.build(TSOConstants.DELAY_TSOBJID);
        ob2.setX(500);

        Thread.sleep(200000);
        of.storeAllModelsInFile("areconnected.txt");
    }



}