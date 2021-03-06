package by.alenavp.vote.exception;

import org.springframework.http.HttpStatus;

/**
 * @author Alena_Papruha
 * @version 1.0
 * @since 10 Jan, 2021
 */

public enum ErrorType {

    FORBIDDEN_OPERATION("error.forbidden", HttpStatus.FORBIDDEN),
    NOT_FOUND("error.notFound", HttpStatus.NOT_FOUND),
    WRONG_DATA("error.wrongData", HttpStatus.UNPROCESSABLE_ENTITY),
    OPERATION_FAILED("error.failOperation", HttpStatus.UNPROCESSABLE_ENTITY),
    APP_ERROR("error.appError", HttpStatus.INTERNAL_SERVER_ERROR);

    private final String errorCode;
    private final HttpStatus status;

    ErrorType(String errorCode, HttpStatus status) {
        this.errorCode = errorCode;
        this.status = status;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
