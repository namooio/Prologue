/*
 * COPYRIGHT (c) NEXTREE Consulting 2014
 * This software is the proprietary of NEXTREE Consulting CO.
 * @since 2014. 6. 10.
 */

package io.namoo.prologue.util.exception;

public class WrongRequestException extends DramaException {
    //
    public WrongRequestException(String message) {
        super(message);
    }

    public WrongRequestException(String message, Developer developer) {
        //
        super(message, developer);
    }
    
    public WrongRequestException(String message, Throwable t) {
        super(message, t);
    }

    public WrongRequestException(Throwable t) {
        super(t);
    }

    public static WrongRequestException newOne(String message, Developer developer) {
        //
        return new WrongRequestException(message, developer);
    }
}