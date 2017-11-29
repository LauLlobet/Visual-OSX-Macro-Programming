package model;

import Constants.TSOConstants;
import com.fasterxml.jackson.databind.InjectableValues;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import logic.Caller;
import logic.ConnectionsChecker;
import logic.IdGenerator;
import model.TSObject.ObjectTS;
import model.TSObject.TSObjectparts.InputConnectionHubTS;
import model.TSObject.TSObjectparts.OutputConnectionHubTS;
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




}
