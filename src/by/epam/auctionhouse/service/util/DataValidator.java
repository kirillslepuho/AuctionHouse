package by.epam.auctionhouse.service.util;



import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import by.epam.auctionhouse.service.exception.ServiceException;

public class DataValidator {

    private static final int MIN_PASSWORD_LENGTH = 5;
    private static final int MAX_PASSWORD_LENGTH = 17;

    private static final String EMAIL_REG_EXP = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
                                            "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";


    public static void checkEmpty(String ...data) throws ServiceException {
        for (String temp : data) {
            if (temp == null || temp.isEmpty()) {
                throw new ServiceException("Wrong data");
            }
        }
    }
    
    public static void checkEmpty(Integer ...data) throws ServiceException {
        for (Integer temp : data) {
            if (temp == null) {
                throw new ServiceException("Wrong data");
            }
        }
    }
    
    public static void checkEmpty(Boolean data) throws ServiceException {
            if (data == null) {
                throw new ServiceException("Wrong data");
            }
        
    }
    

    public static void passwordValidation(String password) throws ServiceException {
        int passwordLength;
        passwordLength = password.length();
        if (passwordLength < MIN_PASSWORD_LENGTH) {
            throw new ServiceException("Password is very short(min 5)");
        }

        if (passwordLength > MAX_PASSWORD_LENGTH) {
            throw new ServiceException("Password very big(max 17)");
        }
    }

    public static void emailValidation(String email) throws ServiceException {
        Pattern pattern;
        Matcher matcher;
        pattern = Pattern.compile(EMAIL_REG_EXP);
        matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            throw new ServiceException("E-mail not right");
        }
    }

    public static void cardNumberValidation(String data) throws ServiceException {
        int temp;
        try {
            temp = Integer.parseInt(data);
        } catch (NumberFormatException exception) {
            throw new ServiceException("Wrong number format", exception);
        }

    }



}
