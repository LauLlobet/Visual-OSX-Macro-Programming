package view.UI;

import Constants.TSOConstants;
import view.UI.resizeandmove.ComponentMover;
import view.UI.resizeandmove.ComponentResizer;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;


public class FrameVObject extends javax.swing.JFrame {

    public Point lastLocation;
    public Point point;
    public Dimension lastSize;
    public Dimension nonFullscreenSize;
    public boolean isFullscreen = false;
    public JPanel header;
    public JPanel footer;
    public PortPanelFactory portPanelFactory;
    public String id;


    protected ArrayList<LinkedList<Double>> activePorts;
    private ArrayList<PortPanel> outputPorts;
    private Color red = new Color(0,0,0);
    protected JPanel mainPanel;


    public FrameVObject(String id, int x, int y, int w, int h, PortPanelFactory portPanelFactory) {
        super(id);
        this.id = id;
        this.portPanelFactory = portPanelFactory;
        lastSize = new Dimension(w,h);
        lastLocation = new Point(x,y);
        nonFullscreenSize = this.getSize();
        point = new Point(w/2,h/2);
        activePorts = new ArrayList<LinkedList<Double>>();
        outputPorts = new ArrayList<PortPanel>();

        this.setBounds(x,y,w,h);
        this.setUndecorated(true);
        this.setAlwaysOnTop( true );

        getRootPane().putClientProperty("apple.awt.draggableWindowBackground", Boolean.FALSE);

        this.mainPanel = new JPanel(new BorderLayout());

        this.setBackground(new Color(200,0,0,10));

        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(mainPanel,BorderLayout.CENTER);

        this.setPreferredSize(new Dimension(100, 161));

        this.header = new JPanel();
        this.header.setPreferredSize(new Dimension(100, 10));
        this.header.setBackground(new Color(200,200,200,30));
        this.header.setLayout(new BoxLayout(this.header, BoxLayout.X_AXIS));

        footer = new JPanel();
        footer.setPreferredSize(new Dimension(100, 10));
        footer.setBackground(new Color(200,200,200,20));
        footer.setLayout(new BoxLayout(footer,  BoxLayout.X_AXIS));

        this.mainPanel.add(this.header, BorderLayout.NORTH);
        this.mainPanel.add(footer, BorderLayout.SOUTH);

        this.pack();
        this.setVisible(true);

        ComponentResizer cr = new ComponentResizer(new Insets(2, 2, 2, 2),new Dimension(1, 1));
        cr.registerComponent(this);
        ComponentMover cm = new ComponentMover(this,header);

    }

    public void createInputPanelPort(String type,int numPort){
        PortPanel p = this.portPanelFactory.create(type,"i", this.id, numPort,this);
        addPanelToObjectAndRevalidate( this.header,p);
    }

    public void createOutputPanelPort(String type, int numPort){
        PortPanel p = this.portPanelFactory.create(type,"o", this.id, numPort, this);
        addPanelToObjectAndRevalidate( this.footer,p);
        outputPorts.add(p);
    }

    public void addPanelToObjectAndRevalidate(JPanel parent, JPanel p){
        parent.setLayout(new BoxLayout(parent, BoxLayout.X_AXIS));
        parent.add(Box.createRigidArea(new Dimension(TSOConstants.UI_SEPARATIONBETWEENPORTS,0)));
        parent.add(p);
        parent.revalidate();
    }


    protected void setActivePortsRed() {
        int i = 0;
        for(LinkedList<Double> port: activePorts) {
            try {
                if ( System.currentTimeMillis() < port.getFirst()) {
                    outputPorts.get(i).setBackground(red);
                } else {
                    outputPorts.get(i).setBackground(outputPorts.get(i).getDefaultBackground());
                }
                outputPorts.get(i).revalidate();
                footer.revalidate();
            }catch (Exception e){

            }
            i++;
        }
    }

    public ArrayList<LinkedList<Double>> getActivePorts() {
        return activePorts;
    }

    public void setActivePorts(ArrayList<LinkedList<Double>> activePorts) {
        this.activePorts = activePorts;
    }

    public void setFullscreen(Boolean b){
        if(b){
            this.nonFullscreenSize = this.getSize();
            Toolkit tk = Toolkit.getDefaultToolkit();
            int xSize = ((int) tk.getScreenSize().getWidth());
            int ySize = ((int) tk.getScreenSize().getHeight());
            this.setSize(xSize,ySize);
            this.setLocation(0,0);
        }else {
            this.setSize(this.nonFullscreenSize);
        }
        this.isFullscreen = b;
    }

    @Override
    public void setSize(int w, int h){
        super.setSize(w,h);
    }

}
