package by.epam.auctionhouse.bean;

import java.io.Serializable;

public class Bet implements Serializable{
	private static final long serialVersionUID = 1L;
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((auction == null) ? 0 : auction.hashCode());
		result = prime * result + ((bet == null) ? 0 : bet.hashCode());
		result = prime * result + ((client == null) ? 0 : client.hashCode());
		result = prime * result + (winner ? 1231 : 1237);
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bet other = (Bet) obj;
		if (auction == null) {
			if (other.auction != null)
				return false;
		} else if (!auction.equals(other.auction))
			return false;
		if (bet == null) {
			if (other.bet != null)
				return false;
		} else if (!bet.equals(other.bet))
			return false;
		if (client == null) {
			if (other.client != null)
				return false;
		} else if (!client.equals(other.client))
			return false;
		if (winner != other.winner)
			return false;
		return true;
	}



}
