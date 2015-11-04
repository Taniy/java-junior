package com.acme.edu;

/**
 * Created by tan on 02.11.15.
 */
public class StateInt extends State {
    private static final String PRIMITIVE = "primitive: ";
    private static final int SUM_OF_END_MESSAGE = 0;
    private Integer sumInt = null;

    @Override
    public void log(String message) {
        int number = Integer.parseInt(message);
        if(number == SUM_OF_END_MESSAGE || number == Integer.MAX_VALUE || number == Integer.MIN_VALUE) {
            flush();
            printer.print(PRIMITIVE + number);
        } else if ((sumInt != null) && (checkOnOverFlowMaxValue(number)||checkOnOverFlowMinValue(number))) {
            printer.print(PRIMITIVE + sumInt);
            sumInt = number;
        } else  {
            if (sumInt == null) {
                sumInt = 0;
            }
            sumInt =  sumInt + number;
        }
    }

    @Override
    public State switchToIntState() {
        return this;
    }

    @Override
    public State switchToStringState() {
        flush();
        return new StateString();
    }

    @Override
    public State switchToStringArrayState() {
        flush();
        return new StateStringArray();
    }

    @Override
    public State switchToDefaultState() {
        return new StateDefault();
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

    public void flush() {
            if (sumInt == null)
                return;
            printer.print(PRIMITIVE + sumInt);
            sumInt = null;
    }
}