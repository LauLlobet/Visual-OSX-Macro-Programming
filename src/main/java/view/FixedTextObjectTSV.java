package view;

import logic.Caller;
import view.UI.PortPanelFactory;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

/**
 * Created by quest on 30/3/16.
 */
public class FixedTextObjectTSV extends ObjectTSV {

    private  JTextField jfield1;
    private String field1;

    public FixedTextObjectTSV(String id, Caller mc, PortPanelFactory portPanelFactory) {
        super(id, mc, portPanelFactory);
        jfield1 = new JTextField();
        jfield1.setEditable(false);
        jfield1.putClientProperty("JComponent.sizeVariant", "mini");
        this.mainPanel.add(jfield1,BorderLayout.CENTER);
    }

    public String getField1() {
        return field1;
    }

    public void setField1(String nfield1) throws Exception {
        if( !nfield1.equals(field1) && modelCaller != null) {
            field1 = nfield1;
            jfield1.setText(nfield1);
            modelCaller.shynchronizeMVCModel(getId(), field1, null);  // set model
        }
    }
}

