package com.acme.edu.iteration01;

import com.acme.edu.Logger;
import com.acme.edu.MessageNullException;
import com.acme.edu.PrinterException;
import com.acme.edu.SysoutCaptureAndAssertionAbility;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.*;
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

    @Test
    public void shouldLogInteger() throws IOException, PrinterException {
        //region when
        logger.log(1);
        logger.log(0);
        logger.log(-1);
        logger.log(1);
        logger.log(0);
        logger.log(2);
        logger.log(-3);
        logger.log(-4);
        logger.finish();

        //endregion

        //region then
        assertSysoutContains("primitive: ");
        assertSysoutEquals("primitive: 1"+SEP+"primitive: 0"+SEP +"primitive: 0"+SEP + "primitive: 0"+SEP +"primitive: -5"+SEP);
        //endregion
    }

    @Test
    public void shouldLogByte() throws IOException, PrinterException {
        //region when
        logger.log((byte) 1);
        logger.log((byte) 0);
        logger.finish();

        //endregion

        //region then
        assertSysoutContains("primitive: " + "1" + SEP);
        assertSysoutContains("primitive: "+ "0" + SEP);
        //endregion
    }


  //  TODO: implement Logger solution to match specification as tests

    @Test
    public void shouldLogChar() throws IOException, PrinterException {
        //region when
        logger.log('a');
        logger.log('b');
        logger.finish();

        //endregion

        //region then
        assertSysoutContains("char: ");
        assertSysoutContains("a");
        assertSysoutContains("b");
        //endregion
    }

    @Test
    public void shouldLogString() throws IOException, MessageNullException, PrinterException {
        //region when
        logger.log("test string 1");
        logger.log("other str");
        logger.finish();

        //endregion

        //region then
        assertSysoutContains("string: " + "test string 1");
        assertSysoutContains("string: " + "other str");
        //endregion
    }

    @Test
    public void shouldLogBoolean() throws IOException, PrinterException {
        //region when
        logger.log(true);
        logger.log(false);
        logger.finish();

        //endregion

        //region then
        assertSysoutContains("primitive: ");
        assertSysoutContains("true");
        assertSysoutContains("false");
        //endregion
    }

    @Test
    public void shouldLogReference() throws IOException, MessageNullException, PrinterException {
        //region when
        logger.log(new Object());
        logger.finish();

        //endregion

        //region then
        assertSysoutContains("reference: ");
        assertSysoutContains("@");
        //endregion
    }

}