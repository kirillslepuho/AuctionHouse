package by.epam.auctionhouse.bean;

public class Lot {
	private String id;
	private String type;
	private String name;
	private int currentPrice;
	private String descriprion;
	private String image;
	private boolean clients;
	private String clientOwer;
	private String blitzBet;
	private String blitzPrice;
	
	
	public Lot(String id, String type, String name, int currentPrice, String descriprion, String image,
			boolean clients,String clientOwer, String blitzBet, String blitzPrice) {
		super();
		this.id = id;
		this.type = type;
		this.name = name;
		this.currentPrice = currentPrice;
		this.descriprion = descriprion;
		this.image = image;
		this.clients = clients;
		this.clientOwer = clientOwer;
		this.blitzBet = blitzBet;
		this.blitzPrice = blitzPrice;
	}
	
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

	
	
	

}
