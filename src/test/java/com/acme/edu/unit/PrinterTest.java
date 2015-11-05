package com.acme.edu.unit;

import com.acme.edu.ConsolePrinter;
import com.acme.edu.Printer;
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
}
