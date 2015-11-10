package com.acme.edu.unit;

import com.acme.edu.exceptions.StateException;
import com.acme.edu.printer.ConsolePrinter;
import com.acme.edu.printer.FilePrinter;
import com.acme.edu.printer.RemotePrinter;
import com.acme.edu.printer.Printer;
import com.acme.edu.exceptions.PrinterException;
import com.acme.edu.SysoutCaptureAndAssertionAbility;
import com.acme.edu.state.State;
import com.acme.edu.state.StateInt;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Created by tan on 05.11.15.
 */
public class PrinterTest implements SysoutCaptureAndAssertionAbility {

    @Test(expected = PrinterException.class, timeout = 5000)
    public void shouldNotPrintInFileWhenFileNotCorrect() throws PrinterException {
        Printer sut = new FilePrinter("ccc/cc", "UTF-8");
        String s ="2";
        for( int i = 0; i < 51; i++)
            sut.print(s);

    }

    @Test(expected = PrinterException.class, timeout = 5000)
    public void shouldNotPrintInFileWhenCharsetNotCorrect() throws PrinterException {
        Printer sut = new FilePrinter("test.txt", "UTF6");
        String s ="2";
        for( int i = 0; i < 51; i++)
            sut.print(s);
    }

    @Test(expected = PrinterException.class, timeout = 5000)
    public void shouldNotPrintInServerWhenServerNotLaunch() throws PrinterException {
        Printer sut = new RemotePrinter();
        for( int i = 0; i < 51; i++)
            sut.print(String.valueOf(i));
    }

    @Test
    public void shouldPrintWhenPrintInConsolePrinter() throws PrinterException {
        captureSysout();
        ConsolePrinter sut= new ConsolePrinter();
        sut.print("TRA");
        assertSysoutContains("TRA");
    }

    @Ignore
    @Test
    public void shouldChangeCollectionInFilePrinterWhenItHavePriority() throws PrinterException {
        Printer sut = new FilePrinter("test.txt", "UTF-8");
        String s ="2";
        String s1 = "Error 2";
        for( int i = 0; i < 51; i++) {
            sut.print(s);
            sut.print(s1);
        }
    }

    @Ignore
    @Test
    public void ShouldWorkInParallelThreads() throws StateException {
        Printer printer = new RemotePrinter();
        Printer printer2 = new RemotePrinter();
        Printer[] printers = {printer,printer2};
        State sut = new StateInt(printers);
        for( int i = 0; i < 51; i++) {
            sut.print("parallel",printers);
            sut.print("parallel2",printer2);
        }
    }
}
