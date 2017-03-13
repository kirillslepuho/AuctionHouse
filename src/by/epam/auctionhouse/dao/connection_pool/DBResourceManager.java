package by.epam.auctionhouse.dao.connection_pool;

import java.util.ResourceBundle;

public class DBResourceManager {
	private final static DBResourceManager instance = new DBResourceManager();

	private ResourceBundle bundle = ResourceBundle.getBundle("by.epam.auctionhouse.dao.connection_pool.db");

	public static DBResourceManager getInstance() {
		return instance;
	}

    public String getValue(String key){
    	return bundle.getString(key);
    }

}
