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

public class AdminDAOImpl implements AdminDAO {

	private static final Logger logger = LogManager.getLogger(AdminDAOImpl.class.getName());

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

	private final static String USER_BLOCKED_SQL = "us_blocked";

	private final static String BET_CLIENT_SQL = "be_client";

	private final static String BET_AUCTION_SQL = "be_auction";

	private final static String BET_BET_SQL = "be_bet";

	private final static String BET_WINNER_SQL = "be_winner";


	private final static String ADD_AUCTION_SQL =
			"INSERT INTO auction_house.auctions (au_lot,au_place,au_begin_date,au_expiration_date,au_time,au_type,au_is_active) " +
					"VALUES(?, ?, ?, ?, ?, ?, ?);";

	private final static String EDIT_AUCTION_SQL =
			"UPDATE auctions SET au_lot=?, au_place=?, au_begin_date=?," +  
					"au_expiration_date=?, au_time=?, au_type=?, au_is_active=?, au_rounds=? " +  
					"WHERE au_id=?";

	private final static String DELETE_AUCTION_SQL = "UPDATE auctions SET auctions.au_is_active = 0 " +  
			"WHERE auctions.au_id=?";

	private final static String ADD_LOT_SQL = "INSERT INTO auction_house.lots (l_type,l_name,l_current_price,l_description,l_image,l_clients,l_ower,l_blitz_bet)" + 
			"VALUES(?, ?, ?, ?, ?, ?, ?, ?);";

	private final static String EDIT_LOT_SQL = 
			"UPDATE lots SET l_type=?, l_name=?,l_current_price=?, l_description=?, l_image=?, l_blitz_bet=? " +  
					"WHERE l_id=?;";
	
	private final static String DELETE_LOT_SQL = "UPDATE lots SET lots.l_blocked = true " +  
			"WHERE lots.l_id=?";

	private final static String GET_LOT_BY_ID_SQL = "SELECT * FROM lots " +  
			"WHERE l_id = ?;";

	private final static String GET_ALL_AVAILABLE_LOTS_SQL = "SELECT * FROM auction_house.lots " + 
			" where l_id NOT IN (Select au_lot FROM auctions) AND l_blocked=false;";

	private final static String GET_ALL_USERS_SQL = "SELECT * " + 
			"FROM users";

	private final static String GET_AUCTION_WINNER_SQL = "SELECT * FROM users " + 
			" where us_id = (Select be_client from bets where be_auction = ? and be_winner = true);";

	private final static String SET_AUCTION_WINNER_SQL = "UPDATE bets SET be_winner=true " + 
			" WHERE be_client = ? and be_auction = ? and be_bet = ?;";
	
	private final static String SET_BETS_FALSE_WINNER_SQL = "UPDATE bets SET be_winner=false " + 
			" WHERE be_auction = ?;";

	private final static String GET_AUCTIONS_BETS_SQL = "SELECT * FROM bets " +  
			"WHERE be_auction=? order by be_bet desc;";

	private final static String SET_USER_BLOCKED_STATUS_SQL = "UPDATE users SET us_blocked = ? " + 
			" where us_id = ?;";


	public AdminDAOImpl() {

	}

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
			preparedStatement.setInt(8, auction.getRounds());
			preparedStatement.setString(9, changeId);

			preparedStatement.execute();
		} catch (SQLException exception) {
			throw new DAOException("Can not edit auction", exception);
		} finally {
			releasePreparedStatement(preparedStatement);
			connectionPool.putConnection(connection);
		}
	}

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
			preparedStatement.setString(4, lot.getDescriprion());
			preparedStatement.setString(5, lot.getImage());
			preparedStatement.setBoolean(6,lot.isClients());
			preparedStatement.setString(7, lot.getClientOwer());
			preparedStatement.setString(8, lot.getBlitzBet());


			preparedStatement.execute();
		} catch (SQLException exception) {
			throw new DAOException("Can not add lot", exception);
		} finally {
			releasePreparedStatement(preparedStatement);
			connectionPool.putConnection(connection);
		}
	}


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
			preparedStatement.setString(7, changeId);

			preparedStatement.execute();
		} catch (SQLException exception) {
			throw new DAOException("Can not edit lot", exception);
		} finally {
			releasePreparedStatement(preparedStatement);
			connectionPool.putConnection(connection);
		}
	}

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
				lot.setCurrentPrice(resultSet.getInt(LOT_CURRENT_PRICE_SQL));
				lot.setDescriprion(resultSet.getString(LOT_DESCRIPTION_SQL));
				lot.setImage(resultSet.getString(LOT_IMAGE_SQL));
				lot.setClients(resultSet.getBoolean(LOT_CLIENTS_SQL));
				lot.setClientOwer(resultSet.getString(LOT_OWER_SQL));
				lot.setBlitzBet(resultSet.getString(LOT_BLITZ_BET_SQL));
			}
			return lot;

		} catch (SQLException exception) {
			throw new DAOException("Can not get lot", exception);
		}finally {
			connectionPool.putConnection(connection);
			releasePreparedStatement(preparedStatement);
		}

	}

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
				lot.setCurrentPrice(resultSet.getInt(LOT_CURRENT_PRICE_SQL));
				lot.setDescriprion(resultSet.getString(LOT_DESCRIPTION_SQL));
				lot.setImage(resultSet.getString(LOT_IMAGE_SQL));
				lot.setClients(resultSet.getBoolean(LOT_CLIENTS_SQL));
				lot.setClientOwer(resultSet.getString(LOT_OWER_SQL));
				lot.setBlitzBet(resultSet.getString(LOT_BLITZ_BET_SQL));
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
