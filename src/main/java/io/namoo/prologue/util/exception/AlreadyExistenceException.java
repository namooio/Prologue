/*
 * COPYRIGHT (c) NEXTREE Consulting 2014
 * This software is the proprietary of NEXTREE Consulting CO.
 * @since 2014. 6. 10.
 */

package io.namoo.prologue.util.exception;

public class AlreadyExistenceException extends DramaException {
    //
    public AlreadyExistenceException(String message) {
        super(message);
    }

    public AlreadyExistenceException(String message, Developer developer) {
        //
        super(message, developer);
    }

    public AlreadyExistenceException(String message, Throwable t) {
        super(message, t);
    }

    public AlreadyExistenceException(Throwable t) {
        super(t);
    }

    public static AlreadyExistenceException newInstance(String message, Developer developer) {
        //
        return new AlreadyExistenceException(message, developer);
    }
}