package com.acme.edu.printer;

import com.acme.edu.exceptions.PrinterException;

import java.io.*;
import java.net.Socket;

/**
 * RemotePrinter extends Printer
 * pass to ServerLogger messages
 */
public class RemotePrinter implements Printer {
    public static final String OK = "OK";
    private Socket socket;

    /**
     * Constructor RemotePrinter
     * connect with Socket
     * @throws PrinterException
     */
    public RemotePrinter() throws PrinterException {
        try {
            socket = new Socket("127.0.0.1", 9000);
        } catch (IOException e) {
            throw new PrinterException(e);
        }
    }

    /**
     * Print to server messages
     * @param message string
     * @throws PrinterException
     */
    @Override
    public void print(String message) throws PrinterException {
        try {
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            out.writeUTF(message + System.lineSeparator());
            DataInputStream in = new DataInputStream(socket.getInputStream());
            String readLine = in.readUTF();
            if (readLine != null);
                System.out.println(readLine);
                switch (readLine) {
                    case OK:
                        break;
                    default:
                        printerException.setPrinterExceptionList(readLine);
                }
        } catch (IOException e) {
            throw printerException;
        }
    }
}
