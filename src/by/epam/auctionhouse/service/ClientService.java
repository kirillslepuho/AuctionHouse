package by.epam.auctionhouse.service;

import java.util.List;

import by.epam.auctionhouse.bean.Auction;
import by.epam.auctionhouse.bean.User;
import by.epam.auctionhouse.service.exception.ServiceException;

public interface ClientService{
	
	User signIn(String email, String password) throws ServiceException;
	
	void registration(String userName, String userEmail, String userPassword, String passwordRepeat, int userCardNumber,
			int userPersonalAccount) throws ServiceException;
	
	Auction findAuction(String lotName) throws ServiceException;

	List<Auction> getAuctions() throws ServiceException;

	Auction getAuction(String auctionId) throws ServiceException;
	
	
}
