package by.epam.auctionhouse.dao.sql;

public class UserQueries {

	public final static String FIND_AUCTION_BY_LOT_NAME_SQL = "SELECT * FROM auctions INNER JOIN lots " + 
			"ON auctions.au_lot = lots.l_id where lots.l_name = ?;";

	public final static String GET_ALL_AUCTIONS_SQL = "SELECT * " + 
			"FROM auctions INNER JOIN lots ON auctions.au_lot = lots.l_id WHERE auctions.au_expiration_date > DATE_FORMAT(NOW(), '%Y-%m-%d') and auctions.au_is_active = true order by au_expiration_date;";

	public final static String GET_AUCTION_BY_ID_SQL = "SELECT * FROM auctions INNER JOIN lots " + 
			"ON auctions.au_lot = lots.l_id where auctions.au_id = ?";

	public final static String ADD_USER_SQL = "INSERT INTO users (us_name,us_email, us_password, us_cardnumber,us_personal_account) VALUES(?, ?, ?, ?, ?);";

	public final static String GET_USER_SQL = "SELECT * FROM users WHERE us_email=? and us_password=?;";

	public final static String PLACE_ENGLISH_BET_SQL = "INSERT INTO auction_house.bets (be_client,be_auction,be_bet) VALUES(?, ?, ?);";

	public final static String CHANGE_LOT_CURRENT_PRICE_SQL = "UPDATE lots SET l_current_price=? " +  
			"WHERE l_id=?;";

	public final static String DELETE_BET_SQL = "Delete from bets" + 
			" WHERE be_client = ? and be_auction = ? and be_bet = ?;";

	public final static String GET_USERS_BETS_SQL = "SELECT * FROM bets " +  
			"WHERE be_client=?;";

	public final static String GET_HIGHEST_BET_SQL = "SELECT * FROM bets " +  
			" where be_auction = ? order by be_bet desc Limit 1;";

	public final static String GET_USERS_LOTS_SQL = "SELECT * FROM lots " +  
			"WHERE l_ower=?;";

	public final static String CHECK_USER_BLOCKED_SQL = "SELECT us_blocked FROM users " +  
			"WHERE us_id=?;";

}
