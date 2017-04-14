package by.test;

import org.junit.Test;

import by.epam.auctionhouse.service.validator.exception.ValidatorException;

import static by.epam.auctionhouse.service.validator.DataValidator.*;

public class ValidatorTest {
	
	
	@Test(expected = ValidatorException.class)
    public void factorialNegative() throws ValidatorException {
        passwordValidation("1");
    }

}