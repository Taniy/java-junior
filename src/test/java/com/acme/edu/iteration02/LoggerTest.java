package com.acme.edu.iteration02;


import com.acme.edu.exceptions.LogException;
import com.acme.edu.Logger;
import com.acme.edu.SysoutCaptureAndAssertionAbility;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
@Ignore

public class LoggerTest implements SysoutCaptureAndAssertionAbility {
    public Logger logger;
    //region given
    @Before
    public void setUpSystemOut() throws IOException {
        resetOut();
        captureSysout();
        logger = new Logger();
    }
    //endregion

    @After
    public void tearDown() {
        resetOut();
    }


   // TODO: implement Logger solution to match specification as tests

    @Test
    public void shouldLogSequentIntegersAsSum() throws LogException {
        //region when
        logger.log("str 1");
        logger.log(1);
        logger.log(2);
        logger.log("str 2");
        logger.log(0);
        logger.finish();

        //endregion

        //region then
        assertSysoutEquals(
                "string: " + "str 1" + SEP +
                        "primitive: " + "3" + SEP +
                        "string: " + "str 2" + SEP +
                        "primitive: " + "0" + SEP
        );
        //endregion
    }

    @Test
    public void shouldLogCorrectlyIntegerOverflowWhenSequentIntegers() throws LogException {
        //region when
        logger.log("str 1");
        logger.log(10);
        logger.log(Integer.MAX_VALUE);
        logger.log("str 2");
        logger.log(0);
        logger.log(Integer.MIN_VALUE);
        logger.finish();

        //endregion

        //region then
        assertSysoutEquals(
            "string: "+"str 1"+ SEP +
            "primitive: "+"10" + SEP +
            "primitive: " +Integer.MAX_VALUE + SEP +
            "string: " + "str 2" + SEP +
            "primitive: " + "0" +SEP +
            "primitive: " + Integer.MIN_VALUE + SEP
        );
        //endregion
    }

    @Test
    public void shouldLogSameSubsequentStringsWithoutRepeat() throws LogException {
        //region when
        logger.log("str 1");
        logger.log("str 2");
        logger.log("str 2");
        logger.log(0);
        logger.log("str 2");
        logger.log("str 3");
        logger.log("str 3");
        logger.log("str 3");
        logger.finish();
        //endregion

        //region then
        assertSysoutEquals(
                "string: " + "str 1" + SEP +
                        "string: " + "str 2 (x2)" + SEP +
                        "primitive: " + "0" + SEP +
                        "string: " + "str 2" + SEP +
                        "string: " + "str 3 (x3)" + SEP
        );
        //endregion
    }
}