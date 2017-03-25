package by.epam.auctionhouse.dao;

import java.util.List;

import by.epam.auctionhouse.bean.Auction;
import by.epam.auctionhouse.bean.Lot;
import by.epam.auctionhouse.bean.User;
import by.epam.auctionhouse.dao.exception.DAOException;

public interface UserDAO {

	User getUserByEmail(String email,String password) throws DAOException;

	void addUser(String userName, String userEmail, String userPassword, int userCardNumber, int userPersonalAccount)
			throws DAOException;

	Auction findAuction(String lotName) throws DAOException;

	List<Auction> getAuctions() throws DAOException;

	Auction getAuction(String auctionId) throws DAOException;
	
	void placeEngishBet(String clientId, String auctionId, String bet) throws DAOException;

	void changeLotCurrentPrice(String bet, Lot lot) throws DAOException;

}
