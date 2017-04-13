package by.epam.auctionhouse.service.util;


public class Util {
	private static final String TRUE = "TRUE";
    private static final String FALSE = "FALSE";
    private static final String YES = "YES";
    private static final String NO = "NO";

    /**
     * Returns converted to boolean type value.
     *
     * @param value String value
     * @return boolean value
     */
	public static final boolean checkBoolean(String bool){
		 boolean rezult;

	        switch (bool.toUpperCase()) {
	            case TRUE:
	                rezult = true;
	                break;
	            case FALSE:
	                rezult = false;
	                break;
	            case YES:
	                rezult = true;
	                break;
	            case NO:
	                rezult = false;
	                break;
	            default:
	                rezult = false;
	                break;
	        }
	        return rezult;
	}
	
}
