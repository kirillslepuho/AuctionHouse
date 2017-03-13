package by.epam.auctionhouse.service.factory;

import by.epam.auctionhouse.dao.connection_pool.ConnectionPool;
import by.epam.auctionhouse.dao.exception.DAOException;
import by.epam.auctionhouse.service.AdminService;
import by.epam.auctionhouse.service.ClientService;
import by.epam.auctionhouse.service.exception.ServiceException;
import by.epam.auctionhouse.service.impl.AdminServiceImpl;
import by.epam.auctionhouse.service.impl.ClientServiceImpl;


public class ServiceFactory {
	private static final ServiceFactory instance = new ServiceFactory();

	private ClientService clientService = new ClientServiceImpl();
	private AdminService adminService = new AdminServiceImpl();

	private ServiceFactory() {
	}
	
	 public void init() throws ServiceException {
	        try {
	            ConnectionPool connectionPool;
	            connectionPool = ConnectionPool.getInstance();
	            connectionPool.init();
	        } catch (DAOException e) {
	            throw new ServiceException(e);
	        }
	    }

	    public void destroy() {
	        ConnectionPool connectionPool;
	        connectionPool = ConnectionPool.getInstance();
	        connectionPool.destroy();
	    }

	public ClientService getClientService() {
		return clientService;
	}
	

	public AdminService getAdminService() {
		return adminService;
	}

	public static ServiceFactory getInstance() {
		return instance;
	}
}
