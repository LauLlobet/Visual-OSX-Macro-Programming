package view;

import logic.Caller;
import view.UI.PortPanelFactory;

import java.awt.*;

/**
 * Created by quest on 30/3/16.
 */
public class ClickerTSV extends ObjectTSV {
    private int clickRelPositionX;
    private int clickRelPositionY;

    public ClickerTSV(String id, Caller mc, PortPanelFactory portPanelFactory) {
        super(id, mc, portPanelFactory);
        this.setBackground(new Color(0,0,0,0));
    }

    @Override
    public void paint(Graphics g){
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
            modelCaller.shynchronizeMVCModel(getId(), nclickRelPositionX, null);  // set model
        }
        repaint();
    }

    public int getClickRelPositionY() {
        return clickRelPositionY;
    }

    public void setClickRelPositionY(int nclickRelPositionY) {
        if(nclickRelPositionY != clickRelPositionY && modelCaller != null) {
            clickRelPositionY = nclickRelPositionY;
            modelCaller.shynchronizeMVCModel(getId(), clickRelPositionY, null);  // set model
        }
        repaint();
    }



}
