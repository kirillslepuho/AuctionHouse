package by.epam.auctionhouse.dao.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import by.epam.auctionhouse.bean.Auction;
import by.epam.auctionhouse.bean.Bet;
import by.epam.auctionhouse.bean.Lot;
import by.epam.auctionhouse.bean.User;
import by.epam.auctionhouse.dao.UserDAO;
import by.epam.auctionhouse.dao.connection_pool.ConnectionPool;
import by.epam.auctionhouse.dao.exception.DAOException;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import static by.epam.auctionhouse.dao.sql.ColumnNames.*;
import static by.epam.auctionhouse.dao.sql.UserQueries.*;

/**
 * Contains all methods for the users operations.
 *
 * @author Kirill Slepuho
 */
public class UserDAOImpl implements UserDAO {

	private static final Logger logger = LogManager.getLogger(UserDAOImpl.class.getName());

	
	public UserDAOImpl() {

	}

	/**
     * Adds user to the database.
     *
     * @param userName name of User
     * @param userEmail Emails user
     * @param userPassword Users password
     * @param userCardNumber Users card number
     * @param userPersonalAcount
     * @throws DAOException if the SQLException is thrown
     * @see SQLException
     */
	public void addUser(String userName, String userEmail, String userPassword, int userCardNumber, int userPersonalAccount) throws DAOException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		try {
			connection = connectionPool.takeConnection();
			preparedStatement = connection.prepareStatement(ADD_USER_SQL);

			preparedStatement.setString(1, userName);
			preparedStatement.setString(2, userEmail);
			preparedStatement.setString(3,userPassword);
			preparedStatement.setInt(4, userCardNumber);
			preparedStatement.setInt(5, userPersonalAccount);

			preparedStatement.execute();
		} catch (SQLException e) {
			throw new DAOException("Can not add user", e);
		} finally {
			connectionPool.putConnection(connection);
			releasePreparedStatement(preparedStatement);
		}
	}

	/**
     * Returns the user by specified email and password.
     *
     * @param userEmail the specified user email
     * @param userPassword the specified user password
     * @return the User object
     * @throws DAOException if the SQLException is thrown
     * @see SQLException
     */
	public User getUserByEmail(String userEmail,String userPassword) throws DAOException{
		Connection connection = null;
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		User user = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = connectionPool.takeConnection();
			preparedStatement = connection.prepareStatement(GET_USER_SQL);
			preparedStatement.setString(1, userEmail);
			preparedStatement.setString(2, userPassword);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()){
				user = new User();
				user.setId(Integer.parseInt(resultSet.getString(USER_ID_SQL)));
				user.setName(resultSet.getString(USER_NAME_SQL));
				user.setEmail(resultSet.getString(USER_EMAIL_SQL));
				user.setPassword(resultSet.getString(USER_PASSWORD_SQL));
				user.setCardnumber(Integer.parseInt(resultSet.getString(USER_CARDNUMBER_SQL)));
				user.setAdmin(resultSet.getBoolean(USER_ADMIN_STATUS_SQL));
				user.setBlocked(resultSet.getBoolean(USER_BLOCKED_SQL));
			}else{
				throw new DAOException("No such user with email" + userEmail);
			}
			return user;

		} catch (SQLException e) {
			throw new DAOException();
		}finally {
			connectionPool.putConnection(connection);
			releasePreparedStatement(preparedStatement);
		}

	}

	/**
     * Returns auction that contain the lotName.
     *
     * @param lotName
     * @return the auction
     * @throws DAOException if the SQLException is thrown
     * @see SQLException
     */
	@Override
	public Auction findAuction(String lotName) throws DAOException {
		Auction auction = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		try {
			connection = connectionPool.takeConnection();
			preparedStatement = connection.prepareStatement(FIND_AUCTION_BY_LOT_NAME_SQL);

			preparedStatement.setString(1, lotName);

			ResultSet resultSet;
			resultSet = preparedStatement.executeQuery();
			auction = getAuctionFromResultSet(resultSet);

			if (auction == null) {
				throw new DAOException("Product with such name not found");
			}

		} catch (SQLException e) {
			throw new DAOException("Product not found", e);
		} finally {
			connectionPool.putConnection(connection);
			releasePreparedStatement(preparedStatement);
		}
		return auction;
	}

	/**
     * Returns the list of auctions.
     *
     * @return the list of Auctions objects
     * @throws DAOException if the SQLException is thrown
     * @see SQLException
     */
	@Override
	public List<Auction> getAuctions() throws DAOException {
		List<Auction> result;
		result = new ArrayList<Auction>(0);
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ConnectionPool connectionPool = ConnectionPool.getInstance();

		try {
			connection = connectionPool.takeConnection();
			preparedStatement = connection.prepareStatement(GET_ALL_AUCTIONS_SQL);

			ResultSet resultSet;
			resultSet = preparedStatement.executeQuery();

			Auction auction;
			auction = getAuctionFromResultSet(resultSet);

			while (auction != null) {
				result.add(auction);
				auction = getAuctionFromResultSet(resultSet);
			}

		} catch (SQLException exception) {
			throw new DAOException("Error get auctions", exception);
		} finally {
			connectionPool.putConnection(connection);
			releasePreparedStatement(preparedStatement);
		}

		return result;
	}

	/**
     * Returns the auction by specified id.
     *
     * @param auctionId the specified auction id
     * @return the Auction object if there is a lot with the specified id in the database, otherwise returns null
     * @throws DAOException if the SQLException is thrown
     * @see SQLException
     */
	@Override
	public Auction getAuction(String auctionId) throws DAOException {
		Auction auction = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ConnectionPool connectionPool = ConnectionPool.getInstance();

		try {
			connection = connectionPool.takeConnection();
			preparedStatement = connection.prepareStatement(GET_AUCTION_BY_ID_SQL);

			preparedStatement.setString(1, auctionId);

			ResultSet resultSet;
			resultSet = preparedStatement.executeQuery();
			auction = getAuctionFromResultSet(resultSet);

			if (auction == null) {
				throw new DAOException("Auction not found");
			}

		} catch (SQLException exception) {
			throw new DAOException("Error get auction", exception);
		} finally {
			connectionPool.putConnection(connection);
			releasePreparedStatement(preparedStatement);
		}


		return auction;
	}

	

	@Override
	public void placeEngishBet(String clientId, String auctionId, String bet, Lot lot) throws DAOException {
		Connection connection = null;
		ConnectionPool connectionPool = ConnectionPool.getInstance();

		try {
			connection = connectionPool.takeConnection();
			connection.setAutoCommit(false);
            placeBet(clientId,auctionId,bet);
			changeLotCurrentPrice(bet, lot.getId());
			connection.commit();
		} catch (SQLException exception) {
			try {
				connection.rollback();
			} catch (SQLException e) {
				throw new DAOException("Can not place bet", exception);
			} 
			throw new DAOException("Can not place bet", exception);
		} finally {
			connectionPool.putConnection(connection);
		}

	}

	/**
     * Add bet to the database.
     *
     * @param clientId the specified client id
     * @param auctionId the specified auction id
     * @param bet Bet value
     * @throws DAOException if the SQLException is thrown
     * @see SQLException
     */
	public void placeBet(String clientId, String auctionId, String bet) throws DAOException {
		Connection connection = null;
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		PreparedStatement preparedStatement = null;

		try {
			connection = connectionPool.takeConnection();
			preparedStatement = connection.prepareStatement(PLACE_ENGLISH_BET_SQL);

			preparedStatement.setString(1, clientId);
			preparedStatement.setString(2, auctionId);
			preparedStatement.setString(3, bet);

			preparedStatement.execute();
		} catch (SQLException exception) {
			throw new DAOException("Can not place english bet", exception);
		} finally {
			releasePreparedStatement(preparedStatement);
			connectionPool.putConnection(connection);
		}

	}

	/**
     * Change lot current price in dataBase.
     *
     * @param bet new bet value
     * @param lotId the specified lot id
     * @throws DAOException if the SQLException is thrown
     * @see SQLException
     */
	@Override
	public void changeLotCurrentPrice(String bet, String lotId) throws DAOException {
		Connection connection = null;
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		PreparedStatement preparedStatement = null;

		try {
			connection = connectionPool.takeConnection();
			preparedStatement = connection.prepareStatement(CHANGE_LOT_CURRENT_PRICE_SQL);

			preparedStatement.setString(1, bet);
			preparedStatement.setString(2, lotId);

			preparedStatement.execute();
		} catch (SQLException exception) {
			throw new DAOException("Can update lot price", exception);
		} finally {
			releasePreparedStatement(preparedStatement);
			connectionPool.putConnection(connection);
		}

	}
	
	
	

	@Override
	public void cancellationBet(String clientId, String auctionId, String bet, Lot lot) throws DAOException {
		Connection connection = null;
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		try {
			
			connection = connectionPool.takeConnection();
			connection.setAutoCommit(false);
            deleteBet(clientId, auctionId, bet);
            String lastHighestBet = getHighestBet(auctionId);
			if(lastHighestBet == null){
				lastHighestBet = String.valueOf(lot.getStartPrice());
			}
			changeLotCurrentPrice(lastHighestBet, lot.getId());
			connection.commit();
		} catch (SQLException exception) {
			try {
				connection.rollback();
			} catch (SQLException e) {
				throw new DAOException("Can not cancell bet", exception);
			} 
			throw new DAOException("Can not cancell bet", exception);
		} finally {
			connectionPool.putConnection(connection);
		}

	}
	
	/**
     * Returns the list of bets by specified user id.
     *
     * @param userId Users id
     * @return the list of Bets objects
     * @throws DAOException if the SQLException is thrown
     * @see SQLException
     */
	@Override
	public List<Bet> getUsersBets(String userId) throws DAOException {
		List<Bet> result;
		result = new ArrayList<Bet>(0);
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ConnectionPool connectionPool = ConnectionPool.getInstance();

		try {
			connection = connectionPool.takeConnection();

			preparedStatement = connection.prepareStatement(GET_USERS_BETS_SQL);
			preparedStatement.setString(1, userId);
			ResultSet resultSet;
			resultSet = preparedStatement.executeQuery();

			Bet bet;
			while (resultSet.next()) {
				bet = new Bet();
				bet.setAuction(resultSet.getString(BET_AUCTION_SQL));
				bet.setClient(resultSet.getString(BET_CLIENT_SQL));
				bet.setBet(resultSet.getString(BET_BET_SQL));
				bet.setWinner(resultSet.getBoolean(BET_WINNER_SQL));
				result.add(bet);
			}

		} catch (SQLException exception) {
			throw new DAOException("Error get lots", exception);
		} finally {
			connectionPool.putConnection(connection);
			releasePreparedStatement(preparedStatement);
		}

		return result;
	}

	/**
     * Returns the list of lots by specified user id.
     *
     * @param userId Users id
     * @return the list of Lots objects
     * @throws DAOException if the SQLException is thrown
     * @see SQLException
     */
	@Override
	public List<Lot> getUsersLots(String userId) throws DAOException {
		List<Lot> result;
		result = new ArrayList<Lot>(0);
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ConnectionPool connectionPool = ConnectionPool.getInstance();

		try {
			connection = connectionPool.takeConnection();

			preparedStatement = connection.prepareStatement(GET_USERS_LOTS_SQL);
			preparedStatement.setString(1, userId);
			ResultSet resultSet;
			resultSet = preparedStatement.executeQuery();

			Lot lot;
			while (resultSet.next()) {
                lot = new Lot();
                lot.setId(resultSet.getString(LOT_ID_SQL));
    			lot.setCurrentPrice(resultSet.getInt(LOT_CURRENT_PRICE_SQL));
    			lot.setDescriprion(resultSet.getString(LOT_DESCRIPTION_SQL));
    			lot.setName(resultSet.getString(LOT_NAME_SQL));
    			lot.setType(resultSet.getString(LOT_TYPE_SQL));
    			lot.setImage(resultSet.getString(LOT_IMAGE_SQL));
    			lot.setClients(resultSet.getBoolean(LOT_CLIENTS_SQL));
    			lot.setClientOwer(resultSet.getString(LOT_OWER_SQL));
    			lot.setBlitzBet(resultSet.getString(LOT_BLITZ_BET_SQL));
				result.add(lot);
			}

		} catch (SQLException exception) {
			throw new DAOException("Error get users lots", exception);
		} finally {
			connectionPool.putConnection(connection);
			releasePreparedStatement(preparedStatement);
		}

		return result;
	}

	/**
     * Get auction from resultSet.
     *
     * @param resultSet
     * @return result,if exists,otherwise return null
     */
	private Auction getAuctionFromResultSet(ResultSet resultSet) throws SQLException {
		Auction result = null;
		Lot lot;
		if (resultSet.next()) {
			result = new Auction();
			result.setId(resultSet.getString(AUCTION_ID_SQL));
			result.setPlace(resultSet.getString(AUCTION_PLACE_SQL));
			result.setBeginDate(resultSet.getString(AUCTION_BEGIN_DATE_SQL));
			result.setExpirationDate(resultSet.getString(AUCTION_EXPIRATION_DATE_SQL));
			result.setTime(resultSet.getString(AUCTION_TIME_SQL));
			result.setType(resultSet.getString(AUCTION_TYPE_SQL));
			result.setIsActive(resultSet.getBoolean(AUCTION_IS_ACTIVE_SQL));

			lot = new Lot();
			lot.setId(resultSet.getString(LOT_ID_SQL));
			lot.setStartPrice(resultSet.getInt(LOT_START_PRICE_SQL));
			lot.setCurrentPrice(resultSet.getInt(LOT_CURRENT_PRICE_SQL));
			lot.setDescriprion(resultSet.getString(LOT_DESCRIPTION_SQL));
			lot.setName(resultSet.getString(LOT_NAME_SQL));
			lot.setType(resultSet.getString(LOT_TYPE_SQL));
			lot.setImage(resultSet.getString(LOT_IMAGE_SQL));
			lot.setClients(resultSet.getBoolean(LOT_CLIENTS_SQL));
			lot.setClientOwer(resultSet.getString(LOT_OWER_SQL));
			lot.setBlitzBet(resultSet.getString(LOT_BLITZ_BET_SQL));
			lot.setBlitzPrice(resultSet.getString(LOT_BLITZ_PRICE_SQL));
			result.setLot(lot);

		}
		return result;
	}
	
	/**
     * Deletes bet from the database.
     *
     * @param clientId the specified client id
     * @param auctionId the specified auction id
     * @param bet Bet value
     * @throws DAOException if the SQLException is thrown
     * @see SQLException
     */
	private void deleteBet(String clientId, String auctionId, String bet) throws DAOException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ConnectionPool connectionPool = ConnectionPool.getInstance();

		try {
			connection = connectionPool.takeConnection();
			preparedStatement = connection.prepareStatement(DELETE_BET_SQL);

			preparedStatement.setString(1, clientId);
			preparedStatement.setString(2, auctionId);
			preparedStatement.setString(3, bet);

			preparedStatement.execute();
		} catch (SQLException exception) {
			throw new DAOException("Delete error", exception);
		} finally {
			releasePreparedStatement(preparedStatement);
			connectionPool.putConnection(connection);
		}
		
	}
	
	/**
     * Returns the highest bet of specified auction id.
     *
     * @param auctonId Auction id
     * @return the value of highest bet
     * @throws DAOException if the SQLException is thrown
     * @see SQLException
     */
	private String getHighestBet(String auctionId) throws DAOException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ConnectionPool connectionPool = ConnectionPool.getInstance();

		try {
			connection = connectionPool.takeConnection();
			preparedStatement = connection.prepareStatement(GET_HIGHEST_BET_SQL);
			preparedStatement.setString(1, auctionId);
			ResultSet resultSet;
			resultSet = preparedStatement.executeQuery();
			String highestBet = null;
			
			if(resultSet.next()) {
				highestBet = resultSet.getString(BET_BET_SQL);
			}
			
			return highestBet;
		} catch (SQLException exception) {
			throw new DAOException("Delete error", exception);
		} finally {
			releasePreparedStatement(preparedStatement);
			connectionPool.putConnection(connection);
		}
		
	}

	/**
     * Close preparedStatement
     *
     * @throws DAOException if the SQLException is thrown
     * @see SQLException
     */
	private void releasePreparedStatement(PreparedStatement preparedStatement) {
		if (preparedStatement != null) {
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				logger.error("Prepared statement not closed : ", e);
			}
		}
	}




}
