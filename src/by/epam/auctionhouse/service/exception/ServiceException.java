package by.epam.auctionhouse.service.exception;

public class ServiceException extends Exception {
	private static final long serialVersionUID = 1L;
	private int errorKey;

	public ServiceException() {
		super();
	}

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(Exception e) {
		super(e);
	}
	
	
	
	public ServiceException(int errorCode){
		this.errorKey = errorCode;
	}

	public ServiceException(String message, Exception e) {
		super(message, e);
	}
	
	public ServiceException(int errorCode, Exception e){
		super(e);
		this.errorKey = errorCode;
	}
	public ServiceException(int errorCode, String message){
		super(message);
		this.errorKey = errorCode;
	}
	
	public ServiceException(int errorCode, Exception e, String message){
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
