package view;

import logic.Caller;
import model.AssetsPersistanceAccessor;
import sun.security.pkcs11.wrapper.CK_ATTRIBUTE;
import view.UI.PortPanelFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.nio.Buffer;


/**
 * Created by quest on 30/3/16.
 */
public class ScreenFeatureFinderTSV extends ObjectTSV implements ItemListener {

    private static final String LOOK_MODE = "LOOK_MODE";
    private static final String TAKE_PIC_MODE = "TAKE_PIC_MODE";
    private final JPanel recordingPanel;
    private final JPanel classPanel;
    CheckboxGroup mode;
    Checkbox start;
    Checkbox takePic;
    Checkbox paint;
    Checkbox look;
    private String pictureToFindString;
    private BufferedImage pictureToFind;

    public ScreenFeatureFinderTSV(String id, Caller mc, PortPanelFactory portPanelFactory) {
        super(id, mc, portPanelFactory);
        recordingPanel = new JPanel(){
            @Override
            public void paintComponent (Graphics g){
                super.paintComponent(g);
                g.clearRect(0,0,1000,1000);
                if(getMode().equals(LOOK_MODE)){
                    System.out.println("look");
                }else {
                    g.drawImage(pictureToFind, 0, 0, null);
                }
            }
        };
        classPanel =  new JPanel();;
        JPanel buttons = new JPanel();
        buttons.setLayout(new BoxLayout(buttons,BoxLayout.X_AXIS));

        mode = new CheckboxGroup();

        //create checkboxes and add to group
        start = new Checkbox("Go!", mode, true);
        takePic = new Checkbox("C", mode, false);
        paint = new Checkbox("P", mode, false);
        look = new Checkbox("L", mode, false);

        //add radio buttons
        buttons.add(start);
        buttons.add(takePic);
        buttons.add(paint);
        buttons.add(look);
        //add listeners
        start.addItemListener(this);
        takePic.addItemListener(this);
        paint.addItemListener(this);
        look.addItemListener(this);

        recordingPanel.setBackground(new Color(0,255,0,5));

        classPanel.setLayout(new BorderLayout());
        classPanel.add(buttons,BorderLayout.SOUTH);
        classPanel.add(recordingPanel, BorderLayout.CENTER);
        mainPanel.add(classPanel,BorderLayout.CENTER);
        this.setTransparent();
    }

    private void setTransparent() {
        //this.setBackground(new Color(0,0,0,0));
    }

    public String getMode(){
        if( mode.getSelectedCheckbox() == takePic ){
            return TAKE_PIC_MODE;
        }
        if( mode.getSelectedCheckbox() == look ){
            return LOOK_MODE;
        }
        return "DEFAULT";
    }


    public JPanel doGetRecordingPane() {
        return recordingPanel;
    }

    @Override
    public void itemStateChanged(ItemEvent itemEvent) {
        if( getMode().equals(TAKE_PIC_MODE) ){
            doTakePicture(null);
        }
        recordingPanel.invalidate();
        recordingPanel.validate();
        recordingPanel.repaint();
    }

    public void doTakePicture(String notused){
        if(modelCaller != null) {
            try{
                modelCaller.shynchronizeMVCModel(getId(), "", null);  // set model
            }catch (Exception e){

            }
        }
    }


    public void setPictureToFind(String npictureToFindString) {
        if(npictureToFindString != pictureToFindString && modelCaller != null) {
            pictureToFindString = npictureToFindString;
            try {
                modelCaller.shynchronizeMVCModel(getId(), npictureToFindString, null);  // set model
            } catch (Exception e) {
                e.printStackTrace();
            }
            pictureToFind = (BufferedImage) AssetsPersistanceAccessor.getInstance().getPersistantAssetFromKey(npictureToFindString);
        }
    }
}

