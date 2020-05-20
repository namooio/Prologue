/*
 * COPYRIGHT (c) NEXTREE Consulting 2014
 * This software is the proprietary of NEXTREE Consulting CO.
 * @since 2014. 6. 10.
 */

package io.namoo.prologue.util.exception;

public class ArgumentException extends DramaException {
    //
    public ArgumentException(String message) {
        super(message);
    }

    public ArgumentException(String message, Developer developer) {
        //
        super(message, developer);
    }
    
    public ArgumentException(String message, Throwable t) {
        super(message, t);
    }

    public ArgumentException(Throwable t) {
        super(t);
    }

    public static ArgumentException newOne(String message, Developer developer) {
        //
        return new ArgumentException(message, developer);
    }
}