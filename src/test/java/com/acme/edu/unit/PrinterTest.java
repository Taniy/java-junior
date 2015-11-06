package com.acme.edu.unit;

import com.acme.edu.printer.ConsolePrinter;
import com.acme.edu.printer.FilePrinter;
import com.acme.edu.printer.Printer;
import com.acme.edu.PrinterException;
import org.junit.Test;

/**
 * Created by tan on 05.11.15.
 */
public class PrinterTest {
    @Test(expected = PrinterException.class, timeout = 5000)
    public void shouldNotPrintWhenPrintNull() throws PrinterException {
        Printer printer = new ConsolePrinter();
        String s = null;
        printer.print(s);
    }

    @Test
    public void shouldPrintInFile() throws PrinterException {
        Printer printer = new FilePrinter("test.txt", "UTF-8");
        String s ="2";
        printer.print(s);
    }
}
