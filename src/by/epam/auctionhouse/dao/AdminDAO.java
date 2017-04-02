package by.epam.auctionhouse.dao;

import java.util.List;

import by.epam.auctionhouse.bean.Auction;
import by.epam.auctionhouse.bean.Bet;
import by.epam.auctionhouse.bean.Lot;
import by.epam.auctionhouse.bean.User;
import by.epam.auctionhouse.dao.exception.DAOException;

public interface AdminDAO {
	void addAuction(Auction auction) throws DAOException;
	
	void deleteAuction(String deleteId) throws DAOException;
	
	void editAuction(Auction auction, String changeId) throws DAOException;
	
	void addLot(Lot lot) throws DAOException;
	
	void editLot(Lot lot, String changeId) throws DAOException;
	
	Lot getLotById(String lotId) throws DAOException;
	
	List<Lot> getLots() throws DAOException;
	
	void setAuctionWinner(String auctionID, String clientID, String bet) throws DAOException;
	
	User getAuctionWinner(String auctionID) throws DAOException;
	
	List<User> getUsers() throws DAOException;
	
	List<Bet> getAuctionsBets(String auctionId) throws DAOException;
	
	void setUserBlockStatus(String userId,boolean status) throws DAOException;
		
}
