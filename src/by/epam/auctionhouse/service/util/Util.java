package by.epam.auctionhouse.service.util;


public class Util {
	private static final String TRUE = "true";
    private static final String FALSE = "false";
    private static final String YES = "Yes";
    private static final String NO = "No";

	public static final boolean checkBoolean(String bool){
		 boolean rezult;

	        switch (bool) {
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
