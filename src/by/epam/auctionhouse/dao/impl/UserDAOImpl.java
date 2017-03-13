package by.epam.auctionhouse.dao.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import by.epam.auctionhouse.bean.Auction;
import by.epam.auctionhouse.bean.Lot;
import by.epam.auctionhouse.bean.User;
import by.epam.auctionhouse.dao.UserDAO;
import by.epam.auctionhouse.dao.connection_pool.ConnectionPool;
import by.epam.auctionhouse.dao.exception.DAOException;



public class UserDAOImpl implements UserDAO {

	private final static String FIND_AUCTION_BY_LOT_NAME_SQL = "SELECT * FROM auctions INNER JOIN lots " + 
			"ON auctions.au_lot = lots.l_id where lots.l_name = ?;";

	private final static String GET_ALL_AUCTIONS_SQL = "SELECT * " + 
			"FROM auctions INNER JOIN lots ON auctions.au_lot = lots.l_id WHERE auctions.au_expiration_date > DATE_FORMAT(NOW(), '%Y-%m-%d');;";

	private final static String GET_AUCTION_BY_ID_SQL = "SELECT * FROM auctions INNER JOIN lots " + 
			"ON auctions.au_lot = lots.l_id where auctions.au_id = ?";

	private final static String ADD_USER_SQL = "INSERT INTO users (us_name,us_email, us_password, us_cardnumber,us_personal_account) VALUES(?, ?, ?, ?, ?);";
	private final static String GET_USER_SQL = "SELECT * FROM users WHERE us_email=? and us_password=?;";

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
			user = new User();
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				user.setId(Integer.parseInt(resultSet.getString("us_id")));
				user.setName(resultSet.getString("us_name"));
				user.setEmail(resultSet.getString("us_email"));
				user.setPassword(resultSet.getString("us_password"));
				user.setCardnumber(Integer.parseInt(resultSet.getString("us_cardnumber")));
				user.setAdmin(resultSet.getBoolean("us_adminstatus"));
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

	private Auction getAuctionFromResultSet(ResultSet resultSet) throws SQLException {
		Auction result = null;
		Lot lot;
		if (resultSet.next()) {
			result = new Auction();
			result.setId(resultSet.getString("au_id"));
			result.setPlace(resultSet.getString("au_place"));
			result.setBeginDate(resultSet.getString("au_begin_date"));
			result.setExpirationDate(resultSet.getString("au_expiration_date"));
			result.setTime(resultSet.getString("au_time"));
			result.setType(resultSet.getString("au_type"));
			result.setIsActive(resultSet.getBoolean("au_is_active"));
			result.setRounds(resultSet.getInt("au_rounds"));

			lot = new Lot();
			lot.setId(resultSet.getString("l_id"));
			lot.setCurrentPrice(resultSet.getInt("l_current_price"));
			lot.setDescriprion(resultSet.getString("l_description"));
			lot.setName(resultSet.getString("l_name"));
			lot.setType(resultSet.getString("l_type"));
			lot.setImage(resultSet.getString("l_image"));
			result.setLot(lot);

		}
		return result;
	}
	
	private void releasePreparedStatement(PreparedStatement preparedStatement) {
		if (preparedStatement != null) {
			try {
				preparedStatement.close();
			} catch (SQLException e) {

			}
		}
	}


}
