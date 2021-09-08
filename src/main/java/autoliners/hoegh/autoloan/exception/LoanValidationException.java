package autoliners.hoegh.autoloan.exception;

public class LoanValidationException extends RuntimeException{

    private String message;

    public LoanValidationException(String message){
        this.message = message;
    }
}
