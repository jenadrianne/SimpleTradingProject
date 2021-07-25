import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class StockPurchase {

	/**
	 * State Variables
	 */
	
	private int purchaseID; 
	private int stockID;
	private int traderId; 
	private int stockQty; 
	private double stockPrice; 
	private Date purchaseDate;
	
	public StockPurchase(int stockID, int traderId, int stockQty, double stockPrice) {
		Random random = new Random();
        this.purchaseID = Math.abs(random.nextInt());
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
	public int getStockID() {
		return stockID;
	}
	public void setStockID(int stockID) {
		this.stockID = stockID;
	}
	public int getTraderId() {
		return traderId;
	}
	public void setTraderId(int traderId) {
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
