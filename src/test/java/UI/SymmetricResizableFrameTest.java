package UI;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;

/**
 * Created by quest on 15/3/16.
 */
public class SymmetricResizableFrameTest {
    public static void main(String[] args) throws Exception {
        Frame frame = new SymmetricResizableFrame("Symmetric",100,200,100,100);
        frame.setVisible(true); // make window visible
        Frame frame2 = new SymmetricResizableFrame("Symmetric",400,200,100,100);
        frame2.setVisible(true); // make window visible
    }
}