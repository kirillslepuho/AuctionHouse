package by.epam.auctionhouse.service.impl;

import by.epam.auctionhouse.bean.Auction;
import by.epam.auctionhouse.bean.Lot;
import by.epam.auctionhouse.dao.AdminDAO;
import by.epam.auctionhouse.dao.exception.DAOException;
import by.epam.auctionhouse.dao.factory.DAOFactory;
import by.epam.auctionhouse.service.AdminService;
import by.epam.auctionhouse.service.exception.ServiceException;

import org.apache.logging.log4j.Logger;

import java.util.List;

import org.apache.logging.log4j.LogManager;


public class AdminServiceImpl implements AdminService{
	
	  private static final Logger logger = LogManager.getLogger(AdminService.class.getName());
	
	 DAOFactory daoFactory = DAOFactory.getInstance();
	 AdminDAO adminDAO = daoFactory.getAdminDAO();
	@Override
	public void addAuction(Auction auction) throws ServiceException {
		try {
            adminDAO.addAuction(auction);
        } catch(DAOException exception) {
        	logger.trace("Error adding auction");
            throw new ServiceException("Can not add auction", exception);
        }
		
	}
	@Override
	public void deleteAuction(String deleteId) throws ServiceException {
		try {
            adminDAO.deleteAuction(deleteId);
        } catch(DAOException exception) {
        	logger.trace("Error while deleting auction");
            throw new ServiceException("Can not delete auction", exception);
        }
		
	}
	@Override
	public void editAuction(Auction auction, String changeId) throws ServiceException {
		try {
            adminDAO.editAuction(auction, changeId);
        } catch(DAOException exception) {
        	logger.trace("Error editing auction");
            throw new ServiceException("Can not edit auction", exception);
        }
		
	}
	@Override
	public void addLot(Lot lot) throws ServiceException {
		try {
            adminDAO.addLot(lot);
        } catch(DAOException exception) {
        	logger.trace("Error while adding auction");
            throw new ServiceException("Can not add lot", exception);
        }
		
	}
	@Override
	public void editLot(Lot lot, String changeId) throws ServiceException {
		try {
            adminDAO.editLot(lot, changeId);
        } catch(DAOException exception) {
        	logger.trace("Error while editing lot");
            throw new ServiceException("Can not edit lot", exception);
        }
		
	}
	@Override
	public Lot getLotById(String lotId) throws ServiceException {
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
            logger.trace("Can not take all films");
            throw new ServiceException("Error while getting products");
        }

        return result;
	}
	 
	
	
}
