package by.epam.auctionhouse.service.util.error;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;


public class MessageManager {
	private static final String LOCALE = "local";
	private static final String LOCALE_EN = "localization.local_en";
	private static final String LOCALE_RU = "localization.local_ru";
	private static final String EN = "en";

	private static final int WRONG_DATA_CODE = 1;
	private static final int ERROR_SHORT_PASSWORD_CODE = 2;
	private static final int ERROR_LONG_PASSWORD_CODE = 3;
	private static final int ERROR_EMAIL_CODE = 4;
	private static final int ERROR_BET_CODE = 5;
	private static final int PASSWORD_NOT_EQUALS_CODE = 6;
	private static final int SIGN_IN_ERROR_CODE = 7;
	private static final int REGISTRATION_ERROR_CODE = 8;
	private static final int WRONG_NUMBER_FORMAT_CODE = 9;
	private static final int ERROR_CODE = 10;
	private static final int ERROR_USER_RIGHTS_CODE = 11;
	
	private static final String WRONG_DATA_MESSAGE = "local.wrong_data";
	private static final String ERROR_SHORT_PASSWORD_MESSAGE = "local.password_short_error";
	private static final String ERROR_LONG_PASSWORD_MESSAGE = "local.password_long_error";
	private static final String ERROR_EMAIL_MESSAGE = "local.email_error";
	private static final String ERROR_BET_MESSAGE = "local.bet_error";
	private static final String PASSWORD_NOT_EQUALS_MESSAGE = "local.not_equals_password";
	private static final String SIGN_IN_ERROR_MESSAGE = "local.sign_in_error";
	private static final String REGISTRATION_ERROR_MESSAGE = "local.registration_error";
	private static final String WRONG_NUMBER_FORMAT_MESSAGE = "local.wrong_number_format";
	private static final String ERROR_MESSAGE = "local.error";
	private static final String ERROR_USER_RIGHTS_MESSAGE = "local.error_user_rights";
	
	private static Map<Integer,String> errorMessages = new HashMap<>();

	static{
		errorMessages.put(WRONG_DATA_CODE, WRONG_DATA_MESSAGE);
		errorMessages.put(ERROR_SHORT_PASSWORD_CODE, ERROR_SHORT_PASSWORD_MESSAGE);
		errorMessages.put(ERROR_LONG_PASSWORD_CODE, ERROR_LONG_PASSWORD_MESSAGE);
		errorMessages.put(ERROR_EMAIL_CODE, ERROR_EMAIL_MESSAGE);
		errorMessages.put(ERROR_BET_CODE, ERROR_BET_MESSAGE);
		errorMessages.put(PASSWORD_NOT_EQUALS_CODE, PASSWORD_NOT_EQUALS_MESSAGE);
		errorMessages.put(SIGN_IN_ERROR_CODE,SIGN_IN_ERROR_MESSAGE);
		errorMessages.put(REGISTRATION_ERROR_CODE, REGISTRATION_ERROR_MESSAGE);
		errorMessages.put(WRONG_NUMBER_FORMAT_CODE, WRONG_NUMBER_FORMAT_MESSAGE);
		errorMessages.put(ERROR_CODE, ERROR_MESSAGE);
		errorMessages.put(ERROR_USER_RIGHTS_CODE, ERROR_USER_RIGHTS_MESSAGE);
	}

	public static String getErrorMessage(HttpServletRequest httpServletRequest,int errorCode) {
		String message;


		try{
			String errorLocaleMessage = errorMessages.get(Integer.valueOf(errorCode));
			message = ResourceBundle.getBundle(getBundleName(httpServletRequest)).getString(errorLocaleMessage);
		}catch (IllegalArgumentException | NullPointerException e) {
			String errorLocaleMessage = errorMessages.get(ERROR_CODE);
			message = ResourceBundle.getBundle(getBundleName(httpServletRequest)).getString(errorLocaleMessage);
		}
		
		return message;
	}

	private static String getBundleName(HttpServletRequest httpServletRequest) {
		if (httpServletRequest.getSession().getAttribute(LOCALE) == null) {
			return LOCALE_EN;
		} else {
			String locale = httpServletRequest.getSession().getAttribute(LOCALE).toString();
			if (locale.equalsIgnoreCase(EN)) {
				return LOCALE_EN;
			} else {
				return LOCALE_RU;
			}
		}
	}
}
