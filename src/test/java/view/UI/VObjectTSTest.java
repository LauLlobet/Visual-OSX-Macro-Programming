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
import view.UI.connections.ConectionDisplayer;
import view.UI.connections.ConnectionCableFactory;
import view.VObjectTS;

import java.awt.*;
import java.util.*;

/**
 * Created by quest on 15/3/16.
 */
public class VObjectTSTest {





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
        ConnectionCableFactory connectionCableFactory = new ConnectionCableFactory();
        PortPanelFactory ppf = new PortPanelFactory(bwcm,connectionCableFactory);
        ConnectionsChecker cc = new ConnectionsChecker();
        Caller caller = new Caller(ppf);
        ConectionDisplayer connectionDisplayer = new ConectionDisplayer(caller,cc, connectionCableFactory);
        bwcm.setCaller(caller,connectionDisplayer);
        IdGenerator idg = new IdGenerator("testing");
        ObjectsFactoryTS of = new ObjectsFactoryTS(cc,idg,caller);

        ObjectTS ob = of.build(TSOConstants.DELAY_TSOBJID);
        ObjectTS ob2 = of.build(TSOConstants.DELAY_TSOBJID);
        ob2.setX(500);

        Thread.sleep(200000);
        of.storeAllModelsInFile("areconnected.txt");
    }



}