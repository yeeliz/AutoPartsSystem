package dataBase;

public class Order {
	
	private String partName;
	private int price, providerId;
	
	public Order(int idProvider, String partsName, int price) {
		this.partName = partsName;
		this.price = price;
		providerId = idProvider;
	}
}
