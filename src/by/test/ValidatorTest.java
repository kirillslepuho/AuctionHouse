package by.test;

import org.junit.Test;

import by.epam.auctionhouse.service.exception.ServiceException;

import static by.epam.auctionhouse.service.util.DataValidator.*;
import static org.junit.Assert.assertEquals;

public class ValidatorTest {
	
	
	@Test(expected = ServiceException.class)
    public void factorialNegative() throws ServiceException {
        passwordValidation("1");
    }

}