package by.epam.auctionhouse.dao.impl;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.epam.auctionhouse.bean.Auction;
import by.epam.auctionhouse.bean.Bet;
import by.epam.auctionhouse.bean.Lot;
import by.epam.auctionhouse.bean.User;
import by.epam.auctionhouse.dao.AdminDAO;
import by.epam.auctionhouse.dao.connection_pool.ConnectionPool;
import by.epam.auctionhouse.dao.exception.DAOException;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import static by.epam.auctionhouse.dao.sql.ColumnNames.*;
import static by.epam.auctionhouse.dao.sql.AdminQueries.*;

/**
 * Contains all methods for the administrator operations.
 *
 * @author Kirill Slepuho
 */
public class AdminDAOImpl implements AdminDAO {

	private static final Logger logger = LogManager.getLogger(AdminDAOImpl.class.getName());



	public AdminDAOImpl() {

	}
	
	/**
     * Adds auction to the database.
     *
     * @param auction the Auction object to add to the database
     * @throws DAOException if the SQLException is thrown
     * @see SQLException
     */
	public void addAuction(Auction auction) throws DAOException {
		Connection connection = null;
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		PreparedStatement preparedStatement = null;

		try {
			connection = connectionPool.takeConnection();
			preparedStatement = connection.prepareStatement(ADD_AUCTION_SQL);

			preparedStatement.setInt(1, Integer.parseInt(auction.getLot().getId()));
			preparedStatement.setString(2, auction.getPlace());
			preparedStatement.setString(3, auction.getBeginDate());
			preparedStatement.setString(4, auction.getExpirationDate());
			preparedStatement.setString(5, auction.getTime());
			preparedStatement.setString(6, auction.getType());
			preparedStatement.setBoolean(7, auction.getIsActive());


			preparedStatement.execute();
		} catch (SQLException exception) {
			throw new DAOException("Can not add auction", exception);
		} finally {
			releasePreparedStatement(preparedStatement);
			connectionPool.putConnection(connection);
		}
	}

	/**
     * Change auction active status in database.
     *
     * @param deleteId the specified auction id
     * @throws DAOException if the SQLException is thrown
     * @see SQLException
     */
	public void deleteAuction(String deleteId) throws DAOException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ConnectionPool connectionPool = ConnectionPool.getInstance();

