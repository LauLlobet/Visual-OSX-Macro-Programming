package model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.InjectableValues;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import logic.ConnectionsChecker;
import model.tsobject.ObjectTS;
import model.tsobject.tsobjectparts.ConnectionHubTS;
import model.tsobject.tsobjectparts.InputConnectionHubTS;
import model.tsobject.tsobjectparts.OutputConnectionHubTS;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by quest on 16/3/16.
 */
public class ConnectionHubTSTest {


    static public class Ihavethem2
    {
        public ArrayList<ConnectionHubTS> em;
        public Ihavethem2(){
            em = new ArrayList<ConnectionHubTS>();
        }
    }

    @Test
    public void testArraySeralization() throws Throwable {
        ConnectionsChecker cc = new ConnectionsChecker();
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        final InjectableValues.Std injectableValues = new InjectableValues.Std();
        injectableValues.addValue(ConnectionsChecker.class, cc);
        mapper.setInjectableValues(injectableValues);


        Ihavethem2 ihavethem = new Ihavethem2();
        ihavethem.em.add(new OutputConnectionHubTS(cc));
        ihavethem.em.add(new OutputConnectionHubTS(cc));
        ihavethem.em.add(new InputConnectionHubTS(cc));
        ihavethem.em.add(new OutputConnectionHubTS(cc));

        System.out.println("a0"+ihavethem.em.get(0).getClass().getName());
        System.out.println("a1"+ihavethem.em.get(1).getClass().getName());
        System.out.println("a2"+ihavethem.em.get(2).getClass().getName());

        String jsonInString = mapper.writeValueAsString(ihavethem);
        System.out.println(jsonInString);

        Ihavethem2 result = mapper.readValue(jsonInString,Ihavethem2.class);
        System.out.println(result.getClass().getName());
    }


    static public class Ihavethem
    {
        public Ihavethem(){

        }

        public String getEm() {
            return em;
        }

        public void setEm(String em) {
            this.em = em;
        }

        public String em;

        public ArrayList<Hub> getHubs() {
            return hubs;
        }

        public void setHubs(ArrayList<Hub> hubs) {
            this.hubs = hubs;
        }

        public ArrayList<Hub> hubs;
    }


    @JsonTypeInfo(
            use = JsonTypeInfo.Id.NAME,
            include = JsonTypeInfo.As.PROPERTY,
            property = "type")
    @JsonSubTypes({
            @JsonSubTypes.Type(value = Hab.class, name = "hab"),
            @JsonSubTypes.Type(value = Hub.class, name = "hub"),
            @JsonSubTypes.Type(value = Heb.class, name = "heb") })
    static public class Hub
    {
        String type="hub";
        public Hub(){
            em = "parent";
        }

        public String getEm() {
            return em;
        }

        public void setEm(String em) {
            this.em = em;
        }

        public String em;

    }


    static public class Hab extends Hub
    {

        String type="hab";
        public Hab(){
            super();
            em = "hab";
            emf = "emfhab";
        }

        public String getEmf() {
            return emf;
        }

        public void setEmf(String em) {
            this.emf = em;
        }

        public String emf;
    }

    static public class Heb extends Hub
    {
        String type="heb";
        public Heb(){
            super();
            em = "hab";
            emfe = "emfheb";
        }

        public String getEmfe() {
            return emfe;
        }

        public void setEmfe(String em) {
            this.emfe = em;
        }

        public String emfe;
    }


    /*

    @Test
    public void testArrayPolymorphismSerialization() throws Throwable {


        Ihavethem ihavethem = new Ihavethem();
        Hub u = new Hub();
        Hub d = new Hab();
        Hub c = new Heb();

        ArrayList<Hub> list = new ArrayList<Hub>();
        list.add(u);
        list.add(d);
        list.add(c);

        ihavethem.setHubs(list);

        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(ihavethem);
        System.out.println(jsonInString);

        Ihavethem ihavethemAfter= mapper.readValue(jsonInString,Ihavethem.class);

        System.out.println(ihavethemAfter.getClass().getName());
    }
    */
}