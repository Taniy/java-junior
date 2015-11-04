package com.acme.edu.unit;

import com.acme.edu.Printer;
import com.acme.edu.State;
import com.acme.edu.StateInt;
import org.junit.Test;
import static org.mockito.Mockito.*;

/**
 * Created by tan on 03.11.15.
 */
public class StateTest {

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

}
