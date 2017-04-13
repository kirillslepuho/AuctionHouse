package by.epam.auctionhouse.dao.sql;

public class AdminQueries {

	


	public final static String ADD_AUCTION_SQL =
			"INSERT INTO auction_house.auctions (au_lot,au_place,au_begin_date,au_expiration_date,au_time,au_type,au_is_active) " +
					"VALUES(?, ?, ?, ?, ?, ?, ?);";

	public final static String EDIT_AUCTION_SQL =
			"UPDATE auctions SET au_lot=?, au_place=?, au_begin_date=?," +  
					"au_expiration_date=?, au_time=?, au_type=?, au_is_active=? " +  
					"WHERE au_id=?";

	public final static String DELETE_AUCTION_SQL = "UPDATE auctions SET auctions.au_is_active = 0 " +  
			"WHERE auctions.au_id=?";

	public final static String ADD_LOT_SQL = "INSERT INTO auction_house.lots (l_type,l_name,l_start_price,l_current_price,l_description,l_image,l_clients,l_ower,l_blitz_bet,l_blitz_price)" + 
			"VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

	public final static String EDIT_LOT_SQL = 
			"UPDATE lots SET l_type=?, l_name=?,l_current_price=?, l_description=?, l_image=?, l_blitz_bet=?,l_blitz_price=? " +  
					"WHERE l_id=?;";
	
	public final static String DELETE_LOT_SQL = "UPDATE lots SET lots.l_blocked = true " +  
			"WHERE lots.l_id=?";

	public final static String GET_LOT_BY_ID_SQL = "SELECT * FROM lots " +  
			"WHERE l_id = ?;";

	public final static String GET_ALL_AVAILABLE_LOTS_SQL = "SELECT * FROM auction_house.lots " + 
			" where l_id NOT IN (Select au_lot FROM auctions) AND l_blocked=false;";

	public final static String GET_ALL_USERS_SQL = "SELECT * " + 
			"FROM users";

	public final static String GET_AUCTION_WINNER_SQL = "SELECT * FROM users " + 
			" where us_id = (Select be_client from bets where be_auction = ? and be_winner = true);";

	public final static String SET_AUCTION_WINNER_SQL = "UPDATE bets SET be_winner=true " + 
			" WHERE be_client = ? and be_auction = ? and be_bet = ?;";
	
	public final static String SET_BETS_FALSE_WINNER_SQL = "UPDATE bets SET be_winner=false " + 
			" WHERE be_auction = ?;";

	public final static String GET_AUCTIONS_BETS_SQL = "SELECT * FROM bets " +  
			"WHERE be_auction=? order by be_bet desc;";

	public final static String SET_USER_BLOCKED_STATUS_SQL = "UPDATE users SET us_blocked = ? " + 
			" where us_id = ?;";

}
