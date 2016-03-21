package view.UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
public class SymmetricResizableFrame extends Frame {

    public Point lastLocation;
    public Point point;
    public Dimension lastSize;
    public Dimension nonFullscreenSize;
    public boolean isFullscreen = false;

    public SymmetricResizableFrame(String name){
        this(name,200,300,100,161);
    }

    public SymmetricResizableFrame (String name, int x, int y, int w, int h) {
        super(name);
        lastSize = new Dimension(w,h);
        lastLocation = new Point(x,y);
        nonFullscreenSize = this.getSize();
        point = new Point(w/2,h/2);
        this.setBounds(x,y,w,h);
        this.setUndecorated( true );


        setBackground(new Color(200,0,0,80));

/*
        Panel mainPanel =  new TransPanel();
        mainPanel.setVisible(true);
        this.add(mainPanel);

        Panel header = new Panel();
        header.setSize(50,10);
        header.setVisible(true);
*/
        //mainPanel.add(header);


/*
        Panel footer = new Panel(new RelativeLayout(RelativeLayout.X_AXIS));
        footer.setVisible(true);
        mainPanel.add(footer, new Float(1));
*/

      //  ComponentMover componentMover = new ComponentMover(this,mainPanel);



/*        Panel footerInputs = new Panel(new RelativeLayout(RelativeLayout.X_AXIS));
        Panel footerResize = new Panel(new RelativeLayout(RelativeLayout.X_AXIS));

        footerResize.setSize(10,10);
        footerResize.setBackground(Color.green);
        footerInputs.setSize(50,10);
        footerInputs.setBackground(Color.gray);

        footer.add(footerInputs,new Float(8));
        footer.add(footerResize,new Float(1));

        JPanel panel = new JPanel( new RelativeLayout(RelativeLayout.Y_AXIS) );*/


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
        System.out.println("Setting super size:"+w+"."+h);
        super.setSize(w,h);
    }

}
