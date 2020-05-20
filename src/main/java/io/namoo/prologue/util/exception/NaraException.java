package io.namoo.prologue.util.exception;

public class NaraException extends RuntimeException {
    //
    private Developer developer;
    private boolean useMessageCode = true;

    public NaraException(String message, Developer developer) {
        //
        super(message);
        this.developer = developer;
    }

    public NaraException(String message) {
        //
        super(message);
        this.developer = Developer.anonymous();
    }

    public NaraException(String message, Throwable t) {
        //
        super(message, t);
    }

    public NaraException(Throwable t) {
        //
        super(t);
    }

    public static NaraException newOne(String message, Developer developer) {
        //
        return new NaraException(message, developer);
    }

    public static NaraException newOneWithMessage(String message) {
        //
        NaraException naraException = new NaraException(message);
        naraException.setUseMessageCode(false);

        return naraException;
    }

    public static NaraException newOneWithMessage(String message, Throwable t) {
        //
        NaraException naraException = new NaraException(message, t);
        naraException.setUseMessageCode(false);

        return naraException;
    }


    public void setUseMessageCode(boolean useMessageCode) {
        //
        this.useMessageCode = useMessageCode;
    }

    public boolean isUseMessageCode() {
        //
        return useMessageCode;
    }

}
