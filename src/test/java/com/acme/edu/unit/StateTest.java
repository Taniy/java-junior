package com.acme.edu.unit;

import com.acme.edu.exceptions.IllegalArgumentException;
import com.acme.edu.exceptions.LogException;
import com.acme.edu.printer.ConsolePrinter;
import com.acme.edu.printer.FilePrinter;
import com.acme.edu.printer.Printer;
import com.acme.edu.printer.RemotePrinter;
import com.acme.edu.state.*;
import org.junit.Assert;
import org.junit.Test;

import static org.mockito.Mockito.*;

/**
 * Created by tan on 03.11.15.
 */
public class StateTest {

    public static final String LINE_SEPARATOR = System.lineSeparator();

    @Test
    public void shouldNotPrintSumWhenLogValuesAndZero() throws LogException {
        Printer mok = mock(Printer.class);
        State sut = new StateInt(mok);

        sut.log("5");
        sut.log("0");

        verify(mok, times(1)).print("primitive: 5");
        verify(mok, times(1)).print("primitive: 0");
    }

    @Test
    public void shouldPrintMaxAndMinWhenLogMaxMinValues() throws LogException {
        Printer mok = mock(Printer.class);
        State sut = new StateInt(mok);

        sut.log(String.valueOf(Integer.MAX_VALUE));
        sut.log(String.valueOf(Integer.MIN_VALUE));

        verify(mok, times(1)).print("primitive: " + Integer.MAX_VALUE);
        verify(mok, times(1)).print("primitive: " + Integer.MIN_VALUE);
    }

    @Test
    public void shouldPrintSumWhenLogSeveralValues() throws LogException {
        Printer mok = mock(Printer.class);
        State sut = new StateInt(mok);

        sut.log("2");
        sut.log("3");
        sut.log("4");
        sut.flush();

        verify(mok, times(1)).print("primitive: 9");
    }

    @Test
    public void shouldPrintSeparateValuesWhenOverflowMaxInteger() throws LogException{
        Printer mok = mock(Printer.class);
        State sut = new StateInt(mok);

        sut.log(String.valueOf(Integer.MAX_VALUE-11));
        sut.log("12");
        sut.flush();

        verify(mok, times(1)).print("primitive: " + String.valueOf(Integer.MAX_VALUE-11));
        verify(mok, times(1)).print("primitive: 12");
    }

    @Test
    public void shouldPrintSeparateValuesWhenOverflowMinInteger() throws LogException {
        Printer mok = mock(Printer.class);
        State sut = new StateInt(mok);

        sut.log(String.valueOf(Integer.MIN_VALUE + 11));
        sut.log("-12");
        sut.flush();

        verify(mok, times(1)).print("primitive: " + String.valueOf(Integer.MIN_VALUE + 11));
        verify(mok, times(1)).print("primitive: -12");
    }

    @Test
    public void shouldSwitchToIntState() throws LogException {
        Printer mok = mock(Printer.class);
        State sut = new StateInt(mok);

        Assert.assertEquals(sut,sut.switchToState(sut));
    }

        @Test
    public void shouldSwitchToDefaultState() throws LogException {
        Printer mok = mock(Printer.class);
        State sut = new StateDefault(mok);

        Assert.assertEquals(sut,sut.switchToState(sut));
    }

    @Test
    public void shouldSwitchToStringState() throws LogException {
        Printer mok = mock(Printer.class);
        State sut = new StateString(mok);

        Assert.assertEquals(sut,sut.switchToState(sut));
    }

    @Test
    public void shouldSwitchToStringArrayState() throws LogException {
        Printer mok = mock(Printer.class);
        State sut = new StateStringArray(mok);

        Assert.assertEquals(sut,sut.switchToState(sut));
    }

    @Test
    public void shouldPrintStringWhenLogString() throws LogException {
        Printer mok = mock(Printer.class);
        State sut = new StateString(mok);

        sut.log("str1");
        sut.flush();

        verify(mok, times(1)).print("string: str1");
    }

    @Test
    public void shouldPrintSumStrinsWhenLogSeveralStrings() throws LogException {
        Printer mok = mock(Printer.class);
        State sut = new StateString(mok);

        sut.log("str1");
        sut.log("str1");
        sut.flush();

        verify(mok, times(1)).print("string: str1 (x2)");
    }

    @Test
    public void shouldPrintCharBooleanReferenceWhenLogCharBooleanReference() throws LogException {
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
    public void shouldPrintStringArraywhenLogStringArray() throws LogException {
        Printer mok = mock(Printer.class);
        State sut = new StateStringArray(mok);

        sut.log("[str2, str3, str]");
        sut.flush();

        verify(mok, times(1)).print("str2"+ LINE_SEPARATOR + "str3" +  LINE_SEPARATOR + "str");
    }
}
