package com.acme.edu;

/**
 * Created by tan on 03.11.15.
 */
public class ConsolePrinter implements Printer {
    @Override
    public void print(String message) {
            System.out.println(message);
    }
}
