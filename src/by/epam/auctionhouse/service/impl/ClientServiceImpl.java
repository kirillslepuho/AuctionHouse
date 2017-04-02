package by.epam.auctionhouse.service.impl;

import by.epam.auctionhouse.bean.Auction;
import by.epam.auctionhouse.bean.Bet;
import by.epam.auctionhouse.bean.Lot;
import by.epam.auctionhouse.bean.User;
import by.epam.auctionhouse.dao.UserDAO;
import by.epam.auctionhouse.dao.exception.DAOException;
import by.epam.auctionhouse.dao.factory.DAOFactory;
import by.epam.auctionhouse.service.ClientService;
import by.epam.auctionhouse.service.exception.ServiceException;
import by.epam.auctionhouse.service.util.DataValidator;
import by.epam.auctionhouse.service.util.MD5;


import org.apache.logging.log4j.Logger;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;

public class ClientServiceImpl implements ClientService{
	
	private static final String SESSION_USER_ATTRIBUTE = "user";
    private static final String SESSION_ADMIN_ATTRIBUTE = "admin";

	private DAOFactory daoFactory = DAOFactory.getInstance();
	private UserDAO userDAO = daoFactory.getUserDAO();
	
	private final Logger logger = LogManager.getLogger("traceLogger");
	
	
	public User signIn(String email,String password) throws ServiceException {
		
		DataValidator.checkEmpty(email, password);
        DataValidator.emailValidation(email);
        DataValidator.passwordValidation(password);

		try {
			password = MD5.md5(password);
			return userDAO.getUserByEmail(email,password);
		} catch (DAOException e) {
			throw new ServiceException("Error while getting user", e);
		}

	}
	
	@Override
    public void signOut(HttpSession session) throws ServiceException {
        if (session == null) {
            throw new ServiceException("Session not found");
        }

        session.removeAttribute(SESSION_USER_ATTRIBUTE);
        session.removeAttribute(SESSION_ADMIN_ATTRIBUTE);

        if (!session.getAttributeNames().hasMoreElements()) {
            session.invalidate();
        }
    }

	@Override
	public void registration(String userName, String userEmail, String userPassword,String passwordRepeat, int userCardNumber,
			int userPersonalAccount) throws ServiceException {
		
		DataValidator.checkEmpty(userEmail, userPassword);
        DataValidator.emailValidation(userEmail);
        DataValidator.passwordValidation(userPassword);
        DataValidator.passwordValidation(passwordRepeat);
        
		
		if (!passwordRepeat.equals(userPassword)) {
            throw new ServiceException("Passwords not equals");
        }
		
		try {
			userPassword = MD5.md5(userPassword);
			userDAO.addUser(userName, userEmail, userPassword, userCardNumber, userPersonalAccount);
		} catch (DAOException e) {
            throw new ServiceException("User with such name exist", e);
		}
		
	}

	@Override
	public Auction findAuction(String lotName) throws ServiceException {
		
		DataValidator.checkEmpty(lotName);
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
            throw new ServiceException("Error while getting products");
        }

        return result;
	}

	@Override
	public Auction getAuction(String auctionId) throws ServiceException {
		DataValidator.checkEmpty(auctionId);
		
		Auction auction;

        try {
            auction = userDAO.getAuction(auctionId);
        } catch (DAOException exception) {
            logger.trace("Can not get auction with id = " + auctionId);
            throw new ServiceException("Error getting film", exception);
        }

        return auction;
	}

	@Override
	public void placeEngishBet(String clientId,Lot lot, String auctionId, String bet) throws ServiceException {
		
		DataValidator.checkEmpty(clientId, auctionId, bet);
		
		DataValidator.betValidation(bet, lot);

		
		try {
			userDAO.placeEngishBet(clientId, auctionId, bet, lot);
		} catch (DAOException e) {
            throw new ServiceException("Error while placing bet", e);
		}
		
		
	}

	@Override
	public List<Bet> getUsersBets(String userId) throws ServiceException {
		DataValidator.checkEmpty(userId);
		List<Bet> usersBets = null;
		
				try {
					usersBets = userDAO.getUsersBets(userId);
				} catch (DAOException e) {
					throw new ServiceException("Error while getting users bets", e);
				}
				return usersBets;
	}

	@Override
	public List<Lot> getUsersLots(String userId) throws ServiceException {
		DataValidator.checkEmpty(userId);
		List<Lot> usersLots = null;
		
				try {
					usersLots = userDAO.getUsersLots(userId);
				} catch (DAOException e) {
					throw new ServiceException("Error while getting users bets", e);
				}
				return usersLots;
	}
	
	
}
