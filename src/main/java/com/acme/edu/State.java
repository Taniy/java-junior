package com.acme.edu;

/**
 * class for different types of State
 */
public interface State {

    /**
     * logging string messages
     * @param message string
     */
    void log(String message) throws PrinterException;

    /**
     * change to current state
     * @return state
     */
    default State switchToState(State state) throws PrinterException {
        if (!(state.getClass() == this.getClass())){
            this.flush();
            return state;
        }
        return this;
    }

    /**
     * clean all messages saved in buffer
     */
    void flush() throws PrinterException;

}
