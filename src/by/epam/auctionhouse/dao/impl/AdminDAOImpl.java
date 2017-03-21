package by.epam.auctionhouse.dao.impl;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.epam.auctionhouse.bean.Auction;
import by.epam.auctionhouse.bean.Lot;
import by.epam.auctionhouse.dao.AdminDAO;
import by.epam.auctionhouse.dao.connection_pool.ConnectionPool;
import by.epam.auctionhouse.dao.exception.DAOException;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class AdminDAOImpl implements AdminDAO {

	private static final Logger logger = LogManager.getLogger(AdminDAOImpl.class.getName());

	private final static String ADD_AUCTION_SQL =
			"INSERT INTO auction_house.auctions (au_lot,au_place,au_begin_date,au_expiration_date,au_time,au_type,au_is_active) " +
					"VALUES(?, ?, ?, ?, ?, ?, ?);";

	private final static String EDIT_AUCTION_SQL =
			"UPDATE auctions SET au_lot=?, au_place=?, au_begin_date=?," +  
					"au_expiration_date=?, au_time=?, au_type=?, au_is_active=?, au_rounds=? " +  
					"WHERE au_id=?";

	private final static String DELETE_AUCTION_SQL = "UPDATE auctions SET auctions.au_is_active = 0 " +  
			"WHERE auctions.au_id=?";

	private final static String ADD_LOT_SQL = "INSERT INTO auction_house.lots (l_type,l_name,l_current_price,l_description,l_image,l_clients)" + 
			"VALUES(?, ?, ?, ?, ?, ?);";

	private final static String EDIT_LOT_SQL = 
			"UPDATE lots SET l_type=?, l_name=?,l_current_price=?, l_description=?, l_image=? " +  
					"WHERE l_id=?;";

	private final static String GET_LOT_BY_ID_SQL = "SELECT * FROM lots " +  
			"WHERE l_id = ?;";
	
	private final static String GET_ALL_LOTS_SQL = "SELECT * " + 
	"FROM lots";




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
			preparedStatement.setString(6, changeId);

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
				lot.setType(resultSet.getString("l_type"));
				lot.setName(resultSet.getString("l_current_price"));
				lot.setDescriprion(resultSet.getString("l_description"));
				lot.setImage(resultSet.getString("l_image"));
				lot.setClients(resultSet.getBoolean("l_clients"));
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
	            preparedStatement = connection.prepareStatement(GET_ALL_LOTS_SQL);

	            ResultSet resultSet;
	            resultSet = preparedStatement.executeQuery();

	            Lot lot;
	            while (resultSet.next()) {
	            	lot = new Lot();
	            	lot.setId(resultSet.getString("l_id"));
	    			lot.setCurrentPrice(resultSet.getInt("l_current_price"));
	    			lot.setDescriprion(resultSet.getString("l_description"));
	    			lot.setName(resultSet.getString("l_name"));
	    			lot.setType(resultSet.getString("l_type"));
	    			lot.setImage(resultSet.getString("l_image"));
	    			lot.setClients(resultSet.getBoolean("l_clients"));
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
