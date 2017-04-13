package by.epam.auctionhouse.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.logging.log4j.Logger;

import by.epam.auctionhouse.dao.connection_pool.ConnectionPool;
import by.epam.auctionhouse.dao.exception.DAOException;
import by.epam.auctionhouse.service.exception.ServiceException;
import by.epam.auctionhouse.service.factory.ServiceFactory;

import org.apache.logging.log4j.LogManager;

public class ServletListener implements ServletContextListener {

    private final Logger logger = LogManager.getLogger(ServletListener.class.getName());


    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try {
        	ConnectionPool connectionPool;
            connectionPool = ConnectionPool.getInstance();
            connectionPool.init();
            logger.info("Connection pool created.");
        } catch (DAOException exception) {
            logger.fatal("Error while creating connection pool : ", exception);
            throw new RuntimeException("Some problems with JDBC", exception);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    	ConnectionPool connectionPool;
        connectionPool = ConnectionPool.getInstance();
        connectionPool.destroy();
        logger.info("Connection pool destroyed.");
    }
}

