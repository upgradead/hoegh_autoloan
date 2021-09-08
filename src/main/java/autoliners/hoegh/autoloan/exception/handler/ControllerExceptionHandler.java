package autoliners.hoegh.autoloan.exception.handler;

import autoliners.hoegh.autoloan.exception.LoanValidationException;
import autoliners.hoegh.autoloan.exception.model.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public ErrorMessage validationExceptionHandler(LoanValidationException e) {
        return new ErrorMessage(HttpStatus.BAD_REQUEST.toString(), e.getMessage());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    public ErrorMessage mainExceptionHandler(Exception e) {
        return new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.toString(),
                "Unexcpected exception " + e.getMessage());
    }
}
