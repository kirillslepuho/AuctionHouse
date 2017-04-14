package by.epam.auctionhouse.service.validator.exception;

/**
 * Throws when some error occurs in the Validator class.
 *
 * @author Kirill Slepuho
 */
public class ValidatorException extends Exception {
    public ValidatorException() {
        super();
    }

    public ValidatorException(String s) {
        super(s);
    }

    public ValidatorException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public ValidatorException(Throwable throwable) {
        super(throwable);
    }

    public ValidatorException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