		try {
			connection = connectionPool.takeConnection();
			preparedStatement = connection.prepareStatement(DELETE_AUCTION_SQL);

			preparedStatement.setString(1, deleteId);

			preparedStatement.execute();
		} catch (SQLException exception) {
			throw new DAOException("Delete error", exception);
		} finally {
			releasePreparedStatement(preparedStatement);
			connectionPool.putConnection(connection);
		}
	}

	/**
     * Updates auction in the database.
     *
     * @param auction the Auction object to update to the database
     * @throws DAOException if the SQLException is thrown
     * @see SQLException
     */
	public void editAuction(Auction auction, String changeId) throws DAOException {
		Connection connection = null;
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		PreparedStatement preparedStatement = null;

		try {
			connection = connectionPool.takeConnection();
			preparedStatement = connection.prepareStatement(EDIT_AUCTION_SQL);

			preparedStatement.setInt(1, Integer.parseInt(auction.getLot().getId()));
			preparedStatement.setString(2, auction.getPlace());
			preparedStatement.setString(3, auction.getBeginDate());
			preparedStatement.setString(4, auction.getExpirationDate());
			preparedStatement.setString(5, auction.getTime());
			preparedStatement.setString(6, auction.getType());
			preparedStatement.setBoolean(7, auction.getIsActive());
			preparedStatement.setString(8, changeId);

			preparedStatement.execute();
		} catch (SQLException exception) {
			throw new DAOException("Can not edit auction", exception);
		} finally {
			releasePreparedStatement(preparedStatement);
			connectionPool.putConnection(connection);
		}
	}

	/**
     * Adds lot to the database.
     *
     * @param lot the Lot object to add to the database
     * @throws DAOException if the SQLException is thrown
     * @see SQLException
     */
	public void addLot(Lot lot) throws DAOException {
		Connection connection = null;
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		PreparedStatement preparedStatement = null;

		try {
			connection = connectionPool.takeConnection();
			preparedStatement = connection.prepareStatement(ADD_LOT_SQL);

			preparedStatement.setString(1, lot.getType());
			preparedStatement.setString(2, lot.getName());
			preparedStatement.setString(3, Integer.toString(lot.getCurrentPrice()));
			preparedStatement.setString(4, Integer.toString(lot.getCurrentPrice()));
			preparedStatement.setString(5, lot.getDescriprion());
			preparedStatement.setString(6, lot.getImage());
			preparedStatement.setBoolean(7,lot.isClients());
			preparedStatement.setString(8, lot.getClientOwer());
			preparedStatement.setString(9, lot.getBlitzBet());
			preparedStatement.setString(10, lot.getBlitzPrice());


			preparedStatement.execute();
		} catch (SQLException exception) {
			throw new DAOException("Can not add lot", exception);
		} finally {
			releasePreparedStatement(preparedStatement);
			connectionPool.putConnection(connection);
		}
	}

	/**
     * Updates lot in the database.
     *
     * @param lot the Lot object to update to the database
     * @throws DAOException if the SQLException is thrown
     * @see SQLException
     */
	public void editLot(Lot lot, String changeId) throws DAOException {
		Connection connection = null;
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		PreparedStatement preparedStatement = null;

		try {
			connection = connectionPool.takeConnection();
			preparedStatement = connection.prepareStatement(EDIT_LOT_SQL);

			preparedStatement.setString(1, lot.getType());
			preparedStatement.setString(2, lot.getName());
			preparedStatement.setString(3, Integer.toString(lot.getCurrentPrice()));
			preparedStatement.setString(4, lot.getDescriprion());
			preparedStatement.setString(5, lot.getImage());
			preparedStatement.setString(6, lot.getBlitzBet());
			preparedStatement.setString(7, lot.getBlitzPrice());
			preparedStatement.setString(8, changeId);

			preparedStatement.execute();
		} catch (SQLException exception) {
			throw new DAOException("Can not edit lot", exception);
		} finally {
			releasePreparedStatement(preparedStatement);
			connectionPool.putConnection(connection);
		}
	}
	
	/**
     * Change lot block status in database.
     *
     * @param deleteId the specified lot id
     * @throws DAOException if the SQLException is thrown
     * @see SQLException
     */
	@Override
	public void blockLot(String deleteId) throws DAOException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ConnectionPool connectionPool = ConnectionPool.getInstance();

		try {
			connection = connectionPool.takeConnection();
			preparedStatement = connection.prepareStatement(DELETE_LOT_SQL);

			preparedStatement.setString(1, deleteId);

			preparedStatement.execute();
		} catch (SQLException exception) {
			throw new DAOException("Delete lot error", exception);
		} finally {
			releasePreparedStatement(preparedStatement);
			connectionPool.putConnection(connection);
		}
		
	}

	/**
     * Returns the lot by specified id.
     *
     * @param lotId the specified lot id
     * @return the Lot object if there is a lot with the specified id in the database, otherwise returns null
     * @throws DAOException if the SQLException is thrown
     * @see SQLException
     */
	public Lot getLotById(String lotId) throws DAOException{
		Connection connection = null;
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Lot lot = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = connectionPool.takeConnection();
			preparedStatement = connection.prepareStatement(GET_LOT_BY_ID_SQL);
			preparedStatement.setString(1, lotId);
			lot = new Lot();
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				lot.setId(lotId);
				lot.setType(resultSet.getString(LOT_TYPE_SQL));
				lot.setName(resultSet.getString(LOT_NAME_SQL));
				lot.setStartPrice(resultSet.getInt(LOT_START_PRICE_SQL));
				lot.setCurrentPrice(resultSet.getInt(LOT_CURRENT_PRICE_SQL));
				lot.setDescriprion(resultSet.getString(LOT_DESCRIPTION_SQL));
				lot.setImage(resultSet.getString(LOT_IMAGE_SQL));
				lot.setClients(resultSet.getBoolean(LOT_CLIENTS_SQL));
				lot.setClientOwer(resultSet.getString(LOT_OWER_SQL));
				lot.setBlitzBet(resultSet.getString(LOT_BLITZ_BET_SQL));
				lot.setBlitzPrice(resultSet.getString(LOT_BLITZ_PRICE_SQL));
			}
			return lot;

		} catch (SQLException exception) {
			throw new DAOException("Can not get lot", exception);
		}finally {
			connectionPool.putConnection(connection);
			releasePreparedStatement(preparedStatement);
		}

	}

	/**
     * Returns the list of lots.
     *
     * @return the list of Lots objects
     * @throws DAOException if the SQLException is thrown
     * @see SQLException
     */
	@Override
	public List<Lot> getLots() throws DAOException {
		List<Lot> result;
		result = new ArrayList<Lot>(0);
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ConnectionPool connectionPool = ConnectionPool.getInstance();

		try {
			connection = connectionPool.takeConnection();
			preparedStatement = connection.prepareStatement(GET_ALL_AVAILABLE_LOTS_SQL);

			ResultSet resultSet;
			resultSet = preparedStatement.executeQuery();

			Lot lot;
			while (resultSet.next()) {
				lot = new Lot();
				lot.setId(resultSet.getString(LOT_ID_SQL));
				lot.setType(resultSet.getString(LOT_TYPE_SQL));
				lot.setName(resultSet.getString(LOT_NAME_SQL));
				lot.setStartPrice(resultSet.getInt(LOT_START_PRICE_SQL));
				lot.setCurrentPrice(resultSet.getInt(LOT_CURRENT_PRICE_SQL));
				lot.setDescriprion(resultSet.getString(LOT_DESCRIPTION_SQL));
				lot.setImage(resultSet.getString(LOT_IMAGE_SQL));
				lot.setClients(resultSet.getBoolean(LOT_CLIENTS_SQL));
				lot.setClientOwer(resultSet.getString(LOT_OWER_SQL));
				lot.setBlitzBet(resultSet.getString(LOT_BLITZ_BET_SQL));
				lot.setBlitzPrice(resultSet.getString(LOT_BLITZ_PRICE_SQL));
				result.add(lot);
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
     * Set auction winner by specified auction Id,client Id, bet value.
     *
     * @param auctionID the specified auction Id
     * @param clientID  the specified client Id
     * @param bet the wins bet Value
     * @throws DAOException if the SQLException is thrown
     * @see SQLException
     */
	@Override
	public void setAuctionWinner(String auctionID, String clientID, String bet) throws DAOException {
		Connection connection = null;
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		PreparedStatement preparedStatement = null;

		try {
			connection = connectionPool.takeConnection();
			preparedStatement = connection.prepareStatement(SET_AUCTION_WINNER_SQL);

			preparedStatement.setString(1, clientID);
			preparedStatement.setString(2, auctionID);
			preparedStatement.setString(3, bet);

			preparedStatement.execute();
		} catch (SQLException exception) {
			throw new DAOException("Can not set winner", exception);
		} finally {
			releasePreparedStatement(preparedStatement);
			connectionPool.putConnection(connection);
		}		
	}
	
	/**
     * Set all bets false in specified auction.
     *
     * @param auctionID the specified auction Id
     * @throws DAOException if the SQLException is thrown
     * @see SQLException
     */
	@Override
	public void setBetsWinFalse(String auctionId) throws DAOException {
		Connection connection = null;
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		PreparedStatement preparedStatement = null;

		try {
			connection = connectionPool.takeConnection();
			preparedStatement = connection.prepareStatement(SET_BETS_FALSE_WINNER_SQL);
			preparedStatement.setString(1, auctionId);
			
			preparedStatement.execute();
		} catch (SQLException exception) {
			throw new DAOException("Can update bets", exception);
		} finally {
			releasePreparedStatement(preparedStatement);
			connectionPool.putConnection(connection);
		}		
		
	}

	/**
     * Get auction winner by specified auction Id.
     *
     * @param auctionID the specified auction Id
     * @return the User, who win auction
     * @throws DAOException if the SQLException is thrown
     * @see SQLException
     */
	@Override
	public User getAuctionWinner(String auctionID) throws DAOException {
		User user = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ConnectionPool connectionPool = ConnectionPool.getInstance();


		try {
			connection = connectionPool.takeConnection();
			preparedStatement = connection.prepareStatement(GET_AUCTION_WINNER_SQL);
			preparedStatement.setString(1, auctionID);
			user = new User();
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				user.setId(resultSet.getInt(USER_ID_SQL));
				user.setName(resultSet.getString(USER_NAME_SQL));
				user.setEmail(resultSet.getString(USER_EMAIL_SQL));
			}


		} catch (SQLException exception) {
			throw new DAOException("Error get auction winner", exception);
		} finally {
			connectionPool.putConnection(connection);
			releasePreparedStatement(preparedStatement);
		}


		return user;
	}

	/**
     * Returns the list of users.
     *
     * @return the list of Users objects
     * @throws DAOException if the SQLException is thrown
     * @see SQLException
     */
	@Override
	public List<User> getUsers() throws DAOException {
		List<User> result;
		result = new ArrayList<User>(0);
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ConnectionPool connectionPool = ConnectionPool.getInstance();

		try {
			connection = connectionPool.takeConnection();
			preparedStatement = connection.prepareStatement(GET_ALL_USERS_SQL);
			ResultSet resultSet;
			resultSet = preparedStatement.executeQuery();
			User user;
			while (resultSet.next()) {
				user = new User();
				user.setId(resultSet.getInt(USER_ID_SQL));
				user.setName(resultSet.getString(USER_NAME_SQL));
				user.setEmail(resultSet.getString(USER_EMAIL_SQL));
				user.setCardnumber(resultSet.getInt(USER_CARDNUMBER_SQL));
				user.setBlocked(resultSet.getBoolean(USER_BLOCKED_SQL));
				result.add(user);
			}
		} catch (SQLException exception) {
			throw new DAOException("Error get users", exception);
		} finally {
			connectionPool.putConnection(connection);
			releasePreparedStatement(preparedStatement);
		}

		return result;
	}

	/**
     * Returns the list of bets by specified auction id.
     *
     * @param auctionID the specified auction Id
     * @return the list of Bets objects
     * @throws DAOException if the SQLException is thrown
     * @see SQLException
     */
	@Override
	public List<Bet> getAuctionsBets(String auctionId) throws DAOException {
		List<Bet> result;
		result = new ArrayList<Bet>(0);
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		try {
			connection = connectionPool.takeConnection();
			preparedStatement = connection.prepareStatement(GET_AUCTIONS_BETS_SQL);
			preparedStatement.setString(1, auctionId);
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
			throw new DAOException("Error get auction bets", exception);
		} finally {
			connectionPool.putConnection(connection);
			releasePreparedStatement(preparedStatement);
		}

		return result;
	}

	/**
     * Updates user status.
     *
     * @param userId the specified user id
     * @param status the user status, such as Blocked, Unblocked
     * @throws DAOException if the SQLException is thrown
     * @see SQLException
     */
	@Override
	public void setUserBlockStatus(String userId,boolean status) throws DAOException {
		Connection connection = null;
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		PreparedStatement preparedStatement = null;

		try {
			connection = connectionPool.takeConnection();
			preparedStatement = connection.prepareStatement(SET_USER_BLOCKED_STATUS_SQL);
			preparedStatement.setBoolean(1, status);
			preparedStatement.setString(2, userId);

			preparedStatement.execute();
		} catch (SQLException exception) {
			throw new DAOException("Can not change user status", exception);
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
