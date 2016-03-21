package model;
import Constants.TSOConstants;
import com.fasterxml.jackson.annotation.JacksonInject;
import logic.Caller;
import model.tsobject.ObjectTS;

public class DelayTS extends ObjectTS {


    public DelayTS() {
        super();
        type = TSOConstants.DELAY_TSOBJID;
    }
}
