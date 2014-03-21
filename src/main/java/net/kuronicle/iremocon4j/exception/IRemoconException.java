package net.kuronicle.iremocon4j.exception;

/**
 * 
 * @author KUROIWA, Keijiro
 * 
 */
public class IRemoconException extends Exception {

    private static final long serialVersionUID = 416080008401664888L;
    
    private String errorCode = "";

    public IRemoconException() {
        super();
    }

    public IRemoconException(String message) {
        super(message);
    }

    public IRemoconException(String message, Throwable cause) {
        super(message, cause);
    }

    public IRemoconException(Throwable cause) {
        super(cause);
    }

    public String getErrorCode() {
        return errorCode;
    }
    
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
