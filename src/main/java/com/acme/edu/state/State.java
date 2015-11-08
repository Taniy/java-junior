package com.acme.edu.state;

import com.acme.edu.exceptions.IllegalArgumentException;
import com.acme.edu.exceptions.StateException;

/**
 * class for different types of State
 */
public interface State {

    /**
     * logging string messages
     * @param message string
     */
    void log(String message) throws IllegalArgumentException, StateException;

    /**
     * change to current state
     * @return state
     */
    default State switchToState(State state) throws StateException {
        if (!(state.getClass() == this.getClass())){
            this.flush();
            return state;
        }
        return this;
    }

    /**
     * clean all messages saved in buffer
     */
    void flush() throws StateException;

}
