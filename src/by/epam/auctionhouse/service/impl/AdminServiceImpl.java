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
import by.epam.auctionhouse.service.util.DataValidator;
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
	 * @throws ServiceException if the DAOException is thrown
	 * @see DAOException
	 * @see AdminDAO
	 */
	@Override
	public void addAuction(Auction auction) throws ServiceException {
		validateInputData(auction);

		try {
			adminDAO.addAuction(auction);
		} catch(DAOException exception) {
			throw new ServiceException("Can not add auction", exception);
		}

	}

	/**
	 * Passes specified auction id to the deleteAuction method of the AdminDAO.
	 *
	 * @param deleteId auction id
	 * @throws ServiceException if the DAOException is thrown
	 * @see DAOException
	 * @see AdminDAO
	 */
	@Override
	public void deleteAuction(String deleteId) throws ServiceException {
		DataValidator.checkEmpty(deleteId);

		try {
			adminDAO.deleteAuction(deleteId);
		} catch(DAOException exception) {
			throw new ServiceException("Can not delete auction", exception);
		}

	}

	/**
	 * Passes Auction object and id to the editAuction method of the AdminDAO.
	 *
	 * @param aduction Auction object
	 * @throws ServiceException if the DAOException is thrown
	 * @see DAOException
	 * @see AdminDAO
	 */
	@Override
	public void editAuction(Auction auction, String changeId) throws ServiceException {
		validateInputData(auction);
		DataValidator.checkEmpty(changeId);
		try {
			adminDAO.editAuction(auction, changeId);
		} catch(DAOException exception) {
			throw new ServiceException("Can not edit auction", exception);
		}

	}

	/**
	 * Passes Lot object to the addLot method of the AdminDAO.
	 *
	 * @param lot Lot object
	 * @throws ServiceException if the DAOException is thrown
	 * @see DAOException
	 * @see AdminDAO
	 */
	@Override
	public void addLot(Lot lot) throws ServiceException {
		validateInputData(lot);
		try {
			adminDAO.addLot(lot);
		} catch(DAOException exception) {
			throw new ServiceException("Can not add lot", exception);
		}

	}

	/**
	 * Passes Lot object and id to the editAuction method of the AdminDAO.
	 *
	 * @param aduction Auction object
	 * @throws ServiceException if the DAOException is thrown
	 * @see DAOException
	 * @see AdminDAO
	 */
	@Override
	public void editLot(Lot lot, String changeId) throws ServiceException {
		validateInputData(lot);
		DataValidator.checkEmpty(changeId);
		try {
			adminDAO.editLot(lot, changeId);
		} catch(DAOException exception) {
			throw new ServiceException("Can not edit lot", exception);
		}

	}

	/**
	 * Passes specified lot id to the blockLot method of the AdminDAO.
	 *
	 * @param deleteId lot id
	 * @throws ServiceException if the DAOException is thrown
	 * @see DAOException
	 * @see AdminDAO
	 */
	@Override
	public void blockLot(String deleteId) throws ServiceException {
		DataValidator.checkEmpty(deleteId);
		try {
			adminDAO.blockLot(deleteId);
		} catch(DAOException exception) {
			throw new ServiceException("Can not add lot", exception);
		}

	}

	/**
	 * Gets Lot object from the getLotById method of the AdminDAO.
	 *
	 * @param id   
	 * @return the Lot object
	 * @throws ServiceException if the DAOException is thrown
	 * @see DAOException
	 * @see AdminDAO
	 */
	@Override
	public Lot getLotById(String lotId) throws ServiceException {
		DataValidator.checkEmpty(lotId);

		Lot lot = null;
		try {
			lot = adminDAO.getLotById(lotId);
		} catch(DAOException exception) {
			throw new ServiceException("Can not get lot", exception);
		}

		return lot;
	}

	/**
	 * Gets Lot object collection from the getLots method of the AdminDAO.
	 *
	 * @return the list of Lot objects
	 * @throws ServiceException if the DAOException is thrown
	 * @see DAOException
	 * @see AdminDAO
	 */
	@Override
	public List<Lot> getLots() throws ServiceException {
		List<Lot> result;

		try {
			result = adminDAO.getLots();
		} catch (DAOException exception) {
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
	 * @throws ServiceException if the DAOException is thrown
	 * @see DAOException
	 * @see AdminDAO
	 */
	@Override
	public void setAuctionWinner(String auctionID, String clientID, String bet) throws ServiceException {
		DataValidator.checkEmpty(auctionID);
		DataValidator.checkEmpty(clientID);
		DataValidator.checkEmpty(bet);
		try {
			adminDAO.setBetsWinFalse(auctionID);
			adminDAO.setAuctionWinner(auctionID, clientID, bet);
		} catch(DAOException exception) {
			throw new ServiceException("Can not set winner", exception);
		}

	}

	/**
	 * Gets Auction winner the specified id from the getAuctionWinner method of the AdminDAO.
	 *
	 * @param auctionID      Auction Id
	 * @return auction winner user
	 * @throws ServiceException if the DAOException is thrown
	 * @see DAOException
	 * @see AdminDAO
	 */
	@Override
	public User getAuctionWinner(String auctionID) throws ServiceException {
		DataValidator.checkEmpty(auctionID);
		User user = null;
		try {
			user = adminDAO.getAuctionWinner(auctionID);
		} catch(DAOException exception) {
			throw new ServiceException("Can not get winner", exception);
		}
		return user;
	}
	
	/**
	 * Gets User object collection from the getUsers method of the AdminDAO.
	 *
	 * @return the list of User objects
	 * @throws ServiceException if the DAOException is thrown
	 * @see DAOException
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
	 * @throws ServiceException if the DAOException is thrown
	 * @see DAOException
	 * @see AdminDAO
	 */
	@Override
	public List<Bet> getAuctionsBets(String auctionId) throws ServiceException {
		DataValidator.checkEmpty(auctionId);
		List<Bet> bets = null;

		try {
			bets = adminDAO.getAuctionsBets(auctionId);
		} catch (DAOException e) {
			throw new ServiceException("Error while getting users", e);
		}
		return bets;
	}
	
	/**
     * Passes the status and user id to the setUserBlockStatus method in the AdminDAO.
     *
     * @param status user status
     * @param userId user id
     * @throws ServiceException if the DAOException is thrown
     * @see DAOException
     * @see AdminDAO
     */
	@Override
	public void setUserBlockStatus(String userId,boolean status) throws ServiceException {
		DataValidator.checkEmpty(userId);
		DataValidator.checkEmpty(status);
		try {
			adminDAO.setUserBlockStatus(userId, status);
		} catch(DAOException exception) {
			throw new ServiceException("Can not block user", exception);
		}

	}

	/**
     * Auction check empty validation
     *
     */
	private void validateInputData(Auction auction) throws ServiceException {
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
     *
     */
	private void validateInputData(Lot lot) throws ServiceException {

		DataValidator.checkEmpty(lot.getCurrentPrice());
		DataValidator.checkEmpty(lot.getDescriprion());
		DataValidator.checkEmpty(lot.getName());
		DataValidator.checkEmpty(lot.getImage());
		DataValidator.checkEmpty(lot.getType());
	}

}
