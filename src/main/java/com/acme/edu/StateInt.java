package com.acme.edu;

/**
 * Created by tan on 02.11.15.
 */
public class StateInt extends State {

    public StateInt() {
        logSumOfStringsInBuf();
    }

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


    private static boolean checkOnOverFlowMaxValue(int  num) {
        boolean flag = false;
        if ((sumInt > 0) && (num > 0) && (sumInt > Integer.MAX_VALUE - num))
            flag = true;
        return flag;
    }

    private static boolean checkOnOverFlowMinValue(int num) {
        boolean flag = false;
        if ((num < 0) && (sumInt < 0) && (sumInt < Integer.MIN_VALUE - num))
            flag = true;
        return flag;
    }

}