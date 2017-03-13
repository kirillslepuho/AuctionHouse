package by.epam.auctionhouse.service.impl;

import by.epam.auctionhouse.bean.Auction;
import by.epam.auctionhouse.bean.User;
import by.epam.auctionhouse.dao.UserDAO;
import by.epam.auctionhouse.dao.exception.DAOException;
import by.epam.auctionhouse.dao.factory.DAOFactory;
import by.epam.auctionhouse.service.ClientService;
import by.epam.auctionhouse.service.exception.ServiceException;
import by.epam.auctionhouse.service.util.MD5;

import org.apache.logging.log4j.Logger;

import java.util.List;

import org.apache.logging.log4j.LogManager;

public class ClientServiceImpl implements ClientService{

	DAOFactory daoFactory = DAOFactory.getInstance();
	UserDAO userDAO = daoFactory.getUserDAO();
	
	private final Logger logger = LogManager.getLogger("traceLogger");
	
	
	public User signIn(String email,String password) throws ServiceException {

		if (email == null || password == null) {
			throw new ServiceException("Incorrect data");
		}

		try {
			password = MD5.md5(password);
			return userDAO.getUserByEmail(email,password);
		} catch (DAOException e) {
			throw new ServiceException("Error while getting user", e);
		}

	}

	@Override
	public void registration(String userName, String userEmail, String userPassword,String passwordRepeat, int userCardNumber,
			int userPersonalAccount) throws ServiceException {
		
		if (!passwordRepeat.equals(userPassword)) {
            throw new ServiceException("Passwords not equals");
        }
		
		try {
			userPassword = MD5.md5(userPassword);
			userDAO.addUser(userName, userEmail, userPassword, userCardNumber, userPersonalAccount);
		} catch (DAOException e) {
			logger.trace("Failed registration e-mail: " + userEmail, e);
            throw new ServiceException("User with such name exist", e);
		}
		
	}

	@Override
	public Auction findAuction(String lotName) throws ServiceException {
         Auction auction;

            try {
				auction = userDAO.findAuction(lotName);
			} catch (DAOException exception) {
				 logger.trace("Auction with such lot name: " + lotName + " not found.");
		          throw new ServiceException("No such auction", exception);
			}
            
            return auction;
	}

	@Override
	public List<Auction> getAuctions() throws ServiceException {
		List<Auction> result;

        try {
            result = userDAO.getAuctions();
        } catch (DAOException exception) {
            logger.trace("Can not take all films");
            throw new ServiceException("Error while getting products");
        }

        return result;
	}

	@Override
	public Auction getAuction(String auctionId) throws ServiceException {
		Auction auction;

        try {
            auction = userDAO.getAuction(auctionId);
        } catch (DAOException exception) {
            logger.trace("Can not get auction with id = " + auctionId);
            throw new ServiceException("Error getting film", exception);
        }

        return auction;
	}
	
	
}
