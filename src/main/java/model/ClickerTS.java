package model;
import Constants.TSOConstants;
import model.tsobject.ObjectTS;

public class ClickerTS extends ObjectTS {
    int clickRelPositionX = 50;
    int clickRelPositionY = 50;

    public ClickerTS() {
        super();
        type = TSOConstants.CLICKER_TSOBJID;
    }

    public int getClickRelPositionX() {
        return clickRelPositionX;
    }

    public void setClickRelPositionX(int clickRelPositionX) {
        this.clickRelPositionX = clickRelPositionX;
    }

    public int getClickRelPositionY() {
        return clickRelPositionY;
    }

    public void setClickRelPositionY(int clickRelPositionY) {
        this.clickRelPositionY = clickRelPositionY;
    }

}
