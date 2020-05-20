/*
 * COPYRIGHT (c) NEXTREE Consulting 2014
 * This software is the proprietary of NEXTREE Consulting CO.
 * @since 2014. 6. 10.
 */

package io.namoo.prologue.util.exception;

public class NoElementException extends DramaException {
    //
    public NoElementException(String message) {
        super(message);
    }

    public NoElementException(String message, Developer developer) {
        //
        super(message, developer);
    }

    public NoElementException(String message, Throwable t) {
        super(message, t);
    }

    public NoElementException(Throwable t) {
        super(t);
    }

    public static NoElementException newInstance(String message, Developer developer) {
        //
        return new NoElementException(message, developer);
    }
}