package model;

import Constants.TSOConstants;
import com.fasterxml.jackson.databind.InjectableValues;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import logic.Caller;
import logic.ConnectionsChecker;
import logic.IdGenerator;
import model.tsobject.ObjectTS;
import model.tsobject.tsobjectparts.InputConnectionHubTS;
import model.tsobject.tsobjectparts.OutputConnectionHubTS;
import org.junit.Test;

/**
 * Created by quest on 17/3/16.
 */
public class ObjectTsTest {
/*
    @Test
    public void testTSObjectSeralization() throws Throwable {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        final InjectableValues.Std injectableValues = new InjectableValues.Std();

        Caller c = new Caller();
        ConnectionsChecker cc = new ConnectionsChecker();
        injectableValues.addValue("cc", cc);
        injectableValues.addValue("caller", c);

        mapper.setInjectableValues(injectableValues);

        ObjectTS ihavethem = new ObjectTS();//new Caller());//c);

        ihavethem.setId("iddddd");

        ihavethem.setOutputsHub(new OutputConnectionHubTS(cc));
        ihavethem.setInputsHub(new InputConnectionHubTS(cc));

        String jsonInString = mapper.writeValueAsString(ihavethem);
        ObjectTS result = mapper.readValue(jsonInString,ObjectTS.class);
        System.out.println(result.getClass().getName());
    }*/

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


}
