package com.acme.edu.iteration03;

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

    //TODO: implement Logger solution to match specification as tests

    @Test
    public void shouldLogIntegersArray() throws LogException {
        //region when
        logger.log(new int[]{-1, 0, 1});
        logger.finish();
        //endregion

        //region then
        assertSysoutEquals(
                "primitives array: 0" + SEP
        );
        //endregion
    }

    @Test
    public void shouldLogIntegersMatrix() throws LogException {
        //region when
        logger.log(new int[][]{{-1, 0, 1}, {1, 2, 3}, {-1, -2, -3}});
        logger.finish();
        //endregion

        //region then
        assertSysoutEquals(
                "primitives matrix: {" + SEP +
                        "{-1, 0, 1}" + SEP +
                        "{1, 2, 3}" + SEP +
                        "{-1, -2, -3}" + SEP +
                        "}" + SEP
        );
        //endregion
    }

    @Test
    public void shouldLogIntegersMulitidimentionalArray() throws LogException {
        //region when
        logger.log(new int[][][][]{{{{0}}}});
        logger.finish();
        //endregion

        //region then
        assertSysoutEquals(
                "primitives multimatrix: {" + SEP +
                        "{" + SEP + "{" + SEP + "{" +
                        "0" +
                        "}" + SEP + "}" + SEP + "}" + SEP +
                        "}" + SEP
        );
        //endregion
    }


    @Test
    public void shouldLogStringsWithOneMethodCall() throws LogException {
        //region when
        logger.log("str1", "string 2", "str 3");
        logger.finish();
        //endregion

        //region then
        assertSysoutContains("str1" + SEP + "string 2"+ SEP + "str 3");
        //endregion
    }

    @Test
    public void shouldLogIntegersWithOneMethodCall() throws LogException {
        //region when
        logger.log(-1, 0, 1, 3);
        logger.finish();
        //endregion

        //region then
        assertSysoutContains("3");
        //endregion
    }

    @Test
    public void shouldCorrectDealWithIntegerOverflowWhenOneMethodCall() throws LogException {
        //region when
        logger.log(1);
        logger.log("str");
        logger.log(Integer.MAX_VALUE - 10);
        logger.log(11);
        logger.log("str1");
        logger.log(Integer.MIN_VALUE + 10);
        logger.log(-11);
        logger.log("str2");
        logger.log(-6);
        logger.log(Integer.MIN_VALUE + 5);
        logger.log("str3");
        logger.log(6);
        logger.log(Integer.MAX_VALUE - 5);
        logger.finish();
        //endregion

        //region then
        assertSysoutContains("primitive: " + "1");
        assertSysoutContains("string: " + "str");
        assertSysoutContains("primitive: " + (Integer.MAX_VALUE - 10));
        assertSysoutContains("primitive: " + 11);
        assertSysoutContains("string: " + "str1");
        assertSysoutContains("primitive: " + (Integer.MIN_VALUE + 10));
        assertSysoutContains("primitive: " + -11);
        assertSysoutContains("string: " + "str2");
        assertSysoutContains("primitive: " + -6);
        assertSysoutContains("primitive: " + (Integer.MIN_VALUE + 5));
        assertSysoutContains("string: " + "str3");
        assertSysoutContains("primitive: " + 6);
        assertSysoutContains("primitive: " + (Integer.MAX_VALUE - 5));

        //endregion
    }
}