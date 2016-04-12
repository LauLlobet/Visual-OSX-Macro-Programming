package view;

import logic.Caller;
import view.UI.PortPanelFactory;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

/**
 * Created by quest on 30/3/16.
 */
public class SingleFieldObjectTSV extends ObjectTSV {

    private  JTextField jfield1;
    private String field1;

    public SingleFieldObjectTSV(String id, Caller mc, PortPanelFactory portPanelFactory) {
        super(id, mc, portPanelFactory);
        jfield1 = new JTextField();
        jfield1.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                trySet();
            }
            public void removeUpdate(DocumentEvent e) {
                trySet();
            }
            public void insertUpdate(DocumentEvent e) {
                trySet();
            }
            public void trySet() {
                try{
                    SingleFieldObjectTSV.this.setField1(jfield1.getText());
                }catch (InvalidFieldException e){
                    JOptionPane.showMessageDialog(null,
                            e.getMessage(), "Error Massage",
                            JOptionPane.ERROR_MESSAGE);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        jfield1.putClientProperty("JComponent.sizeVariant", "mini");
        this.mainPanel.add(jfield1,BorderLayout.CENTER);
    }

    public String getField1() {
        return field1;
    }

    public void setField1(String nfield1) throws Exception {
        if( !nfield1.equals(field1) && modelCaller != null) {
            field1 = nfield1;
            modelCaller.shynchronizeMVCModel(getId(), field1, null);  // set model
            if(!field1.equals(jfield1.getText())){
                setTextBoxField1();
            }
        }
    }

    public void setTextBoxField1(){
        jfield1.setText(field1);
    }
}

