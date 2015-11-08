package com.acme.edu;

import com.acme.edu.exceptions.ServerException;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * ServerLogger
 * Log received messages to file
 */
public class ServerLogger {

    public static final int MaxOfMessages = 50;
    public static final String ОК = "OK";
    public static final int PORT = 9000;
    private String path = "server.txt";
    private String charSet = "UTF-8";
    private int counterOfMessages;
    private StringBuilder builder = new StringBuilder();

    /**
     * connecting ServerSocket
     * listened and writing messages
     */
    public ServerLogger() throws ServerException {
        ServerSocket sv;
        Socket client = null;
        try {
            sv = new ServerSocket(PORT);
            client = sv.accept();
            DataInputStream in = new DataInputStream(client.getInputStream());
            DataOutputStream out = new DataOutputStream(client.getOutputStream());
            String readLine;
            int i = 0;
            while ((readLine = in.readUTF()) != null) {
                printToFile(readLine);
                out.writeUTF(ОК);
                System.out.println(readLine);
            };
            in.close();
            out.close();
            sv.close();
        } catch (IOException|ServerException e1) {
            try {
                if (client != null) {
                    DataOutputStream out = new DataOutputStream(client.getOutputStream());
                    out.writeUTF(e1+"");
                    client.close();
                }
            } catch (IOException e) {
                throw new ServerException(e);
            }
        }
    }

    /**
     * print received messages to file
     * @param message
     * @throws ServerException
     */
    public void printToFile(String message) throws ServerException {
        counterOfMessages++;
        builder.append(message);
        if (counterOfMessages > MaxOfMessages) {
            FileOutputStream file;
            try {
                file = new FileOutputStream(path, true);
                PrintWriter printWriter = new PrintWriter(
                        new OutputStreamWriter(
                                new BufferedOutputStream(file), charSet));
                printWriter.write(builder.toString());
                printWriter.flush();
            } catch (IOException e) {
                throw new ServerException(e);
            }
            counterOfMessages = 0;
            builder.delete(0,builder.length());
        }
    }

    /**
     * launch server
     * @param args
     */
    public static void main(String[] args) throws ServerException {
        ServerLogger serverLogger = new ServerLogger();
    }

}
