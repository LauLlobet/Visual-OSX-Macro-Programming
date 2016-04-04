package model;

import model.tsobject.ObjectTS;

/**
 * Created by quest on 1/4/16.
 */
public abstract class FixedTextObjectTS extends ObjectTS {

    protected String field1 = "3000";

    public FixedTextObjectTS() {
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
