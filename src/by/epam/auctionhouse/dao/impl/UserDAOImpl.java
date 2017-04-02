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

public class UserDAOImpl implements UserDAO {

	private static final Logger logger = LogManager.getLogger(UserDAOImpl.class.getName());

	private final static String AUCTION_ID_SQL = "au_id";

	private final static String AUCTION_LOT_SQL = "au_lot";

	private final static String AUCTION_PLACE_SQL = "au_place";

	private final static String AUCTION_BEGIN_DATE_SQL = "au_begin_date";

	private final static String AUCTION_EXPIRATION_DATE_SQL = "au_expiration_date";

	private final static String AUCTION_TIME_SQL = "au_time";

	private final static String AUCTION_TYPE_SQL = "au_type";

	private final static String AUCTION_IS_ACTIVE_SQL = "au_is_active";

	private final static String AUCTION_ROUNDS_SQL = "au_rounds";

	private final static String LOT_ID_SQL = "l_id";

	private final static String LOT_TYPE_SQL = "l_type";

	private final static String LOT_NAME_SQL = "l_name";

	private final static String LOT_CURRENT_PRICE_SQL = "l_current_price";

	private final static String LOT_DESCRIPTION_SQL = "l_description";

	private final static String LOT_IMAGE_SQL = "l_image";

	private final static String LOT_CLIENTS_SQL = "l_clients";

	private final static String LOT_OWER_SQL = "l_ower";

	private final static String LOT_BLITZ_BET_SQL = "l_blitz_bet";

	private final static String USER_ID_SQL = "us_id";

	private final static String USER_NAME_SQL = "us_name";

	private final static String USER_EMAIL_SQL = "us_email";

	private final static String USER_CARDNUMBER_SQL = "us_cardnumber";

	private final static String USER_ADMIN_STATUS_SQL = "us_adminstatus";

	private final static String USER_BLOCKED_SQL = "us_blocked";

	private final static String USER_PASSWORD_SQL = "us_password";

	private final static String BET_CLIENT_SQL = "be_client";

	private final static String BET_AUCTION_SQL = "be_auction";

	private final static String BET_BET_SQL = "be_bet";

	private final static String BET_WINNER_SQL = "be_winner";

	private final static String FIND_AUCTION_BY_LOT_NAME_SQL = "SELECT * FROM auctions INNER JOIN lots " + 
			"ON auctions.au_lot = lots.l_id where lots.l_name = ?;";

	private final static String GET_ALL_AUCTIONS_SQL = "SELECT * " + 
			"FROM auctions INNER JOIN lots ON auctions.au_lot = lots.l_id WHERE auctions.au_expiration_date > DATE_FORMAT(NOW(), '%Y-%m-%d') and auctions.au_is_active = true;";

	private final static String GET_AUCTION_BY_ID_SQL = "SELECT * FROM auctions INNER JOIN lots " + 
			"ON auctions.au_lot = lots.l_id where auctions.au_id = ?";

	private final static String ADD_USER_SQL = "INSERT INTO users (us_name,us_email, us_password, us_cardnumber,us_personal_account) VALUES(?, ?, ?, ?, ?);";

	private final static String GET_USER_SQL = "SELECT * FROM users WHERE us_email=? and us_password=?;";

	private final static String PLACE_ENGLISH_BET_SQL = "INSERT INTO auction_house.bets (be_client,be_auction,be_bet) VALUES(?, ?, ?);";

	private final static String CHANGE_LOT_CURRENT_PRICE_SQL = "UPDATE lots SET l_current_price=? " +  
			"WHERE l_id=?;";

	private final static String GET_USERS_BETS_SQL = "SELECT * FROM bets " +  
			"WHERE be_client=?;";

	private final static String GET_USERS_LOTS_SQL = "SELECT * FROM lots " +  
			"WHERE l_ower=?;";


	public UserDAOImpl() {

	}

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
			changeLotCurrentPrice(bet, lot);
			connection.commit();
		} catch (SQLException exception) {
			try {
				connection.rollback();
			} catch (SQLException e) {
				throw new DAOException("Can not place english bet", exception);
			} 
			throw new DAOException("Can not place english bet", exception);
		} finally {
			connectionPool.putConnection(connection);
		}

	}

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

	@Override
	public void changeLotCurrentPrice(String bet, Lot lot) throws DAOException {
		Connection connection = null;
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		PreparedStatement preparedStatement = null;

		try {
			connection = connectionPool.takeConnection();
			preparedStatement = connection.prepareStatement(CHANGE_LOT_CURRENT_PRICE_SQL);

			preparedStatement.setString(1, bet);
			preparedStatement.setString(2, lot.getId());

			preparedStatement.execute();
		} catch (SQLException exception) {
			throw new DAOException("Can update lot price", exception);
		} finally {
			releasePreparedStatement(preparedStatement);
			connectionPool.putConnection(connection);
		}

	}

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

	private void releasePreparedStatement(PreparedStatement preparedStatement) {
		if (preparedStatement != null) {
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				logger.error("Prepared statement not closed : ", e);
			}
		}
	}

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
			result.setRounds(resultSet.getInt(AUCTION_ROUNDS_SQL));

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
			result.setLot(lot);

		}
		return result;
	}




}
