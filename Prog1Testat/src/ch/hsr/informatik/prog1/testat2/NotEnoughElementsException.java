package ch.hsr.informatik.prog1.testat2;

public class NotEnoughElementsException extends Exception {

    private static final long serialVersionUID = 8945202023702581823L;

    public NotEnoughElementsException() {
    }

    public NotEnoughElementsException(String aMessage) {
        super(aMessage);
    }

    public NotEnoughElementsException(Throwable aCause) {
        super(aCause);
    }

    public NotEnoughElementsException(String aMessage, Throwable aCause) {
        super(aMessage, aCause);
    }

    public NotEnoughElementsException(String aMessage, Throwable aCause,
            boolean aEnableSuppression, boolean aWritableStackTrace) {
        super(aMessage, aCause, aEnableSuppression, aWritableStackTrace);
    }
}
