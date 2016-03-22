package model;

import Constants.TSOConstants;
import logic.Caller;
import logic.ConnectionsChecker;
import logic.IdGenerator;
import model.tsobject.ObjectTS;
import org.junit.Test;
import view.VObjectTS;

import java.awt.*;

import static org.junit.Assert.*;

/**
 * Created by quest on 18/3/16.
 */
public class ObjectsFactoryTSTest {
   /* @Test
    public void testStoreToFile() throws Throwable {

        String fileName = "temp.tsp";
        Caller c = new Caller();
        ConnectionsChecker cc = new ConnectionsChecker();
        IdGenerator idGen = new IdGenerator("TestSet");
        ObjectsFactoryTS of = new ObjectsFactoryTS(cc,idGen, c);
        ObjectTS dts =  of.build(TSOConstants.DELAY_TSOBJID);
        dts.setX(300);
        dts =  of.build(TSOConstants.DELAY_TSOBJID);
        dts.setX(400);
        dts =  of.build(TSOConstants.SWITCH_TSOBJID);
        dts.setY(500);
        dts =  of.build(TSOConstants.DELAY_TSOBJID);
        dts.setX(40);

        of.storeAllModelsInFile(fileName);
        String content = new String(Files.readAllBytes(Paths.get(fileName)));
        System.out.println(content);

    }*/

    @Test
    public void testReadFromToFile() throws Throwable {
/*
        String fileName = "temp.tsp";
        Caller c = new Caller();
        ConnectionsChecker cc = new ConnectionsChecker();
        IdGenerator idGen = new IdGenerator("TestSet");
        ObjectsFactoryTS of = new ObjectsFactoryTS(cc,idGen, c);

        try {
            of.loadFromFile(fileName);
            ObjectTS obj = c.getModelsInArray().get(0);
            System.out.println("inintw:" + obj.getW());
           // Thread.sleep(1700);
            assertNotEquals(obj.getW(), 400);
            of.storeAllModelsInFile(fileName);
        } catch(Exception e) {
            e.printStackTrace();
            ObjectTS dts1 = of.build(TSOConstants.DELAY_TSOBJID);
            dts1.setX(100);
            ObjectTS dts2 = of.build(TSOConstants.DELAY_TSOBJID);
            dts2.setX(200);
            ObjectTS dts3 = of.build(TSOConstants.SWITCH_TSOBJID);
            dts3.setX(300);
            ObjectTS dts31 = of.build(TSOConstants.SWITCH_TSOBJID);
            dts31.setX(400);
            ObjectTS dts4 = of.build(TSOConstants.DELAY_TSOBJID);
            dts4.setX(500);
            ObjectTS dts5 = of.build(TSOConstants.DELAY_TSOBJID);
            dts5.setX(600);
            ObjectTS dts6 = of.build(TSOConstants.DELAY_TSOBJID);
            dts6.setX(700);
            of.storeAllModelsInFile(fileName);
        }
        Thread.sleep(15700);*/
    }

/*
    @Test
    public void testTSFactorySeralization() throws Throwable {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        final InjectableValues.Std injectableValues = new InjectableValues.Std();

        Caller c = new Caller();
        ConnectionsChecker cc = new ConnectionsChecker();
        injectableValues.addValue("cc", cc);
        injectableValues.addValue("caller", c);

        mapper.setInjectableValues(injectableValues);

        IdGenerator idGen = new IdGenerator("TestSet");
        ObjectsFactoryTS of = new ObjectsFactoryTS(cc,idGen, new Caller());


        DelayTS dts = (DelayTS) of.build(TSOConstants.DELAY_TSOBJID);

        String jsonInString = mapper.writeValueAsString(dts);
        System.out.println(jsonInString);
        ObjectTS result = mapper.readValue(jsonInString,ObjectTS.class);
        System.out.println(result.getClass().getName());
    }
    */
}
