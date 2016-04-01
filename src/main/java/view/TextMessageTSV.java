package view;

import logic.Caller;
import view.UI.PortPanelFactory;

/**
 * Created by quest on 1/4/16.
 */
public class TextMessageTSV extends SingleFieldObjectTSV {
    public TextMessageTSV(String id, Caller mc, PortPanelFactory portPanelFactory) {
        super(id, mc, portPanelFactory);
    }
}
