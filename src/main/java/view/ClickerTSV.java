package view;

import logic.Caller;
import view.UI.PortPanelFactory;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by quest on 30/3/16.
 */
public class ClickerTSV extends ObjectTSV {
    private int clickRelPositionX;
    private int clickRelPositionY;
    private boolean toBeClicked = false;

    public ClickerTSV(String id, Caller mc, PortPanelFactory portPanelFactory) {
        super(id, mc, portPanelFactory);
        Button b = new Button("Set");
        b.setPreferredSize(new Dimension(20,20));
        this.getContentPane().add(b,BorderLayout.AFTER_LAST_LINE);
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ClickerTSV.this.doSetToBeClicked();
            }
        });
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                ClickerTSV.this.hasBeenClicked(mouseEvent.getX(), mouseEvent.getY());
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {

            }
        });

       this.setTransparent();
    }

    private void hasBeenClicked(int x, int y) {
        if(toBeClicked){
            setTransparent();
            toBeClicked = false;
            this.setClickRelPositionX(x);
            this.setClickRelPositionY(y);
        }
    }

    private void setTransparent() {
        this.setBackground(new Color(0,0,0,0));
    }

    private void doSetToBeClicked() {
        ClickerTSV.this.setNotTransparent();
        this.toBeClicked = true;
    }

    private void setNotTransparent() {
        this.setBackground(new Color(100,200,200,100));
    }


    @Override
    public void paint(Graphics g){
        super.paint(g);
        g.setColor(new Color(0, 0,0,180));
        ((Graphics2D)g).setStroke(new BasicStroke(2));
        g.drawLine(clickRelPositionX - 10, clickRelPositionY, clickRelPositionX - 4, clickRelPositionY);
        g.drawLine(clickRelPositionX, clickRelPositionY - 10, clickRelPositionX, clickRelPositionY - 4);

        g.drawLine(clickRelPositionX + 4, clickRelPositionY, clickRelPositionX + 10, clickRelPositionY);
        g.drawLine(clickRelPositionX, clickRelPositionY +4, clickRelPositionX, clickRelPositionY + 10);
    }


    public int getClickRelPositionX() {
        return clickRelPositionX;
    }

    public void setClickRelPositionX(int nclickRelPositionX) {
        if(nclickRelPositionX != clickRelPositionX && modelCaller != null) {
            clickRelPositionX = nclickRelPositionX;
            try {
                modelCaller.shynchronizeMVCModel(getId(), nclickRelPositionX, null);  // set model
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        repaint();
    }

    public int getClickRelPositionY() {
        return clickRelPositionY;
    }

    public void setClickRelPositionY(int nclickRelPositionY) {
        if(nclickRelPositionY != clickRelPositionY && modelCaller != null) {
            clickRelPositionY = nclickRelPositionY;
            try {
                modelCaller.shynchronizeMVCModel(getId(), clickRelPositionY, null);  // set model
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        repaint();
    }



}
