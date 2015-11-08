package com.acme.edu.state;

import com.acme.edu.exceptions.IllegalArgumentException;
import com.acme.edu.printer.Printer;
import com.acme.edu.exceptions.PrinterException;

/**
 * Class StateInt implements State
 * logging fo type int
 */
public class StateInt implements State {
    private static final String PRIMITIVE = "primitive: ";
    private static final int SUM_OF_END_MESSAGE = 0;
    private Integer sumInt = null;
    private Printer[] printers;

    /**
     * pass Printer to StateInt
     * @param printers
     */
    public StateInt(Printer... printers) {
        this.printers = printers;
    }

    /**
     * count sum of int messages
     * and print them
     * @param message string
     */
    @Override
    public void log(String message) throws IllegalArgumentException {
        int number = Integer.parseInt(message);
        if(number == SUM_OF_END_MESSAGE || number == Integer.MAX_VALUE || number == Integer.MIN_VALUE) {
            flush();
            printToPrinter(PRIMITIVE + number);
        } else if ((sumInt != null) && (checkOnOverFlowMaxValue(number)||checkOnOverFlowMinValue(number))) {
            printToPrinter(PRIMITIVE + sumInt);
            sumInt = number;
        } else  {
            if (sumInt == null) {
                sumInt = 0;
            }
            sumInt =  sumInt + number;
        }
    }

    @Override
    public void flush() throws IllegalArgumentException {
        if (sumInt == null)
            return;
        printToPrinter(PRIMITIVE + sumInt);
        sumInt = null;
    }

    private boolean checkOnOverFlowMaxValue(int  num) {
        boolean flag = false;
        if ((sumInt > 0) && (num > 0) && (sumInt > Integer.MAX_VALUE - num))
            flag = true;
        return flag;
    }

    private boolean checkOnOverFlowMinValue(int num) {
        boolean flag = false;
        if ((num < 0) && (sumInt < 0) && (sumInt < Integer.MIN_VALUE - num))
            flag = true;
        return flag;
    }

    private void printToPrinter(String message) throws IllegalArgumentException {
        for(Printer printer: printers)
            try {
                printer.print(message);
            } catch (PrinterException e) {
                throw new IllegalArgumentException(e);
            }
    }
}