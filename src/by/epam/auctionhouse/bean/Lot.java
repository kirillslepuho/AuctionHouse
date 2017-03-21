package by.epam.auctionhouse.bean;

public class Lot {
	private String id;
	private String type;
	private String name;
	private int currentPrice;
	private String descriprion;
	private String image;
	private boolean isClients;
	
	
	
	public Lot(String id, String type, String name, int currentPrice, String descriprion, String image,
			boolean isClients) {
		super();
		this.id = id;
		this.type = type;
		this.name = name;
		this.currentPrice = currentPrice;
		this.descriprion = descriprion;
		this.image = image;
		this.isClients = isClients;
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
		return isClients;
	}
	
	public void setClients(boolean isClients) {
		this.isClients = isClients;
	}
	
	





}
