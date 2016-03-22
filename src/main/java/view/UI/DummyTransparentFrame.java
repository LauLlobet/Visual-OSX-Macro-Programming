package view.UI;

import javax.swing.*;
import java.awt.*;

/**
 * Created by quest on 21/3/16.
 */
public class DummyTransparentFrame extends TransparentFrame {

    public DummyTransparentFrame(String name, int x, int y, int w, int h) {
        super(name, x, y, w, h);
        JPanel header = new JPanel();
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
        cr.registerComponent(this);
    }
}
