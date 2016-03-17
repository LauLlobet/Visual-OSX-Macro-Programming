package view.UI;

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
        TransPanel p = new TransPanel();
        this.add(p,BorderLayout.CENTER);
        MoveMouseListener mouseListener = new MoveMouseListener(this);
        p.addMouseListener(mouseListener);
        p.addMouseMotionListener(mouseListener);
    /*                Point newLocation = that.getLocationOnScreen();
                    Dimension newSize = that.getSize();
                    int incW = (int)(-that.lastSize.getWidth() + newSize.getWidth());
                    if( incW != 0 && incW < 20 && incW > 0) {
                        double finalSize = newSize.getWidth() + incW;
                        Dimension finalDimension = new Dimension((int) finalSize, (int) newSize.getHeight());
                        that.lastSize.setSize(finalDimension);
                        System.out.println("was" + that.getSize().getWidth() + "setting " + finalDimension.getWidth());
                        that.setBounds((int) 40,//newLocation.getX()-incW,
                                (int) newLocation.getY(),
                                (int) finalDimension.getWidth(),
                                (int) finalDimension.getHeight());
                    }
                    if(incW > 20){
                        that.lastSize.setSize(newSize);
                */
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


    class MoveMouseListener implements MouseListener, MouseMotionListener {
        SymmetricResizableFrame target;
        Point start_drag;
        Point start_loc;
        Dimension start_size;
        Point relative_start_loc;

        public MoveMouseListener(SymmetricResizableFrame target) {
            this.target = target;
        }

        Point getScreenLocation(MouseEvent e) {
            Point cursor = e.getPoint();
            Point target_location = target.getLocationOnScreen();
            return new Point((int) (target_location.getX() + cursor.getX()),
                    (int) (target_location.getY() + cursor.getY()));
        }

        public void mouseClicked(MouseEvent e) {
            boolean fs = !target.isFullscreen;
            target.setFullscreen(fs);
        }

        public void mouseEntered(MouseEvent e) {
        }

        public void mouseExited(MouseEvent e) {
        }

        public void mousePressed(MouseEvent e) {
            this.start_drag = this.getScreenLocation(e);
            this.start_loc = target.getLocation();
            this.start_size = target.getSize();
            this.relative_start_loc = e.getPoint();
            System.out.println("click"+this.relative_start_loc.getX());
        }

        public void mouseReleased(MouseEvent e) {
        }

        public boolean isAtTheBottomRight(Point p){
            int squareSize = 10;
            Dimension d = target.getSize();
            return (p.getX() > d.getWidth() - 10 && p.getY() > d.getHeight() - 10 );
        }

        public void mouseDragged(MouseEvent e) {
            point = e.getPoint();
            if(isAtTheBottomRight(e.getPoint())){
                changeSize(e);

            }else{

               System.out.println("click------>");

                Point current = this.getScreenLocation(e);
                Point offset = new Point((int) current.getX() - (int) start_drag.getX(),
                        (int) current.getY() - (int) start_drag.getY());
                Frame frame = target;
                Point new_location = new Point(
                        (int) (this.start_loc.getX() + offset.getX()), (int) (this.start_loc
                        .getY() + offset.getY()));
                frame.setLocation(new_location);
            }
        }

        public void changeSize(MouseEvent e){
            Point current = this.getScreenLocation(e);
            Point offset = new Point((int) current.getX() - (int) start_drag.getX(),
                    (int) current.getY() - (int) start_drag.getY());
            target.setSize(
                    (int) (this.start_size.getWidth() + offset.getX()),
                    (int) (this.start_size.getHeight() + offset.getY())
                    );
        }

        public void changeSizeSymmetric(MouseEvent e){
            Point current = this.getScreenLocation(e);
            Point offset = new Point((int) current.getX() - (int) start_drag.getX(),
                    (int) current.getY() - (int) start_drag.getY());
            target.setLocation(
                    (int) (this.start_loc.getX() - offset.getX()),
                    (int) (this.start_loc.getY() - offset.getY()));
            target.setSize(
                    (int) (this.start_size.getWidth() + 2* offset.getX()),
                    (int) (this.start_size.getHeight() + 2* offset.getY())
            );
        }

        public void mouseMoved(MouseEvent e) {
        }
    }

}
