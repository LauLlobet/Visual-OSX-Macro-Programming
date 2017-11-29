package model.TSObject.TSObjectparts;

import Constants.TSOConstants;
import com.fasterxml.jackson.annotation.JacksonInject;
import logic.ConnectionsChecker;

/**
 * Created by quest on 16/3/16.
 */
public class InputConnectionHubTS extends ConnectionHubTS {

    public InputConnectionHubTS(@JacksonInject("cc") final ConnectionsChecker cc) throws Throwable{
        super(cc);
        type = TSOConstants.IDTSObjectInputConnectionHub;
    }
    public void setParentId(String id){
        super.setParentId("[i]"+id);
    }
}
