/*
 * COPYRIGHT (c) NEXTREE Consulting 2014
 * This software is the proprietary of NEXTREE Consulting CO.
 * @since 2014. 6. 10.
 */

package io.namoo.prologue.util.exception;

public class DramaException extends RuntimeException {
    //
    private Developer developer;

    public DramaException(String message, Developer developer) {
        //
        super(message);
        this.developer = developer;
    }

    public DramaException(String message) {
        //
        super(message);
        this.developer = Developer.anonymous();
    }

    public DramaException(String message, Throwable t) {
        super(message, t);
    }

    public DramaException(Throwable t) {
        super(t);
    }

    public static DramaException newOne(String message, Developer developer) {
        //
        return new DramaException(message, developer);
    }
}