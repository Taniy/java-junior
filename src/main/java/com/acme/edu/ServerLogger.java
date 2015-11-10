package com.acme.edu;

import com.acme.edu.exceptions.ServerException;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ServerLogger
 * Log received messages to file
 */
public class ServerLogger {
    private ServerSocket sv;
    private ExecutorService pool = Executors.newFixedThreadPool(5);
    public static final int PORT = 9000;


    /**
     * connecting ServerSocket
     * listened and writing messages
     */
    public ServerLogger() throws ServerException {
        try {
            System.out.println("begin");
            sv = new ServerSocket(PORT);
        } catch (IOException e1) {
            throw new ServerException(e1);
        }
    }

    /**
     * Start accept clients
     * @throws ServerException
     */
    public void start() throws ServerException {
        while (true) {
            Socket client;
            try {
                client = sv.accept();
            } catch (IOException e) {
                throw new ServerException(e);
            }
            pool.execute(new MyExecutor(client));
            System.out.println("end");
        }
    }

    /**
     * launch server
     * @param args
     */
    public static void main(String[] args) throws ServerException {
        ServerLogger server = new ServerLogger();
        server.start();
    }

}
