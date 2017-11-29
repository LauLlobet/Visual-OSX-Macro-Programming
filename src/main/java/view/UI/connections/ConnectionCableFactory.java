package view.UI.connections;

import logic.ConnectionsChecker;
import model.TSObject.TSObjectparts.TSOConnection;
import view.UI.PortPanel;

import java.util.Hashtable;

/**
 * Created by quest on 29/3/16.
 */
public class ConnectionCableFactory {
    public Hashtable<String,PortPanel> panels;

    public ConnectionCableFactory(){
        panels = new Hashtable<String, PortPanel>();
    }

    public ConnectionCable create(TSOConnection conn) {
        PortPanel origin = this.getViewOriginPortPanelForTSOConnection(conn);
        PortPanel destiny = this.getViewDestinyPortPanelForTSOConnection(conn);
        ConnectionCable connection = new ConnectionCable();
        connection.x1 = (float)origin.getLocationOnScreen().getX() + origin.getWidth()/2;
        connection.y1  = (float)origin.getLocationOnScreen().getY() + origin.getHeight();

        connection.x6 = (float)destiny.getLocationOnScreen().getX() + destiny.getWidth()/2;
        connection.y6 = (float)destiny.getLocationOnScreen().getY();
        return connection;
    }

    public void addViewPortPanelToList(PortPanel portPanel) {
        panels.put(portPanel.getViewId(),portPanel);
    }

    public PortPanel getViewDestinyPortPanelForTSOConnection(TSOConnection conn){
        String id = conn.doGetDestinyViewIdFromConnection();
        return panels.get(id);
    }

    public PortPanel getViewOriginPortPanelForTSOConnection(TSOConnection conn){
        String id = conn.doGetOriginViewIdFromConnection();
        return panels.get(id);
    }
}
