package by.epam.auctionhouse.bean;

public class Bet {
	private String client;
	private String auction;
	private String bet;
	private boolean winner;

	

	public Bet(String client, String auction, String bet, boolean winner) {
		super();
		this.client = client;
		this.auction = auction;
		this.bet = bet;
		this.winner = winner;
	}
	public Bet() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getClient() {
		return client;
	}
	public void setClient(String client) {
		this.client = client;
	}
	public String getAuction() {
		return auction;
	}
	public void setAuction(String auction) {
		this.auction = auction;
	}
	public String getBet() {
		return bet;
	}
	public void setBet(String bet) {
		this.bet = bet;
	}
	public boolean isWinner() {
		return winner;
	}
	public void setWinner(boolean winner) {
		this.winner = winner;
	}



}
