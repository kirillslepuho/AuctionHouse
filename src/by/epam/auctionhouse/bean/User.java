package by.epam.auctionhouse.bean;

import java.io.Serializable;


public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String email;
	private String password;
	private int cardnumber;
	private boolean isAdmin;
	private boolean blocked;

	
	
	public User() {
		super();
	}

	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public int getCardnumber() {
		return cardnumber;
	}



	public void setCardnumber(int cardnumber) {
		this.cardnumber = cardnumber;
	}



	public boolean isAdmin() {
		return isAdmin;
	}



	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public boolean isBlocked() {
		return blocked;
	}

	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}

	
}