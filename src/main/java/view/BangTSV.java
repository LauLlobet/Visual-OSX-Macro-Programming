package view;

import logic.Caller;
import view.UI.PortPanelFactory;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by quest on 30/3/16.
 */
public class BangTSV extends ObjectTSV {

    private  JButton button;
    private String field1;

    public BangTSV(String id, Caller mc, PortPanelFactory portPanelFactory) {
        super(id, mc, portPanelFactory);
        button = new JButton("B");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                BangTSV.this.doSendBang("");
            }
        });

        button.putClientProperty("JComponent.sizeVariant", "mini");
        this.getContentPane().add(button,BorderLayout.CENTER);
    }

    public void doSendBang(String s) {
        try {
            modelCaller.shynchronizeMVCModel(getId(), "", null);  // set model
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

