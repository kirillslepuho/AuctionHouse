package by.epam.auctionhouse.service;

import java.util.List;

import by.epam.auctionhouse.bean.Auction;
import by.epam.auctionhouse.bean.Bet;
import by.epam.auctionhouse.bean.Lot;
import by.epam.auctionhouse.bean.User;
import by.epam.auctionhouse.dao.exception.DAOException;
import by.epam.auctionhouse.service.exception.ServiceException;

public interface AdminService {
	void addAuction(Auction auction) throws ServiceException;
	void deleteAuction(String deleteId) throws ServiceException;
	void editAuction(Auction auction, String changeId) throws ServiceException;
	void addLot(Lot lot) throws ServiceException;
	void editLot(Lot lot, String changeId) throws ServiceException;
	Lot getLotById(String lotId) throws ServiceException;
	List<Lot> getLots() throws ServiceException;
	void setAuctionWinner(String auctionID, String clientID, String bet) throws ServiceException;

	User getAuctionWinner(String auctionID) throws ServiceException;

	List<User> getUsers() throws ServiceException;

	List<Bet> getAuctionsBets(String auctionId) throws ServiceException;

	void setUserBlockStatus(String userId,boolean status) throws ServiceException;
}
