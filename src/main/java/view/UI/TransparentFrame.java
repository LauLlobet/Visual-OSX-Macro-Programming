package view.UI;

import javax.swing.*;
import java.awt.*;

public class TransparentFrame extends Frame {

    public Point lastLocation;
    public Point point;
    public Dimension lastSize;
    public Dimension nonFullscreenSize;
    public boolean isFullscreen = false;


    public TransparentFrame(String name, int x, int y, int w, int h) {
        super(name);
        lastSize = new Dimension(w,h);
        lastLocation = new Point(x,y);
        nonFullscreenSize = this.getSize();
        point = new Point(w/2,h/2);
        this.setBounds(x,y,w,h);
        this.setUndecorated(true);

        this.setBackground(new Color(255,255,255,40));

        this.setLayout(new BorderLayout());

       /* JPanel header = new JPanel();
        header.setPreferredSize(new Dimension(10, 10));

        JPanel body = new JPanel();
        body.setBackground(new Color(0,255,0,10));

        JPanel footer = new JPanel();
        footer.setPreferredSize(new Dimension(10, 10));
        footer.setBackground(new Color(0,0,255,50));
        footer.setLayout(new BorderLayout());

        JPanel resizer = new JPanel();
        footer.setPreferredSize(new Dimension(10, 10));
        footer.setBackground(new Color(0,255,255,50));
        footer.setLayout(new BorderLayout());


        this.add(header, BorderLayout.PAGE_START);
        this.add(body, BorderLayout.CENTER);
        this.add(footer, BorderLayout.PAGE_END);
        footer.add(resizer,BorderLayout.EAST);


        this.pack();
        this.setVisible(true);

        ComponentResizer cr = new ComponentResizer(new Insets(2, 2, 2, 2),new Dimension(1, 1));
        cr.registerComponent(this);*/
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
