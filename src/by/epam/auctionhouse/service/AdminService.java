package by.epam.auctionhouse.service;

import java.util.List;

import by.epam.auctionhouse.bean.Auction;
import by.epam.auctionhouse.bean.Lot;
import by.epam.auctionhouse.service.exception.ServiceException;

public interface AdminService {
	void addAuction(Auction auction) throws ServiceException;
	void deleteAuction(String deleteId) throws ServiceException;
	void editAuction(Auction auction, String changeId) throws ServiceException;
	void addLot(Lot lot) throws ServiceException;
	void editLot(Lot lot, String changeId) throws ServiceException;
	Lot getLotById(String lotId) throws ServiceException;
	List<Lot> getLots() throws ServiceException;
}
