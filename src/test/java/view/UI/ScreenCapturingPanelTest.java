package view.UI;

import org.junit.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by quest on 21/3/16.
 */
public class ScreenCapturingPanelTest {

    public  class ShowingFramesPanel extends JPanel implements ScreenRegionsListener {

        public BufferedImage image;
        public ShowingFramesPanel(){

        }

        @Override
        public void newCapture(BufferedImage capture) {
            image = capture;
            this.repaint();
        }

        @Override
        public void paint(Graphics g) {
            g.drawImage(image, 0, 0, this);
            g.drawRect(30,20,10,10);
            this.paintComponents(g);
        }
    }
    @Test
    public void testScreenCapturingTest() throws Exception {

        ScreenCapturer sc = new ScreenCapturer();
        Frame frame = new TransparentFrame("Symmetric",100,100,100,200);


        JPanel header = new ScreenCapturingPanel();
        header.setPreferredSize(new Dimension(100, 100));
        header.setBackground(new Color(30,30,30,50));

        ShowingFramesPanel footer = new ShowingFramesPanel();
        footer.setPreferredSize(new Dimension(100, 100));
       // footer.setBackground(new Color(0,0,255,50));
        footer.setLayout(new BorderLayout());

        frame.add(header, BorderLayout.PAGE_START);
        frame.add(footer, BorderLayout.PAGE_END);


        frame.pack();
        frame.setVisible(true);

        sc.addPanelAndListener(header,footer);
        Thread t = new Thread(sc);
        t.start();



        frame.pack();
        frame.setVisible(true);

        ComponentMover componentMover = new ComponentMover(frame,footer);

        Thread.sleep(30000);


        /*ComponentResizer cr = new ComponentResizer(new Insets(2, 2, 2, 2),new Dimension(1, 1));
        cr.registerComponent(frame);*/

    }
}
