package com.acme.edu.unit;

import com.acme.edu.exceptions.LogException;
import com.acme.edu.Logger;
import org.junit.Test;

/**
 * Created by tan on 05.11.15.
 */
public class LoggerTest {

    @Test(expected = LogException.class, timeout = 5000)
    public void shouldWaitExceptionWhenStringNull() throws LogException {
        Logger logger = new Logger();
        String s = null;
        logger.log(s);
    }

    @Test(expected = LogException.class, timeout = 5000)
    public void shouldWaitExceptionWhenStringArgNull() throws LogException {
        Logger logger = new Logger();
        String[] a = null;
        logger.log(a);
    }


}
