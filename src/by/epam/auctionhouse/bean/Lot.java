package by.epam.auctionhouse.bean;

import java.io.Serializable;

public class Lot implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String type;
	private String name;
	private int startPrice;
	private int currentPrice;
	private String descriprion;
	private String image;
	private boolean clients;
	private String clientOwer;
	private String blitzBet;
	private String blitzPrice;
	
	
	public Lot() {
		super();
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getCurrentPrice() {
		return currentPrice;
	}
	
	public void setCurrentPrice(int currentPrice) {
		this.currentPrice = currentPrice;
	}
	
	public String getDescriprion() {
		return descriprion;
	}
	
	public void setDescriprion(String descriprion) {
		this.descriprion = descriprion;
	}
	
	public String getImage() {
		return image;
	}
	
	public void setImage(String image) {
		this.image = image;
	}
	
	public boolean isClients() {
		return clients;
	}
	
	public void setClients(boolean clients) {
		this.clients = clients;
	}

	public String getClientOwer() {
		return clientOwer;
	}

	public void setClientOwer(String clientOwer) {
		this.clientOwer = clientOwer;
	}

	public String getBlitzBet() {
		return blitzBet;
	}

	public void setBlitzBet(String blitzBet) {
		this.blitzBet = blitzBet;
	}

	public String getBlitzPrice() {
		return blitzPrice;
	}

	public void setBlitzPrice(String blitzPrice) {
		this.blitzPrice = blitzPrice;
	}

	public int getStartPrice() {
		return startPrice;
	}

	public void setStartPrice(int startPrice) {
		this.startPrice = startPrice;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((blitzBet == null) ? 0 : blitzBet.hashCode());
		result = prime * result + ((blitzPrice == null) ? 0 : blitzPrice.hashCode());
		result = prime * result + ((clientOwer == null) ? 0 : clientOwer.hashCode());
		result = prime * result + (clients ? 1231 : 1237);
		result = prime * result + currentPrice;
		result = prime * result + ((descriprion == null) ? 0 : descriprion.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((image == null) ? 0 : image.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + startPrice;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		Lot other = (Lot) obj;
		if (blitzBet == null) {
			if (other.blitzBet != null)
				return false;
		} else if (!blitzBet.equals(other.blitzBet))
			return false;
		if (blitzPrice == null) {
			if (other.blitzPrice != null)
				return false;
		} else if (!blitzPrice.equals(other.blitzPrice))
			return false;
		if (clientOwer == null) {
			if (other.clientOwer != null)
				return false;
		} else if (!clientOwer.equals(other.clientOwer))
			return false;
		if (clients != other.clients)
			return false;
		if (currentPrice != other.currentPrice)
			return false;
		if (descriprion == null) {
			if (other.descriprion != null)
				return false;
		} else if (!descriprion.equals(other.descriprion))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (image == null) {
			if (other.image != null)
				return false;
		} else if (!image.equals(other.image))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (startPrice != other.startPrice)
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	
	
	

}
