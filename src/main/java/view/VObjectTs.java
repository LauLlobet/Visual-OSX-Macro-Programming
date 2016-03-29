package view;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import logic.Caller;
import view.UI.FrameVObject;
import view.UI.PortPanelFactory;

import java.io.IOException;
import java.util.Iterator;

/**
 * Created by quest on 17/3/16.
 */
public class VObjectTS extends FrameVObject {

    public Caller modelCaller;

    private int x;
    private int y;
    private int w;
    private int h;

    public VObjectTS(String id, Caller mc, PortPanelFactory portPanelFactory) {
        super(id,1,1,1,1, portPanelFactory);
        this.modelCaller = mc;
        this.setVisible(true);
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

    public void setInputsHub(String inputHubString) {
        try{
            trySetInputsHub(inputHubString);
        }catch (Exception e){
            e.printStackTrace();
        }

    }


    public void setOutputsHub(String outputsHubString) {
        try{
            trySetOutputsHub(outputsHubString);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void trySetOutputsHub(String outputsHubString) throws Exception{
        Iterator<JsonNode> i = getJsonNodeIterator(outputsHubString);
        int num = 0;
        while (i.hasNext()){
            String port = i.next().path("messageType").textValue();
            createVOutputPort(port,num);
            num++;
        }
    }

    private void trySetInputsHub(String inputHubString) throws Exception{
        Iterator<JsonNode> i = getJsonNodeIterator(inputHubString);
        int num = 0;
        while (i.hasNext()){
            String port = i.next().path("messageType").textValue();
            createVInputPort(port,num);
            num++;
        }
    }

    private void createVInputPort(String port,int num) {
        createInputPanelPort(port,num);
    }

    private void createVOutputPort(String port, int num) {
        createOutputPanelPort(port, num);
    }


    private Iterator<JsonNode> getJsonNodeIterator(String inputHubString) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(inputHubString);
        return rootNode.path("ports").elements();
    }

}
