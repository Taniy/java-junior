package com.acme.edu.iteration01;

import static com.acme.edu.Logger.*;
import com.acme.edu.SysoutCaptureAndAssertionAbility;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

public class LoggerTest implements SysoutCaptureAndAssertionAbility {
    //region given
    @Before
    public void setUpSystemOut() throws IOException {
        resetOut();
        captureSysout();
    }
    //endregion

    @After
    public void tearDown() {
        resetOut();
    }

    @Test
    public void shouldLogInteger() throws IOException {
        //region when
        log(1);
        log(0);
        log(-1);
        log(1);
        log(0);
        log(2);
        log(-3);
        log(-4);
        close();

        //endregion

        //region then
        assertSysoutContains("primitive: ");
        assertSysoutEquals("primitive: 1"+SEP+"primitive: 0"+SEP +"primitive: 0"+SEP + "primitive: 0"+SEP +"primitive: -5"+SEP);
        //endregion
    }

    @Test
    public void shouldLogByte() throws IOException {
        //region when
        log((byte)1);
        log((byte)0);
        close();

        //endregion

        //region then
        assertSysoutContains("primitive: " + "1" + SEP);
        assertSysoutContains("primitive: "+ "0" + SEP);
        //endregion
    }


  //  TODO: implement Logger solution to match specification as tests

    @Test
    public void shouldLogChar() throws IOException {
        //region when
        log('a');
        log('b');
        close();

        //endregion

        //region then
        assertSysoutContains("char: ");
        assertSysoutContains("a");
        assertSysoutContains("b");
        //endregion
    }

    @Test
    public void shouldLogString() throws IOException {
        //region when
        log("test string 1");
        log("other str");
        close();

        //endregion

        //region then
        assertSysoutContains("string: " + "test string 1");
        assertSysoutContains("string: " + "other str");
        //endregion
    }

    @Test
    public void shouldLogBoolean() throws IOException {
        //region when
        log(true);
        log(false);
        close();

        //endregion

        //region then
        assertSysoutContains("primitive: ");
        assertSysoutContains("true");
        assertSysoutContains("false");
        //endregion
    }

    @Test
    public void shouldLogReference() throws IOException {
        //region when
        log(new Object());
        close();

        //endregion

        //region then
        assertSysoutContains("reference: ");
        assertSysoutContains("@");
        //endregion
    }
}