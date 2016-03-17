package view;

import logic.Caller;
import logic.ModelCaller;
import view.UI.SymmetricResizableFrame;

import java.awt.*;

/**
 * Created by quest on 17/3/16.
 */
public class VObjectTS extends SymmetricResizableFrame {

    public Caller modelCaller;

    public String id;

    public VObjectTS(String name, Caller mc) {
        super("Symmetric",100,200,100,100);
        this.setVisible(true);
        id = name;
        modelCaller = mc;
    }


    public String getId() {
        return id;
    }

    public int getX() {
        return (int)this.getLocation().getX();
    }

    public void setX(int x) {
        int old = getX();
        this.setLocation(x,this.getY());
        modelCaller.callModel(getId(),x,old);
    }
    public void setY(int y)
    {
        int old = getY();
        this.setLocation(this.getX(),y);
        modelCaller.callModel(getId(),y,old);
    }
    public void setW(int w) {
        int old = getW();
        this.setSize(w,this.getH());
        modelCaller.callModel(getId(),w,old);
    }

    public void setH(int h) {
        int old = getH();
        this.setSize(this.getWidth(),h);
        modelCaller.callModel(getId(),h,old);
    }

    public int getY() {
        return (int)this.getLocation().getY();
    }



    public int getW() {
        return (int)this.getSize().getWidth();
    }


    public int getH() {
        return (int)this.getSize().getHeight();
    }


}
