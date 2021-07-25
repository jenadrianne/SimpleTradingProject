import java.text.SimpleDateFormat;
import java.util.Date;

public class StockPurchase {

	/**
	 * State Variables
	 */
	
	private double purchaseID; 
	private double stockID;
	private double traderId; 
	private int stockQty; 
	private double stockPrice; 
	private Date purchaseDate;
	
	public StockPurchase(double stockID, double traderId, int stockQty, double stockPrice) {
		super();
		this.purchaseID = Math.random();
		this.stockID = stockID;
		this.traderId = traderId;
		this.stockQty = stockQty;
		this.stockPrice = stockPrice;
		
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
		Date date = new Date(System.currentTimeMillis());
		this.purchaseDate = date;
	}
	
	public double getPurchaseID() {
		return purchaseID;
	}
	public void setPurchaseID(int purchaseID) {
		this.purchaseID = purchaseID;
	}
	public double getStockID() {
		return stockID;
	}
	public void setStockID(double stockID) {
		this.stockID = stockID;
	}
	public double getTraderId() {
		return traderId;
	}
	public void setTraderId(double traderId) {
		this.traderId = traderId;
	}
	public int getStockQty() {
		return stockQty;
	}
	public void setStockQty(int stockQty) {
		this.stockQty = stockQty;
	}
	public double getStockPrice() {
		return stockPrice;
	}
	public void setStockPrice(double stockPrice) {
		this.stockPrice = stockPrice;
	}
	public Date getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	
}
