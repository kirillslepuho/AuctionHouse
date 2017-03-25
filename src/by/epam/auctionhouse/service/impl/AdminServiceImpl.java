package by.epam.auctionhouse.service.impl;

import by.epam.auctionhouse.bean.Auction;
import by.epam.auctionhouse.bean.Lot;
import by.epam.auctionhouse.dao.AdminDAO;
import by.epam.auctionhouse.dao.exception.DAOException;
import by.epam.auctionhouse.dao.factory.DAOFactory;
import by.epam.auctionhouse.service.AdminService;
import by.epam.auctionhouse.service.exception.ServiceException;
import by.epam.auctionhouse.service.util.DataValidator;

import org.apache.logging.log4j.Logger;

import com.mysql.fabric.xmlrpc.base.Data;

import java.util.List;

import org.apache.logging.log4j.LogManager;


public class AdminServiceImpl implements AdminService{

	private static final Logger logger = LogManager.getLogger(AdminService.class.getName());

	DAOFactory daoFactory = DAOFactory.getInstance();
	AdminDAO adminDAO = daoFactory.getAdminDAO();


	@Override
	public void addAuction(Auction auction) throws ServiceException {
		validateInputData(auction);
		
		try {
			adminDAO.addAuction(auction);
		} catch(DAOException exception) {
			logger.trace("Error adding auction");
			throw new ServiceException("Can not add auction", exception);
		}

	}
	@Override
	public void deleteAuction(String deleteId) throws ServiceException {
		DataValidator.checkEmpty(deleteId);
		
		try {
			adminDAO.deleteAuction(deleteId);
		} catch(DAOException exception) {
			logger.trace("Error while deleting auction");
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
			logger.trace("Error editing auction");
			throw new ServiceException("Can not edit auction", exception);
		}

	}
	@Override
	public void addLot(Lot lot) throws ServiceException {
		validateInputData(lot);
		try {
			adminDAO.addLot(lot);
		} catch(DAOException exception) {
			logger.trace("Error while adding auction");
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
			logger.trace("Error while editing lot");
			throw new ServiceException("Can not edit lot", exception);
		}

	}
	@Override
	public Lot getLotById(String lotId) throws ServiceException {
		DataValidator.checkEmpty(lotId);
		
		Lot lot = null;
		try {
			lot = adminDAO.getLotById(lotId);
		} catch(DAOException exception) {
			logger.trace("Error while getting lot");
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
		DataValidator.checkEmpty(lot.isClients());
		DataValidator.checkEmpty(lot.getCurrentPrice());
		DataValidator.checkEmpty(lot.getDescriprion());
		DataValidator.checkEmpty(lot.getName());
		DataValidator.checkEmpty(lot.getImage());
		DataValidator.checkEmpty(lot.getType());
	}

}
