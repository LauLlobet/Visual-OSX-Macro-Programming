package view;

import logic.Caller;
import view.UI.PortPanelFactory;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

/**
 * Created by quest on 30/3/16.
 */
public class ThresholdTSV extends ObjectTSV {

    private  JPanel boxpanel;
    private int threshold2;
    private int threshold1;
    private int maxValue;
    private int value;
    private JSlider sliderValue;

    public ThresholdTSV(String id, Caller mc, PortPanelFactory portPanelFactory) {
        super(id, mc, portPanelFactory);
        boxpanel = new JPanel();
        boxpanel.setLayout(new BorderLayout());
        this.getContentPane().add(boxpanel,BorderLayout.CENTER);
        JPanel inputSliderPanel = new JPanel();
        boxpanel.setBackground(new Color(200,200,200));

        inputSliderPanel.setBackground(new Color(100,100,100));
        inputSliderPanel.setLayout(new BorderLayout());

        sliderValue = new JSlider();
        sliderValue.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider s = (JSlider) e.getSource();
                //s.setValue(value);
            }
        });
        sliderValue.putClientProperty("JComponent.sizeVariant", "mini");
        inputSliderPanel.add(sliderValue,BorderLayout.CENTER);


        final JSlider sliderT1 = new JSlider();
        sliderT1.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider s = (JSlider) e.getSource();
                ThresholdTSV.this.setThreshold1(s.getValue());
            }
        });
        sliderT1.setPreferredSize(new Dimension(100,20));
        sliderT1.putClientProperty("JComponent.sizeVariant", "mini");
        final JSlider sliderT2 = new JSlider();
        sliderT2.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider s = (JSlider) e.getSource();
                ThresholdTSV.this.setThreshold2(s.getValue());
            }
        });
        sliderT2.setPreferredSize(new Dimension(100,20));
        sliderT2.putClientProperty("JComponent.sizeVariant", "mini");
        boxpanel.add(inputSliderPanel,BorderLayout.NORTH);
        boxpanel.add(sliderT1,BorderLayout.CENTER);
        boxpanel.add(sliderT2,BorderLayout.SOUTH);
    }


    public int getThreshold2() {
        return threshold2;
    }

    public void setThreshold2(int nthreshold2) {
        if(nthreshold2 != threshold2 && modelCaller != null) {
            threshold2 = nthreshold2;
            modelCaller.shynchronizeMVCModel(getId(), threshold2, null);  // set model
        }
    }

    public int getThreshold1() {
        return threshold1;
    }

    public void setThreshold1(int nthreshold1) {
        if(nthreshold1 != threshold1 && modelCaller != null) {
            threshold1 = nthreshold1;
            modelCaller.shynchronizeMVCModel(getId(), threshold1, null);  // set model
        }
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int nmaxValue) {
        if(nmaxValue != maxValue && modelCaller != null) {
            maxValue = nmaxValue;
            modelCaller.shynchronizeMVCModel(getId(), maxValue, null);  // set model
            sliderValue.setMaximum(maxValue);
        }
    }

    public int getValue() {
        return value;
    }

    public void setValue(int nvalue) {
        if(nvalue != value && modelCaller != null) {
            value = nvalue;
            modelCaller.shynchronizeMVCModel(getId(), value, null);  // set model
            sliderValue.setValue(value);
        }
    }

}

