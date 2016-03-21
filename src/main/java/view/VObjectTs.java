package view;

import logic.Caller;
import view.UI.TransparentFrame;

/**
 * Created by quest on 17/3/16.
 */
public class VObjectTS extends TransparentFrame {

    public Caller modelCaller;

    public String id;


    private int x;
    private int y;
    private int w;
    private int h;

    public VObjectTS(String name, Caller mc) {
        super("Symmetric",1,1,1,1);
        this.modelCaller = mc;
        this.setVisible(true);
        id = name;
    }


    public String getId() {
        return id;
    }

    @Override
    public void setSize(int _w, int _h){
        System.out.println("Setting size to :"+_w+" "+_h);
        super.setSize(_w,_h);
        this.setW(_w);
        this.setH(_h);
    }

    @Override
    public void setLocation(int _x, int _y){
        super.setLocation(_x,_y);
        this.setX(_x);
        this.setY(_y);
    }


    public void setW(int nw) {;
        if(nw != w && modelCaller != null) {
            w = nw;
            modelCaller.shynchronizeMVCModel(getId(), nw, null);  // set model
        }
        w = nw;
        if (nw != this.getSize().getWidth())  {
            System.out.println("settingW:"+nw+" "+h);
            super.setSize(nw,h);
        }

    }

    public void setH(int nh) {
        if(nh != h && modelCaller != null) {
            h = nh;
            modelCaller.shynchronizeMVCModel(getId(), nh, null);  // set model
        }
        h = nh;
        if (nh != this.getSize().getHeight())  {
            System.out.println("settingH:"+w+" "+nh);
            super.setSize(w,nh);
        }
    }

    public void resetSize(){
        this.setW(w);
        this.setH(h);
    }


    public void setX(int nx) {
        if(nx != x && modelCaller != null) {
            x = nx;
            modelCaller.shynchronizeMVCModel(getId(), nx, null);  // set model
        }
        if (nx != this.getLocation().getX())  {
            super.setLocation(nx,this.getY());
        }
    }
    public void setY(int ny)
    {
        if(ny != y && modelCaller != null) {
            y = ny;
            modelCaller.shynchronizeMVCModel(getId(), ny, null);  // set model
        }
        if (ny != this.getLocation().getY())  {
            super.setLocation(this.getX(),ny);
        }
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


    public int getX() {
        return (int)this.getLocation().getX();
    }

}
