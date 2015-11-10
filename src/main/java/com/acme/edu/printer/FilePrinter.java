package com.acme.edu.printer;

import com.acme.edu.exceptions.PrinterException;

import java.io.*;
import java.util.*;

/**
 *Class FilePrinter implements Printer
 * print in File
 */
public class FilePrinter implements Printer {
    public static final int MaxOfMessages = 50;
    private String path;
    private String charSet;
    private int counterOfMessages;
    private List <String> buffer  = new ArrayList<String>(50);
    public static final String ERROR = "ERROR";

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
        buffer.add(message+"\n");
        if (counterOfMessages > MaxOfMessages) {
            Collections.sort(buffer, new Comparator <String>() {
                @Override
                public int compare(String o1, String o2) {
                    if (checkOnPriorityOfCollection(o1) < checkOnPriorityOfCollection(o2))
                        return 1;
                    else if (checkOnPriorityOfCollection(o1) > checkOnPriorityOfCollection(o2))
                            return -1;
                    return 0;
                }
            });
            FileOutputStream file;
            try {
                file = new FileOutputStream(path, true);
                PrintWriter printWriter = new PrintWriter(
                        new OutputStreamWriter(
                                new BufferedOutputStream(file), charSet));
                String str = new String();
                for (String elem: buffer)
                    str +=elem;
                printWriter.write(str);
                printWriter.flush();
            } catch (FileNotFoundException | UnsupportedEncodingException e) {
                throw new PrinterException(e);
            } catch (IOException e) {
                throw new PrinterException(e);
            }
            counterOfMessages = 0;
            buffer.clear();
        }
    }

    private int checkOnPriorityOfCollection(String string) {
        if(string.contains(ERROR))
            return 1;
        return 0;
    }
}
