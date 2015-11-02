package com.acme.edu.iteration02;

import static com.acme.edu.Logger.*;
import com.acme.edu.SysoutCaptureAndAssertionAbility;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

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


   // TODO: implement Logger solution to match specification as tests

    @Test
    public void shouldLogSequentIntegersAsSum() throws IOException {
        //region when
        log("str 1");
        log(1);
        log(2);
        log("str 2");
        log(0);
        close();

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
    public void shouldLogCorrectlyIntegerOverflowWhenSequentIntegers() {
        //region when
        log("str 1");
        log(10);
        log(Integer.MAX_VALUE);
        log("str 2");
        log(0);
        log(Integer.MIN_VALUE);
        close();

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
    public void shouldLogCorrectlyByteOverflowWhenSequentBytes() {
        //region when
        log("str 1");
        log((byte)10);
        log((byte)Byte.MAX_VALUE);
        log("str 2");
        log(0);
        close();

        //endregion

        //region then
        assertSysoutEquals(
                "string: " + "str 1" + SEP +
                        "primitive: " + "10" + SEP +
                        "primitive: " + Byte.MAX_VALUE + SEP +
                        "string: " + "str 2" + SEP +
                        "primitive: " + "0" + SEP
        );
        //endregion
    }

    @Test
    public void shouldLogSameSubsequentStringsWithoutRepeat() throws IOException {
        //region when
        log("str 1");
        log("str 2");
        log("str 2");
        log(0);
        log("str 2");
        log("str 3");
        log("str 3");
        log("str 3");
        close();
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