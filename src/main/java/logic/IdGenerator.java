package logic;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by quest on 16/3/16.
 */
public class IdGenerator {
    int idCounter;
    String macroName;

    public IdGenerator(String _macroName) {
        idCounter = 0;
        macroName = _macroName;
    }

    public String getNextId(Object o) {
        idCounter++;
        String className = o.getClass().getName();
        return macroName+"."+className+"."+idCounter+"."+getCurrentTimeStamp();
    }

    public String getCurrentTimeStamp() {
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyyMMddHHmmssSSS");//dd/MM/yyyy
        Date now = new Date();
        String strDate = sdfDate.format(now);
        return strDate;
    }
}
