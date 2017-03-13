package by.epam.auctionhouse.dao.connection_pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import by.epam.auctionhouse.dao.exception.DAOException;

public class ConnectionPool {

	private static ConnectionPool instance = new ConnectionPool();
	private String user;
	private String password;
	private String url;
	private String jdbc_class;
	private int number_connections;
	private final Logger logger = LogManager.getLogger(ConnectionPool.class.getName());


	private BlockingQueue<Connection> connections;
	private CopyOnWriteArrayList<Connection> usedConnections;

	private ConnectionPool(){
		DBResourceManager dbResourceManager = DBResourceManager.getInstance();
		this.jdbc_class = dbResourceManager.getValue(DBParameter.DB_DRIVER);
		this.url = dbResourceManager.getValue(DBParameter.DB_URL);
		this.user = dbResourceManager.getValue(DBParameter.DB_USER);
		this.password = dbResourceManager.getValue(DBParameter.DB_PASSWORD);
		this.number_connections = Integer.parseInt(dbResourceManager.getValue(DBParameter.DB_POOL_SIZE));

	}

	public static ConnectionPool getInstance() {
		return instance;
	}

	public void init() throws DAOException {
		try {
			Class.forName(jdbc_class);
		} catch (ClassNotFoundException exception) {
			throw new DAOException("Problem with connection to JDBC", exception);
		}
		connections = new ArrayBlockingQueue<>(number_connections);
		usedConnections = new CopyOnWriteArrayList<>();

		for (int i = 0; i < number_connections; i++) {
			Connection connection;
			try {
				connection = DriverManager.getConnection(url, user, password);
			} catch (SQLException exception) {
				throw new DAOException("Problem with establishing connection", exception);
			}
			connections.add(connection);
		}
		logger.info("Connection initialized.");
	}

	public void destroy() {

		for (Connection connection : connections) {
			try {
				connection.close();
			} catch (SQLException exception) {
				logger.error("Problem with closing connection : ", exception);
			}
		}

		for (Connection connection : usedConnections) {
			try {
				connection.close();
			} catch (SQLException exception) {
				logger.error("Problem with closing connection : ", exception);
			}
		}
	}

	public Connection takeConnection() throws DAOException {
		Connection connection = null;
		try {
			connection = connections.take();
			usedConnections.add(connection);
		} catch (InterruptedException e) {
			throw new DAOException(e);
		}
		return connection;
	}

	public void putConnection(Connection connection) throws DAOException {
		if (connection != null) {
			connections.add(connection);
			usedConnections.remove(connection);
		} else {
			logger.warn("Null connection returned.");
		}
	}

}
