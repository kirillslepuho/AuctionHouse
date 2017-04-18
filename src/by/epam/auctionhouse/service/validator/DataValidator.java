package by.epam.auctionhouse.service.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import by.epam.auctionhouse.bean.Lot;
import by.epam.auctionhouse.dao.UserDAO;
import by.epam.auctionhouse.dao.exception.DAOException;
import by.epam.auctionhouse.dao.factory.DAOFactory;
import by.epam.auctionhouse.service.validator.exception.ValidatorException;

/**
 * Provides methods to validate request parameters.
 *
 * @author Kirill Slepuho
 */
public class DataValidator {

	private static final int WRONG_DATA_CODE = 1;
	private static final int ERROR_SHORT_PASSWORD_CODE = 2;
	private static final int ERROR_LONG_PASSWORD_CODE = 3;
	private static final int ERROR_EMAIL_CODE = 4;
	private static final int ERROR_BET_CODE = 5;
	private static final int WRONG_NUMBER_FORMAT_CODE = 9;
	private static final int ERROR_USER_RIGHTS_CODE = 11;
	
    private static final int MIN_PASSWORD_LENGTH = 5;
    private static final int MAX_PASSWORD_LENGTH = 17;

    private static final String EMAIL_REG_EXP = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
                                            "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    private static final String RU = "ru";
    private static final String EN = "en";
    
    /**
     * Validates request parameters and throws ValidatorException if validation is unsuccessful.
     *
     * @param data data
     * @throws ValidatorException if the parameters are empty
     */
    public static void checkEmpty(String ...data) throws ValidatorException {
        for (String temp : data) {
            if (temp == null || temp.isEmpty()) {
                throw new ValidatorException(WRONG_DATA_CODE, "Wrong data");
            }
        }
    }
    
    public static void checkEmpty(Integer ...data) throws ValidatorException {
        for (Integer temp : data) {
            if (temp == null) {
                throw new ValidatorException(WRONG_DATA_CODE, "Wrong data");
            }
        }
    }
    
    public static void checkEmpty(Boolean data) throws ValidatorException {
            if (data == null) {
                throw new ValidatorException(WRONG_DATA_CODE, "Wrong data");
            }
        
    }
    
    public static void localValidation(String local) throws ValidatorException {
        if (!local.equals(RU)) {
            if (!local.equals(EN)) {
                throw new ValidatorException("No such local.");
            }
        }
    }
    
    /**
     * Validates password and throws ValidatorException if validation is unsuccessful.
     *
     * @param password user password
     * @throws ValidatorException if the password are incorrect
     */
    public static void passwordValidation(String password) throws ValidatorException {
        int passwordLength;
        passwordLength = password.length();
        if (passwordLength < MIN_PASSWORD_LENGTH) {
            throw new ValidatorException(ERROR_SHORT_PASSWORD_CODE,"Passwort to short");
        }

        if (passwordLength > MAX_PASSWORD_LENGTH) {
            throw new ValidatorException(ERROR_LONG_PASSWORD_CODE,"Password to long");
        }
    }
    /**
     * Validates email and throws ValidatorExceptions if validation is unsuccessful.
     *
     * @param email email
     * @throws ValidatorException if the email is incorrect
     */
    public static void emailValidation(String email) throws ValidatorException {
        Pattern pattern;
        Matcher matcher;
        pattern = Pattern.compile(EMAIL_REG_EXP);
        matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            throw new ValidatorException(ERROR_EMAIL_CODE,"E-mail not right");
        }
    }
    /**
     * Validates bets and throws ValidatorException  if validation is unsuccessful.
     *
     * @param email email
     * @throws ValidatorException if the email is incorrect
     */
    public static void betValidation(String bet,Lot lot) throws ValidatorException {
        int temp;
        try {
            temp = Integer.parseInt(bet);
        } catch (NumberFormatException exception) {
            throw new ValidatorException(WRONG_NUMBER_FORMAT_CODE, exception,"Wrong number format");
        }
        
        if(temp < lot.getCurrentPrice()){
        	throw new ValidatorException(ERROR_BET_CODE,"Wrong bet");
        }
        
    }
    
    /**
     * Validates users rights and throws ValidatorException  if validation is unsuccessful.
     *
     * @param email email
     * @throws ValidatorException if the email is incorrect
     */
    public static void userValidation(String userId) throws ValidatorException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        UserDAO userDAO = daoFactory.getUserDAO();
        boolean userBlocked = false;
		try {
			userBlocked = userDAO.checkUserBlocked(userId);
		} catch (DAOException e) {
            userBlocked = true;
		}
        
        if(userBlocked){
        	throw new ValidatorException(ERROR_USER_RIGHTS_CODE,"Error with user " + userId + " rights");
        }
        
    }



}
