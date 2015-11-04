package com.acme.edu;

/**
 * class for different types of State
 */
public abstract class State {

    /**
     * logging string messages
     * @param message string
     */
    public abstract void log(String message);

    /**
     * change current state to int
     * @return int state
     */
    public abstract State switchToIntState();

    /**
     * change current state to string
     * @return string state
     */
    public abstract State switchToStringState();

    /**
     * change current state to stringArray
     * @return StringArray state
     */
    public abstract State switchToStringArrayState();

    /**
     * change current state to char,reference, boolean
     * @return default state
     */
    public abstract State switchToDefaultState();

    /**
     * clean all messages saved in buffer
     */
    public abstract void flush();

}
