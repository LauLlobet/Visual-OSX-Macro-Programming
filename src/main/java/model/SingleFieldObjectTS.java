package model;

import Constants.TSOConstants;
import logic.ConnectionsChecker;
import logic.IdGenerator;
import model.TSObject.ObjectTS;

import java.util.Arrays;

/**
 * Created by quest on 1/4/16.
 */
public abstract class SingleFieldObjectTS extends ObjectTS {

    protected String field1 = "3000";

    public SingleFieldObjectTS() {
        super();
    }

    @Override
    public void processTic(){
        super.processTic();
    }

    public String getField1() {
        return field1;
    }

    public void setField1(String field1) throws Exception{
        this.field1 = field1;
        if(registeredInMvc){caller.shyncronizeMVCView(getId(),field1,null);};
    }


}
