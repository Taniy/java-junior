package com.acme.edu;

/**
 * Created by tan on 02.11.15.
 */
public class StateInt extends State {

    @Override
    public void realize(String message) {
        int number = Integer.valueOf(message);
        if(number == SUM_OF_END_MESSAGE || number == Integer.MAX_VALUE || number == Integer.MIN_VALUE) {
            logSumOfIntInBuf();
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
        return state;
    }

    @Override
    public State switchToStringState() {
        flush();
        state = new StateString();
        return state;
    }

    @Override
    public State switchToCharState() {
        flush();
        state = new StateChar();
        return state;
    }

    @Override
    public State switchToStringArrayState() {
        flush();
        state = new StateStringArray();
        return state;
    }

    @Override
    public State switchToBooleanState() {
        flush();
        state = new StateBoolean();
        return state;
    }

    @Override
    public State switchToReferenceState() {
        flush();
        state = new StateReference();
        return state;
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

    private void flush() {
        logSumOfIntInBuf();
    }
}