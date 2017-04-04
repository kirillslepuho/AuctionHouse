package by.epam.auctionhouse.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import by.epam.auctionhouse.bean.Auction;
import by.epam.auctionhouse.bean.Bet;
import by.epam.auctionhouse.bean.Lot;
import by.epam.auctionhouse.bean.User;
import by.epam.auctionhouse.service.exception.ServiceException;

public interface ClientService{
	
	User signIn(String email, String password) throws ServiceException;
	
	void registration(String userName, String userEmail, String userPassword, String passwordRepeat, int userCardNumber,
			int userPersonalAccount) throws ServiceException;
	
	Auction findAuction(String lotName) throws ServiceException;

	List<Auction> getAuctions() throws ServiceException;

	Auction getAuction(String auctionId) throws ServiceException;

	void signOut(HttpSession session) throws ServiceException;
	
	void placeEngishBet(String clientId, Lot lot, String auctionId, String bet) throws ServiceException;
	
	List<Bet> getUsersBets(String userId) throws ServiceException;
	
	List<Lot> getUsersLots(String userId) throws ServiceException;
	
	void cancellationBet(String clientId, String auctionId, String bet, String lotId) throws ServiceException;
}
