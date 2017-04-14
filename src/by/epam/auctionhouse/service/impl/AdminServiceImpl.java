package by.epam.auctionhouse.service.impl;

import by.epam.auctionhouse.bean.Auction;
import by.epam.auctionhouse.bean.Bet;
import by.epam.auctionhouse.bean.Lot;
import by.epam.auctionhouse.bean.User;
import by.epam.auctionhouse.dao.AdminDAO;
import by.epam.auctionhouse.dao.exception.DAOException;
import by.epam.auctionhouse.dao.factory.DAOFactory;
import by.epam.auctionhouse.service.AdminService;
import by.epam.auctionhouse.service.exception.ServiceException;
import by.epam.auctionhouse.service.validator.DataValidator;
import by.epam.auctionhouse.service.validator.exception.ValidatorException;

import java.util.List;


/**
 * Provides methods to access AdminDAO.
 *
 * @author Kirill Slepuho
 */
public class AdminServiceImpl implements AdminService{


	private DAOFactory daoFactory = DAOFactory.getInstance();
	private AdminDAO adminDAO = daoFactory.getAdminDAO();


	/**
	 * Passes Auction object to the addAuction method of the AdminDAO.
	 *
	 * @param auction Auction object
	 * @throws ServiceException if the DAOException | ValidatorException is thrown
	 * @see DAOException | ValidatorException
	 * @see AdminDAO
	 */
	@Override
	public void addAuction(Auction auction) throws ServiceException {
		
		try {
			validateInputData(auction);
			adminDAO.addAuction(auction);
		} catch(DAOException exception) {
			throw new ServiceException("Can not add auction", exception);
		} catch (ValidatorException e) {
			throw new ServiceException(e.getMessage());
		}

	}

	/**
	 * Passes specified auction id to the deleteAuction method of the AdminDAO.
	 *
	 * @param deleteId auction id
	 * @throws ServiceException if the DAOException | ValidatorException is thrown
	 * @see DAOException | ValidatorException
	 * @see AdminDAO
	 */
	@Override
	public void deleteAuction(String deleteId) throws ServiceException {
		

		try {
			DataValidator.checkEmpty(deleteId);
			adminDAO.deleteAuction(deleteId);
		} catch(DAOException exception) {
			throw new ServiceException("Can not delete auction", exception);
		} catch (ValidatorException e) {
			throw new ServiceException(e.getMessage());
		}

	}

	/**
	 * Passes Auction object and id to the editAuction method of the AdminDAO.
	 *
	 * @param aduction Auction object
	 * @throws ServiceException if the DAOException | ValidatorException is thrown
	 * @see DAOException | ValidatorException
	 * @see AdminDAO
	 */
	@Override
	public void editAuction(Auction auction, String changeId) throws ServiceException {
		
		try {
			validateInputData(auction);
			DataValidator.checkEmpty(changeId);
			
			adminDAO.editAuction(auction, changeId);
		} catch(DAOException exception) {
			throw new ServiceException("Can not edit auction", exception);
		} catch (ValidatorException e) {
			throw new ServiceException(e.getMessage());
		}

	}

	/**
	 * Passes Lot object to the addLot method of the AdminDAO.
	 *
	 * @param lot Lot object
	 * @throws ServiceException if the DAOException | ValidatorException is thrown
	 * @see DAOException | ValidatorException
	 * @see AdminDAO
	 */
	@Override
	public void addLot(Lot lot) throws ServiceException {
		
		try {
			validateInputData(lot);
			
			adminDAO.addLot(lot);
		} catch(DAOException exception) {
			throw new ServiceException("Can not add lot", exception);
		} catch (ValidatorException e) {
			throw new ServiceException(e.getMessage());
		}

	}

	/**
	 * Passes Lot object and id to the editAuction method of the AdminDAO.
	 *
	 * @param aduction Auction object
	 * @throws ServiceException if the DAOException | ValidatorException is thrown
	 * @see DAOException | ValidatorException
	 * @see AdminDAO
	 */
	@Override
	public void editLot(Lot lot, String changeId) throws ServiceException {
		
		try {
			validateInputData(lot);
			DataValidator.checkEmpty(changeId);
			
			adminDAO.editLot(lot, changeId);
		} catch(DAOException exception) {
			throw new ServiceException("Can not edit lot", exception);
		} catch (ValidatorException e) {
			throw new ServiceException(e.getMessage());
		}

	}

	/**
	 * Passes specified lot id to the blockLot method of the AdminDAO.
	 *
	 * @param deleteId lot id
	 * @throws ServiceException if the DAOException | ValidatorException is thrown
	 * @see DAOException | ValidatorException
	 * @see AdminDAO
	 */
	@Override
	public void blockLot(String deleteId) throws ServiceException {
		
		try {
			DataValidator.checkEmpty(deleteId);
			
			adminDAO.blockLot(deleteId);
		} catch(DAOException exception) {
			throw new ServiceException("Can not add lot", exception);
		} catch (ValidatorException e) {
			throw new ServiceException(e.getMessage());
		}

	}

	/**
	 * Gets Lot object from the getLotById method of the AdminDAO.
	 *
	 * @param id   
	 * @return the Lot object
	 * @throws ServiceException if the DAOException | ValidatorException is thrown
	 * @see DAOException | ValidatorException
	 * @see AdminDAO
	 */
	@Override
	public Lot getLotById(String lotId) throws ServiceException {
		

		Lot lot = null;
		try {
			DataValidator.checkEmpty(lotId);
			
			lot = adminDAO.getLotById(lotId);
		} catch(DAOException  exception) {
			throw new ServiceException("Can not get lot", exception);
		} catch (ValidatorException e) {
			throw new ServiceException(e.getMessage());
		}

		return lot;
	}

