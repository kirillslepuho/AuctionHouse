package by.test;
import org.junit.Test;

import by.epam.auctionhouse.service.validator.DataValidator;
import by.epam.auctionhouse.service.validator.exception.ValidatorException;

import static org.junit.Assert.assertEquals;


public class ValidatorTestTwo{

	private static final String WRONG_EMAIL = "dsaj@gmail";
	private static final String INVALID_USERNAME_MSG = "E-mail not right";
	
    @Test
    public void validateUser() {
        try {
			DataValidator.emailValidation(WRONG_EMAIL);
		} catch (ValidatorException e) {
			assertEquals(INVALID_USERNAME_MSG, e.getMessage());
		}
    }
    }
