package com.acme.edu;

import com.acme.edu.exceptions.ServerException;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by tan on 10.11.15.
 */
public class MyExecutor implements Runnable {
    private Socket client;
    public static final int MaxOfMessages = 50;
    public static final String ОК = "OK";
    private String path = "server.txt";
    private String charSet = "UTF-8";
    private int counterOfMessages;
    private List<String> buffer  = new ArrayList<>(50);
    public static final String ERROR = "ERROR";
    /**
     * create ClientSocket
     * @param socket
     */
    public MyExecutor(Socket socket) {
        client = socket;
    }

    /**
     * handle messages and printToFile
     */
    @Override
    public void run() {
        try {
            DataInputStream in = new DataInputStream(client.getInputStream());
            DataOutputStream out = new DataOutputStream(client.getOutputStream());
            String readLine;
            while ((readLine = in.readUTF()) != null) {
                printToFile(readLine);
                out.writeUTF(ОК);
                System.out.println(readLine);
            }
        } catch (IOException|ServerException e1) {
            try {
                if (client != null) {
                    DataOutputStream out = new DataOutputStream(client.getOutputStream());
                    out.writeUTF(e1+"");
                    client.close();
                }
            } catch (IOException e) {
                try {
                    throw new ServerException(e);
                } catch (ServerException e2) {
                    e2.printStackTrace();
                }
            }
        }
    }

    /**
     * print received messages to file
     * @param message
     * @throws ServerException
     */
    private void printToFile(String message) throws ServerException {
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
                throw new ServerException(e);
            } catch (IOException e) {
                throw new ServerException(e);
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