	/**
	 * Gets Lot object collection from the getLots method of the AdminDAO.
	 *
	 * @return the list of Lot objects
	 * @throws ServiceException if the DAOException | ValidatorException is thrown
	 * @see DAOException | ValidatorException
	 * @see AdminDAO
	 */
	@Override
	public List<Lot> getLots() throws ServiceException {
		List<Lot> result;

		try {
			result = adminDAO.getLots();
		} catch (DAOException  exception) {
			throw new ServiceException("Error while getting products");
		}

		return result;
	}

	/**
	 * Passes auction id, client id, bet to the setAuctionWinner method of the AdminDAO.
	 *
	 * @param auctionID      Auction Id
	 * @param clientID Client id
	 * @param bet Bet value
	 * @throws ServiceException if the DAOException | ValidatorException is thrown
	 * @see DAOException | ValidatorException
	 * @see AdminDAO
	 */
	@Override
	public void setAuctionWinner(String auctionID, String clientID, String bet) throws ServiceException {
		
		try {
			DataValidator.checkEmpty(auctionID);
			DataValidator.checkEmpty(clientID);
			DataValidator.checkEmpty(bet);
			
			adminDAO.setBetsWinFalse(auctionID);
			adminDAO.setAuctionWinner(auctionID, clientID, bet);
		} catch(DAOException exception) {
			throw new ServiceException("Can not set winner", exception);
		} catch (ValidatorException e) {
			throw new ServiceException(e.getMessage());
		}

	}

	/**
	 * Gets Auction winner the specified id from the getAuctionWinner method of the AdminDAO.
	 *
	 * @param auctionID      Auction Id
	 * @return auction winner user
	 * @throws ServiceException if the DAOException | ValidatorException is thrown
	 * @see DAOException | ValidatorException
	 * @see AdminDAO
	 */
	@Override
	public User getAuctionWinner(String auctionID) throws ServiceException {
		
		User user = null;
		try {
			DataValidator.checkEmpty(auctionID);
			
			user = adminDAO.getAuctionWinner(auctionID);
		} catch(DAOException exception) {
			throw new ServiceException("Can not get winner", exception);
		} catch (ValidatorException e) {
			throw new ServiceException(e.getMessage());
		}
		return user;
	}
	
	/**
	 * Gets User object collection from the getUsers method of the AdminDAO.
	 *
	 * @return the list of User objects
	 * @throws ServiceException if the DAOException | ValidatorException is thrown
	 * @see DAOException | ValidatorException
	 * @see AdminDAO
	 */
	@Override
	public List<User> getUsers() throws ServiceException {
		List<User> users = null;

		try {
			users = adminDAO.getUsers();
		} catch (DAOException e) {
			throw new ServiceException("Error while getting users", e);
		}
		return users;
	}
	
	/**
	 * Gets Bet object collection from the getAuctionsBets method of the AdminDAO.
	 * 
	 * @param id  
	 * @return the list of Bet objects
	 * @throws ServiceException if the DAOException | ValidatorException is thrown
	 * @see DAOException | ValidatorException
	 * @see AdminDAO
	 */
	@Override
	public List<Bet> getAuctionsBets(String auctionId) throws ServiceException {
		
		List<Bet> bets = null;

		try {
			DataValidator.checkEmpty(auctionId);
			
			bets = adminDAO.getAuctionsBets(auctionId);
		} catch (DAOException e) {
			throw new ServiceException("Error while getting users", e);
		} catch (ValidatorException e) {
			throw new ServiceException(e.getMessage());
		}
		return bets;
	}
	
	/**
     * Passes the status and user id to the setUserBlockStatus method in the AdminDAO.
     *
     * @param status user status
     * @param userId user id
     * @throws ServiceException if the DAOException | ValidatorException is thrown
     * @see DAOException | ValidatorException
     * @see AdminDAO
     */
	@Override
	public void setUserBlockStatus(String userId,boolean status) throws ServiceException {
		
		try {
			DataValidator.checkEmpty(userId);
			DataValidator.checkEmpty(status);
			
			adminDAO.setUserBlockStatus(userId, status);
		} catch(DAOException exception) {
			throw new ServiceException("Can not block user", exception);
		} catch (ValidatorException e) {
			throw new ServiceException(e.getMessage());
		}

	}

	/**
     * Auction check empty validation
	 * @throws ValidatorException 
     *
     */
	private void validateInputData(Auction auction) throws ValidatorException {
		DataValidator.checkEmpty(auction.getBeginDate());
		DataValidator.checkEmpty(auction.getExpirationDate());
		DataValidator.checkEmpty(auction.getLot().getId());
		DataValidator.checkEmpty(auction.getPlace());
		DataValidator.checkEmpty(auction.getTime());
		DataValidator.checkEmpty(auction.getIsActive());
		DataValidator.checkEmpty(auction.getType());
	}

	/**
     * Lot check empty validation
	 * @throws ValidatorException 
     *
     */
	private void validateInputData(Lot lot) throws  ValidatorException {

		DataValidator.checkEmpty(lot.getCurrentPrice());
		DataValidator.checkEmpty(lot.getDescriprion());
		DataValidator.checkEmpty(lot.getName());
		DataValidator.checkEmpty(lot.getImage());
		DataValidator.checkEmpty(lot.getType());
	}

}
