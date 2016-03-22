package view;

import com.sun.awt.AWTUtilities;

import javax.swing.*;
import java.awt.*;

/**
 * Created by quest on 22/3/16.
 */
public class FullScreenConnectionShow {

    public static void main ( String[] args )
    {
        Window w = new Window ( null );

        w.add ( new JComponent()
        {
            protected void paintComponent ( Graphics g )
            {
                g.setColor ( Color.BLACK );
                g.fillRect ( 0, getHeight () / 2 - 10, getWidth (), 20 );
                g.fillRect ( getWidth () / 2 - 10, 0, 20, getHeight () );
            }

            public Dimension getPreferredSize ()
            {
                return new Dimension ( 100, 100 );
            }

            public boolean contains ( int x, int y )
            {
                return false;
            }
        } );

        AWTUtilities.setWindowOpaque ( w, false );
        AWTUtilities.setWindowOpacity ( w, 0.5f );

        w.pack ();
        w.setLocationRelativeTo ( null );
        w.setVisible ( true );
    }
}
