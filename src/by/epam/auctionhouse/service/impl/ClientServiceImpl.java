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
import by.epam.auctionhouse.service.validator.DataValidator;
import by.epam.auctionhouse.service.validator.exception.ValidatorException;
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

	/**
	 * Gets User object from the getUserByEmail method of the UserDAO.
	 *
	 * @param email User email
	 * @param password User password
	 * @return User object
	 * @throws ServiceException if the DAOException | ValidatorException is thrown
	 * @see DAOException | ValidatorException
	 * @see UserDAO
	 */
	public User signIn(String email,String password) throws ServiceException {

		
		try {
			DataValidator.checkEmpty(email, password);
			DataValidator.emailValidation(email);
			DataValidator.passwordValidation(password);
			password = MD5.md5(password);
			return userDAO.getUserByEmail(email,password);
		} catch (DAOException e) {
			throw new ServiceException("Wrong email or password", e);
		} catch (ValidatorException e) {
			throw new ServiceException(e.getMessage());
		}

	}

	/**
	 * Invalidates current session if exists.
	 *
	 * @param session  the HttpSession
	 * @throws ServiceException if session not exists
	 */
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

	/**
	 * Check current local.
	 *
	 * @param local  
	 * @throws ServiceException if local is no valid
	 */
	@Override
	public void setLocal(String local) throws ServiceException {
		try{
			DataValidator.checkEmpty(local);
			DataValidator.localValidation(local);
		}catch(ValidatorException e){
			throw new ServiceException(e.getMessage());
		}
	}

	/**
	 * Gets User object from the signUp method of the UserDAO.
	 *
	 * @param user User object
	 * @throws ServiceException if the ConnectionPoolException or the DAOException | ValidatorException is thrown
	 * @see ConnectionPoolException
	 * @see DAOException | ValidatorException
	 * @see UserDAO
	 */
	@Override
	public void registration(String userName, String userEmail, String userPassword,String passwordRepeat, int userCardNumber,
			int userPersonalAccount) throws ServiceException {
		try{
			DataValidator.checkEmpty(userEmail, userPassword);
			DataValidator.emailValidation(userEmail);
			DataValidator.passwordValidation(userPassword);
			DataValidator.passwordValidation(passwordRepeat);
		}catch(ValidatorException e){
			throw new ServiceException(e.getMessage());
		}
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

	/**
	 * Gets Auction object collection by the specified lot name from the findAuction method of the UserDAO.
	 *
	 * @param lotName  Lot name
	 * @return the Auction
	 * @throws ServiceException if the DAOException | ValidatorException is thrown
	 * @see DAOException | ValidatorException
	 * @see UserDAO
	 */
	@Override
	public Auction findAuction(String lotName) throws ServiceException {
		Auction auction;

		try {
			DataValidator.checkEmpty(lotName);
			auction = userDAO.findAuction(lotName);
		} catch (DAOException exception) {
			logger.trace("Auction with such lot name: " + lotName + " not found.");
			throw new ServiceException("No such auction", exception);
		} catch (ValidatorException e) {
			throw new ServiceException(e.getMessage());
		}

		return auction;
	}

	/**
	 * Gets Auction object collection from the getAuctions method of the UserDAO.
	 *
	 * @return the list of Auctions objects
	 * @throws ServiceException if the DAOException | ValidatorException is thrown
	 * @see DAOException | ValidatorException
	 * @see UserDAO
	 */
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

	/**
	 * Gets Auction object from the getAuction method of the UserDAO.
	 *
	 * @param auctionId   
	 * @return the Auction object
	 * @throws ServiceException if the DAOException | ValidatorException is thrown
	 * @see DAOException | ValidatorException
	 * @see UserDAO
	 */
	@Override
	public Auction getAuction(String auctionId) throws ServiceException {
		Auction auction;

		try {
			DataValidator.checkEmpty(auctionId);
			auction = userDAO.getAuction(auctionId);
		} catch (DAOException exception) {
			logger.trace("Can not get auction with id = " + auctionId);
			throw new ServiceException("Error getting film", exception);
		} catch (ValidatorException e) {
			throw new ServiceException(e.getMessage());
		}

		return auction;
	}

	/**
	 * Passes the client id,auction id, bet value, needed lot to the placeEnglishBet method in the UserDAO.
	 *
	 * @param clientId Client id
	 * @param lot    Needed lot
	 * @param auctionId    Auction id
	 * @param bet Bet value
	 * @throws ServiceException if the DAOException | ValidatorException is thrown
	 * @see DAOException | ValidatorException
	 * @see UserDAO
	 */
	@Override
	public void placeEngishBet(String clientId,Lot lot, String auctionId, String bet) throws ServiceException {		
		try {
			DataValidator.checkEmpty(clientId, auctionId, bet);
			DataValidator.betValidation(bet, lot);
			userDAO.placeEngishBet(clientId, auctionId, bet, lot);
		} catch (DAOException e) {
			throw new ServiceException("Error while placing bet", e);
		} catch (ValidatorException e) {
			throw new ServiceException(e.getMessage());
		}


	}

	/**
	 * Gets Bet object collection by the specified user id from the getUsersBets method of the UserDAO.
	 *
	 * @param userId   
	 * @return the list of Bets objects
	 * @throws ServiceException if the DAOException | ValidatorException is thrown
	 * @see DAOException | ValidatorException
	 * @see UserDAO
	 */
	@Override
	public List<Bet> getUsersBets(String userId) throws ServiceException {

		List<Bet> usersBets = null;

		try {
			DataValidator.checkEmpty(userId);
			usersBets = userDAO.getUsersBets(userId);
		} catch (DAOException e) {
			throw new ServiceException("Error while getting users bets", e);
		} catch (ValidatorException e) {
			throw new ServiceException(e.getMessage());
		}
		return usersBets;
	}

	/**
	 * Gets Lot object collection by the specified user id from the getUsersLots method of the UserDAO.
	 *
	 * @param userId   
	 * @return the list of Lots objects
	 * @throws ServiceException if the DAOException | ValidatorException is thrown
	 * @see DAOException | ValidatorException
	 * @see UserDAO
	 */
	@Override
	public List<Lot> getUsersLots(String userId) throws ServiceException {	
		List<Lot> usersLots = null;

		try {
			DataValidator.checkEmpty(userId);
			usersLots = userDAO.getUsersLots(userId);
		} catch (DAOException e) {
			throw new ServiceException("Error while getting users bets", e);
		} catch (ValidatorException e) {
			throw new ServiceException(e.getMessage());
		}
		return usersLots;
	}

	/**
	 * Passes the client id,auction id, bet value, needed lot to the cancellationBet method in the UserDAO.
	 *
	 * @param clientId Client id
	 * @param auctionId    Auction id
	 * @param bet Bet value
	 * @param lot    Needed lot
	 * @throws ServiceException if the DAOException | ValidatorException is thrown
	 * @see DAOException | ValidatorException
	 * @see UserDAO
	 */
	@Override
	public void cancellationBet(String clientId, String auctionId, String bet, Lot lot) throws ServiceException {

		try {
			DataValidator.checkEmpty(clientId, auctionId, bet);
			userDAO.cancellationBet(clientId, auctionId, bet, lot);
		} catch (DAOException e) {
			throw new ServiceException("Error while placing bet", e);
		} catch (ValidatorException e) {
			throw new ServiceException(e.getMessage());
		}


	}


}
