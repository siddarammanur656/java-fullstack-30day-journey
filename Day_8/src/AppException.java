public class AppException extends RuntimeException{

    private final String errorCode;
    private final int    httpStatus;

    public AppException(String message, String errorCode, int httpStatus) {
        super(message);
        this.errorCode  = errorCode;
        this.httpStatus = httpStatus;
    }
}