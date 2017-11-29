package model;

import model.TSObject.ObjectTS;
import view.InvalidFieldException;

/**
 * Created by quest on 1/4/16.
 */
public abstract class SingleNumericFieldObjectTS extends ObjectTS {

    protected String field1 = "3000";
    protected int intField1 = 0;

    public SingleNumericFieldObjectTS() {
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
        try{
            intField1 = Integer.parseInt(field1);
            this.field1 = field1;
            if(registeredInMvc){caller.shyncronizeMVCView(getId(),field1,null);};
        }catch (NumberFormatException e){
            throw  new InvalidFieldException();
        }
    }


}
