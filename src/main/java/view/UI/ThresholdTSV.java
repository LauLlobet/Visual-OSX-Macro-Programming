package view.UI;

import logic.Caller;
import view.ObjectTSV;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

/**
 * Created by quest on 30/3/16.
 */
public class ThresholdTSV extends ObjectTSV {

    private final JPanel boxpanel;
    private int threshold2;
    private int threshold1;
    private int maxValue;
    private int value;
    final JSlider sliderValue;

    public ThresholdTSV(String id, Caller mc, PortPanelFactory portPanelFactory) {
        super(id, mc, portPanelFactory);
        boxpanel = new JPanel();
        boxpanel.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

        this.getContentPane().add(boxpanel,BorderLayout.CENTER);


        sliderValue = new JSlider();
        sliderValue.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider s = (JSlider) e.getSource();
                s.setValue(value);
            }
        });


        final JSlider sliderT1 = new JSlider();
        sliderT1.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider s = (JSlider) e.getSource();
                ThresholdTSV.this.setThreshold1(s.getValue());
            }
        });
        sliderT1.setPreferredSize(new Dimension(100,20));

        final JSlider sliderT2 = new JSlider();
        sliderT2.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider s = (JSlider) e.getSource();
                ThresholdTSV.this.setThreshold2(s.getValue());
            }
        });
        sliderT2.setPreferredSize(new Dimension(100,20));


        boxpanel.add(sliderT1);
        boxpanel.add(sliderT2);
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

