package by.epam.auctionhouse.service.validator.exception;

/**
 * Throws when some error occurs in the Validator class.
 *
 * @author Kirill Slepuho
 */
public class ValidatorException extends Exception {
	private static final long serialVersionUID = 1L;
	private int errorKey;
	
	public ValidatorException() {
        super();
    }

    public ValidatorException(String s) {
        super(s);
    }
    
    public ValidatorException(int errorCode){
    	this.errorKey = errorCode;
    }

    public ValidatorException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public ValidatorException(Throwable throwable) {
        super(throwable);
    }

    public ValidatorException(int errorCode, Throwable throwable){
		super(throwable);
		this.errorKey = errorCode;
	}
	public ValidatorException(int errorCode, String message){
		super(message);
		this.errorKey = errorCode;
	}
	
	public ValidatorException(int errorCode, Exception e, String message){
		super(message,e);
		this.errorKey = errorCode;
	}
    
	public int getErrorKey() {
		return errorKey;
	}

	public void setErrorKey(int errorKey) {
		this.errorKey = errorKey;
	}
}
