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




public class AdminServiceImpl implements AdminService{


	private DAOFactory daoFactory = DAOFactory.getInstance();
	private AdminDAO adminDAO = daoFactory.getAdminDAO();


	@Override
	public void addAuction(Auction auction) throws ServiceException {
		validateInputData(auction);

		try {
			adminDAO.addAuction(auction);
		} catch(DAOException exception) {
			throw new ServiceException("Can not add auction", exception);
		}

	}
	@Override
	public void deleteAuction(String deleteId) throws ServiceException {
		DataValidator.checkEmpty(deleteId);

		try {
			adminDAO.deleteAuction(deleteId);
		} catch(DAOException exception) {
			throw new ServiceException("Can not delete auction", exception);
		}

	}
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
	@Override
	public void addLot(Lot lot) throws ServiceException {
		validateInputData(lot);
		try {
			adminDAO.addLot(lot);
		} catch(DAOException exception) {
			throw new ServiceException("Can not add lot", exception);
		}

	}
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
	@Override
	public void blockLot(String deleteId) throws ServiceException {
		DataValidator.checkEmpty(deleteId);
		try {
			adminDAO.blockLot(deleteId);
		} catch(DAOException exception) {
			throw new ServiceException("Can not add lot", exception);
		}
		
	}
	
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


	private void validateInputData(Auction auction) throws ServiceException {
		DataValidator.checkEmpty(auction.getBeginDate());
		DataValidator.checkEmpty(auction.getExpirationDate());
		DataValidator.checkEmpty(auction.getLot().getId());
		DataValidator.checkEmpty(auction.getPlace());
		DataValidator.checkEmpty(auction.getTime());
		DataValidator.checkEmpty(auction.getIsActive());
		DataValidator.checkEmpty(auction.getType());
		DataValidator.checkEmpty(auction.getRounds());
	}

	private void validateInputData(Lot lot) throws ServiceException {

		DataValidator.checkEmpty(lot.getCurrentPrice());
		DataValidator.checkEmpty(lot.getDescriprion());
		DataValidator.checkEmpty(lot.getName());
		DataValidator.checkEmpty(lot.getImage());
		DataValidator.checkEmpty(lot.getType());
	}
	
}
