package model;

import Constants.TSOConstants;
import com.fasterxml.jackson.databind.InjectableValues;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
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

    @Test
    public void testTSObjectSeralization() throws Throwable {
        ConnectionsChecker cc = new ConnectionsChecker();
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        final InjectableValues.Std injectableValues = new InjectableValues.Std();
        injectableValues.addValue(ConnectionsChecker.class, cc);
        mapper.setInjectableValues(injectableValues);

        ObjectTS ihavethem = new ObjectTS();
        ihavethem.setOutputsHub(new OutputConnectionHubTS(cc));
        ihavethem.setInputsHub(new InputConnectionHubTS(cc));

        String jsonInString = mapper.writeValueAsString(ihavethem);
        System.out.println(jsonInString);

        ObjectTS result = mapper.readValue(jsonInString,ObjectTS.class);
        System.out.println(result.getClass().getName());
    }

    @Test
    public void testTSFactorySeralization() throws Throwable {
        ConnectionsChecker cc = new ConnectionsChecker();
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        final InjectableValues.Std injectableValues = new InjectableValues.Std();
        injectableValues.addValue(ConnectionsChecker.class, cc);
        mapper.setInjectableValues(injectableValues);
        IdGenerator idGen = new IdGenerator("TestSet");

        ObjectsFactoryTS of = new ObjectsFactoryTS(cc,idGen);
        DelayTS dts = (DelayTS) of.build(TSOConstants.DELAY_TSOBJID);
        String jsonInString = mapper.writeValueAsString(dts);
        System.out.println(jsonInString);
        ObjectTS result = mapper.readValue(jsonInString,ObjectTS.class);
        System.out.println(result.getClass().getName());
    }


}
