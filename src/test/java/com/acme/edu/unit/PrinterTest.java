package com.acme.edu.unit;

import com.acme.edu.exceptions.LogException;
import com.acme.edu.printer.ConsolePrinter;
import com.acme.edu.printer.FilePrinter;
import com.acme.edu.printer.RemotePrinter;
import com.acme.edu.printer.Printer;
import com.acme.edu.exceptions.PrinterException;
import com.acme.edu.SysoutCaptureAndAssertionAbility;
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
    public void shouldPrintWhenPrintInConsolePrinter() throws LogException {
        captureSysout();
        ConsolePrinter sut= new ConsolePrinter();
        sut.print("TRA");
        assertSysoutContains("TRA");
    }
}
