package view.UI;

import logic.BewteenWindowsConnectionMaker;

import javax.swing.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.*;

/**
 * Created by quest on 22/3/16.
 */
public class PortPanel extends JPanel implements DragGestureListener, DragSourceListener, DropTargetListener {



    private BewteenWindowsConnectionMaker bwcm;
    private String id;
    private DragSource ds = DragSource.getDefaultDragSource();

    public PortPanel(BewteenWindowsConnectionMaker bwcm,String id){
        this.bwcm = bwcm;
        this.id = id;
        int action = DnDConstants.ACTION_COPY_OR_MOVE;
        ds.createDefaultDragGestureRecognizer(this, action, this);
        new DropTarget(this, DnDConstants.ACTION_COPY_OR_MOVE, this);

        System.out.println("port instantiated");

    }

    public void dragEnter(DropTargetDragEvent e) {
        //  System.out.println("Entering drop target #1");
    }

    public void dragExit(DropTargetEvent e) {
        // System.out.println("Exiting drop target #1");
    }

    public void dragOver(DropTargetDragEvent e) {
        //  System.out.println("Dragging over drop target #1");
    }

    public void drop(DropTargetDropEvent e) {
        // System.out.println("Dropping");

        try {
            Transferable t = e.getTransferable();

            if (e.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                e.acceptDrop(e.getDropAction());

                String s;
                s = (String) t.getTransferData(DataFlavor.stringFlavor);
                this.bwcm.connect(s,this.id);
                e.dropComplete(true);
            } else
                e.rejectDrop();
        } catch (java.io.IOException e2) {
        } catch (UnsupportedFlavorException e2) {
        }
    }

    public void dropActionChanged(DropTargetDragEvent e) {
        //System.out.println("Drop action changed #1");
    }

    public void dragGestureRecognized (DragGestureEvent e){
        try {
            Transferable t = new StringSelection(this.id);
            e.startDrag(DragSource.DefaultCopyNoDrop, t, this);
        } catch (InvalidDnDOperationException e2) {
            System.out.println(e2);
        }
    }

    public void dragDropEnd(DragSourceDropEvent e) {
        this.bwcm.draggingEnded();
    }

    public void dragEnter(DragSourceDragEvent e) {
        // System.out.println("Entering drop target #2");
        DragSourceContext ctx = e.getDragSourceContext();
        int action = e.getDropAction();
        if ((action & DnDConstants.ACTION_COPY) != 0)
            ctx.setCursor(DragSource.DefaultCopyDrop);
        else
            ctx.setCursor(DragSource.DefaultCopyNoDrop);
    }

    public void dragExit(DragSourceEvent e) {
        //System.out.println("Exiting drop target #2");
    }

    public void dragOver(DragSourceDragEvent e) {
        this.bwcm.draggingOver((int)this.getLocationOnScreen().getX()+this.getWidth()/2,
                (int)this.getLocationOnScreen().getY()+this.getHeight(),
                e.getX(),
                e.getY());
    }

    public void dropActionChanged(DragSourceDragEvent e) {
        // System.out.println("Drop action changed #2");
    }


    public String getViewId() {
        return id;
    }
}
