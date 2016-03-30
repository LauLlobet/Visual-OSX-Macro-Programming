package model;
import Constants.TSOConstants;
import logic.ConnectionsChecker;
import logic.IdGenerator;
import model.tsobject.ObjectTS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class ClickerTS extends ObjectTS {
    int clickRelPositionX = 50;
    int clickRelPositionY = 50;

    public ClickerTS() {
        super();
        type = TSOConstants.CLICKER_TSOBJID;
    }

    @Override
    public void processTic(){
        try {
            LinkedList<String> buffer = getInputsHub().getPorts().get(0).getBuffer();
            if(buffer.size() == 0){
                return;
            }
            String message = buffer.removeFirst();
            if("bang".startsWith(message)){
                doClick();
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    private void doClick() {
        try {
            Robot robot = new Robot();
            robot.mouseMove(x+clickRelPositionX,y+clickRelPositionY);
            robot.mousePress(InputEvent.BUTTON1_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_MASK);
            this.setToBack(false);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public static ObjectTS createOne(IdGenerator idGenerator, ConnectionsChecker connectionChecker) throws Throwable{
        ObjectTS newObj;
        newObj = new ClickerTS();
        newObj.setId(idGenerator.getNextId(newObj));
        newObj = setConectionHubs(newObj, connectionChecker);

        newObj.getOutputsHub().setPorts(generatePorts(new ArrayList()));
        newObj.getInputsHub().setPorts(generatePorts(Arrays.asList(
                TSOConstants.MBANG
        )));
        newObj.setW(100);
        newObj.setH(161);
        newObj.setX(900);
        newObj.setY(400);
        return newObj;
    }

    public int getClickRelPositionX() {
        return clickRelPositionX;
    }

    public void setClickRelPositionX(int clickRelPositionX) {
        this.clickRelPositionX = clickRelPositionX;
        if(registeredInMvc){caller.shyncronizeMVCView(getId(),clickRelPositionX,null);};
    }

    public int getClickRelPositionY() {
        return clickRelPositionY;
    }

    public void setClickRelPositionY(int clickRelPositionY) {
        this.clickRelPositionY = clickRelPositionY;
        if(registeredInMvc){caller.shyncronizeMVCView(getId(),clickRelPositionY,null);};

    }

}
