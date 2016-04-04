package view;

import logic.Caller;
import view.UI.PortPanelFactory;

/**
 * Created by quest on 1/4/16.
 */
public class TextPrinterTSV extends FixedTextObjectTSV {
    public TextPrinterTSV(String id, Caller mc, PortPanelFactory portPanelFactory) {
        super(id, mc, portPanelFactory);
    }
}
