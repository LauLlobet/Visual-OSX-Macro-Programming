package logic;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by quest on 1/4/16.
 */
public class KeyboardTest {

    @Test
    public void testType() throws Exception {
        Keyboard key = new Keyboard();
        Thread.sleep(1000);
        key.type("codetofind\\\\cmd\\\\r\\\\cmdend\\\\code");
    }
}