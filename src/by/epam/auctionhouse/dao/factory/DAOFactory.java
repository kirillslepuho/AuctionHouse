package by.epam.auctionhouse.dao.factory;

import by.epam.auctionhouse.dao.AdminDAO;
import by.epam.auctionhouse.dao.UserDAO;
import by.epam.auctionhouse.dao.impl.AdminDAOImpl;
import by.epam.auctionhouse.dao.impl.UserDAOImpl;

public class DAOFactory {
	private static final DAOFactory daoFactory = new DAOFactory();

	private DAOFactory() {
	}

	private UserDAO userDAO = new UserDAOImpl();
    private AdminDAO adminDAO = new AdminDAOImpl();

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public AdminDAO getAdminDAO() {
		return adminDAO;
	}


	public static DAOFactory getInstance() {
		return daoFactory;
	}
}
