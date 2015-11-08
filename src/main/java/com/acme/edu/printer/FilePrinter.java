package com.acme.edu.printer;

import com.acme.edu.exceptions.PrinterException;

import java.io.*;

/**
 *Class FilePrinter implements Printer
 * print in File
 */
public class FilePrinter implements Printer {
    public static final int MaxOfMessages = 50;
    private String path;
    private String charSet;
    private int counterOfMessages;
    private StringBuilder builder = new StringBuilder();

    /**
     * constructor of Printer
     * @param path String. Print in filepath
     * @param charSet String. Charset of file
     */
    public FilePrinter(String path, String charSet) {
        this.path = path;
        this.charSet = charSet;
    }

    /**
     * print in file when buf are full
     * @param message string
     * @throws PrinterException
     */
    @Override
    public void print(String message) throws PrinterException {
        counterOfMessages++;
        builder.append(message + File.separator);
        if (counterOfMessages > MaxOfMessages) {
            FileOutputStream file;
            try {
                file = new FileOutputStream(path, true);
                PrintWriter printWriter = new PrintWriter(
                        new OutputStreamWriter(
                                new BufferedOutputStream(file), charSet));
                printWriter.write(builder.toString());
                printWriter.flush();
            } catch (FileNotFoundException | UnsupportedEncodingException e) {
                printerException.setPrinterExceptionList(e.toString());
                throw printerException;
            } catch (IOException e) {
                printerException.setPrinterExceptionList(e.toString());
                throw printerException;
            }
            counterOfMessages = 0;
            builder.delete(0,builder.length());
        }
    }
}
