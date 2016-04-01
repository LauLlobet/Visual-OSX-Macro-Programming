package view;

import logic.Caller;
import view.UI.PortPanelFactory;

/**
 * Created by quest on 1/4/16.
 */
public class DelayerTSV extends SingleFieldObjectTSV {
    public DelayerTSV(String id, Caller mc, PortPanelFactory portPanelFactory) {
        super(id, mc, portPanelFactory);
    }
}
