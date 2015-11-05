package com.acme.edu.unit;

import com.acme.edu.Logger;
import com.acme.edu.MessageNullException;
import org.junit.Test;

/**
 * Created by tan on 05.11.15.
 */
public class LoggerTest {

    @Test(expected = MessageNullException.class, timeout = 5000)
    public void shouldWaitExceptionWhenStringNull() throws MessageNullException {
        Logger logger = new Logger();
        String s = null;
        logger.log(s);
    }

    @Test(expected = MessageNullException.class, timeout = 5000)
    public void shouldWaitExceptionWhenStringArgNull() throws MessageNullException {
        Logger logger = new Logger();
        String[] a = null;
        logger.log(a);
    }


}
