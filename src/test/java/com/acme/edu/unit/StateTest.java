package com.acme.edu.unit;

import com.acme.edu.*;
import org.junit.Assert;
import org.junit.Test;
import static org.mockito.Mockito.*;
import com.acme.edu.SysoutCaptureAndAssertionAbility;

/**
 * Created by tan on 03.11.15.
 */
public class StateTest implements SysoutCaptureAndAssertionAbility {

    @Test
    public void shouldNotPrintSumWhenLogValuesAndZero() {
        Printer mok = mock(Printer.class);
        State sut = new StateInt(mok);

        sut.log("5");
        sut.log("0");

        verify(mok, times(1)).print("primitive: 5");
        verify(mok, times(1)).print("primitive: 0");
    }

    @Test
    public void shouldPrintMaxAndMinWhenLogMaxMinValues() {
        Printer mok = mock(Printer.class);
        State sut = new StateInt(mok);

        sut.log(String.valueOf(Integer.MAX_VALUE));
        sut.log(String.valueOf(Integer.MIN_VALUE));

        verify(mok, times(1)).print("primitive: " + Integer.MAX_VALUE);
        verify(mok, times(1)).print("primitive: " + Integer.MIN_VALUE);
    }

    @Test
    public void shouldPrintSumWhenLogSeveralValues() {
        Printer mok = mock(Printer.class);
        State sut = new StateInt(mok);

        sut.log("2");
        sut.log("3");
        sut.log("4");
        sut.flush();

        verify(mok, times(1)).print("primitive: 9");
    }

    @Test
    public void shouldPrintSeparateValuesWhenOverflowMaxInteger() {
        Printer mok = mock(Printer.class);
        State sut = new StateInt(mok);

        sut.log(String.valueOf(Integer.MAX_VALUE-11));
        sut.log("12");
        sut.flush();

        verify(mok, times(1)).print("primitive: " + String.valueOf(Integer.MAX_VALUE-11));
        verify(mok, times(1)).print("primitive: 12");
    }

    @Test
    public void shouldPrintSeparateValuesWhenOverflowMinInteger() {
        Printer mok = mock(Printer.class);
        State sut = new StateInt(mok);

        sut.log(String.valueOf(Integer.MIN_VALUE+11));
        sut.log("-12");
        sut.flush();

        verify(mok, times(1)).print("primitive: " + String.valueOf(Integer.MIN_VALUE+11));
        verify(mok, times(1)).print("primitive: -12");
    }

    @Test
    public void shouldSwitchToIntState() {
        Printer mok = mock(Printer.class);
        State sut = new StateInt(mok);

        Assert.assertEquals(sut,sut.switchToIntState());
    }

        @Test
    public void shouldSwitchToDefaultState() {
        Printer mok = mock(Printer.class);
        State sut = new StateDefault(mok);

        Assert.assertEquals(sut,sut.switchToDefaultState());
    }

    @Test
    public void shouldSwitchToStringState() {
        Printer mok = mock(Printer.class);
        State sut = new StateString(mok);

        Assert.assertEquals(sut,sut.switchToStringState());
    }

    @Test
    public void shouldSwitchToStringArrayState() {
        Printer mok = mock(Printer.class);
        State sut = new StateStringArray(mok);

        Assert.assertEquals(sut,sut.switchToStringArrayState());
    }

    @Test
    public void shouldPrintStringWhenLogString() {
        Printer mok = mock(Printer.class);
        State sut = new StateString(mok);

        sut.log("str1");
        sut.flush();

        verify(mok, times(1)).print("string: str1");
    }

    @Test
    public void shouldPrintSumStrinsWhenLogSeveralStrings() {
        Printer mok = mock(Printer.class);
        State sut = new StateString(mok);

        sut.log("str1");
        sut.log("str1");
        sut.flush();

        verify(mok, times(1)).print("string: str1 (x2)");
    }

    @Test
    public void shouldPrintCharBooleanReferenceWhenLogCharBooleanReference() {
        Printer mok = mock(Printer.class);
        State sut = new StateDefault(mok);

        sut.log("primitive: true");
        sut.log("char: a");
        sut.log("reference: @");
        sut.flush();

        verify(mok, times(1)).print("primitive: true");
        verify(mok, times(1)).print("char: a");
        verify(mok, times(1)).print("reference: @");

    }

    @Test
    public void shouldPrintStringArraywhenLogStringArray() {
        Printer mok = mock(Printer.class);
        State sut = new StateStringArray(mok);

        sut.log("[str2, str3, str]");
        sut.flush();

        verify(mok, times(1)).print("str2"+ Logger.SEP + "str3" + Logger.SEP + "str");
    }

    @Test
    public void shouldPrintWhenPrintInConsolePrinter() {
        captureSysout();
        ConsolePrinter sut= new ConsolePrinter();
        sut.print("TRA");
        assertSysoutContains("TRA");
        }
}
