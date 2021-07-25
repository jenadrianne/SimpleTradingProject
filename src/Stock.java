import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;


public class Stock {
	
	/*
	 * State Variables
	 */
	private double StockID; 
	private String StockName; 
	private double currPrice; 
	private double maxPrice; 
	private double minPrice; 
	private int stockQty;
	private Date lastModified; 

	/*
	 * Constructors
	 */
	public Stock() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Custom Constructor 
	 * @param stockName
	 * @param currPrice
	 * @param maxPrice
	 * @param minPrice
	 * @param stockQty
	 */
	public Stock(String stockName, double currPrice, double maxPrice, double minPrice, int stockQty) {
		this.StockName = stockName; 
		this.currPrice = currPrice; 
		this.maxPrice = maxPrice; 
		this.minPrice = minPrice; 
		this.stockQty = stockQty; 
		Random random = new Random();
        this.StockID = random.nextInt();
		
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
		Date date = new Date(System.currentTimeMillis());
		this.lastModified = date;
	}

	public double getStockID() {
		return StockID;
	}

	public void setStockID(double stockID) {
		StockID = stockID;
	}

	public String getStockName() {
		return StockName;
	}

	public void setStockName(String stockName) {
		StockName = stockName;
	}

	public double getCurrPrice() {
		return currPrice;
	}

	public void setCurrPrice(double currPrice) {
		this.currPrice = currPrice;
	}

	public double getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(double maxPrice) {
		this.maxPrice = maxPrice;
	}

	public double getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(double minPrice) {
		this.minPrice = minPrice;
	}

	public int getStockQty() {
		return stockQty;
	}

	public void setStockQty(int stockQty) {
		this.stockQty = stockQty;
	}

	public Date getLastModified() {
		return lastModified;
	}

	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}
	
	/**
	 * View Stock Information
	 */
	public void viewStock() {
		System.out.format("StockID : %f\n", this.StockID); 
		System.out.format("Stock Name: %s\n ", this.StockName); 
		System.out.format("Stock Current Selling price: $%.2f\n ", this.currPrice); 
		System.out.format("Stock Max Selling price: $%.2f \n", this.maxPrice); 
		System.out.format("Stock Min Selling price: $%.2f \n", this.minPrice); 
	}
	
	/**
	 * Update Stock Information
	 * @param stockName
	 * @param currPrice
	 * @param maxPrice
	 * @param minPrice
	 * @param stockQty
	 */
	public void updateStock(String stockName, double currPrice, double maxPrice, double minPrice, int stockQty) {
		this.StockName = stockName; 
		this.currPrice = currPrice; 
		this.maxPrice = maxPrice; 
		this.minPrice = minPrice; 
		this.stockQty = stockQty; 
		
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
		Date date = new Date(System.currentTimeMillis());
		this.lastModified = date;
	}
	

	/**
	 * Update Stock Information
	 * @param stockName
	 * @param currPrice
	 * @param maxPrice
	 * @param minPrice
	 * @param stockQty
	 */
	public List<Stock>  deleteStock(List<Stock> stockList, double id) {
		for(Stock temp : stockList) {
			if(temp.getStockID() == id) {
				stockList.remove(temp); 
				System.out.println("Info Deleted Successfully");
				break; 
			}
		}
		return stockList;
	}
	
	/**
	 * Add or Create new Stock Item
	 * @param stockName
	 * @param currPrice
	 * @param maxPrice
	 * @param minPrice
	 * @param stockQty
	 * @return
	 */
	public Stock addstock(String stockName, double currPrice, double maxPrice, double minPrice, int stockQty) {
		return new Stock( stockName,  currPrice,  maxPrice,  minPrice,  stockQty); 
	}
	
	
	/**
	 * Check the qty of available stock items
	 * @param stockId
	 * @param stockList
	 * @return
	 */
	public int checkAvailability(double stockId, List<Stock> stockList) {
		for(Stock temp : stockList) {
			if(temp.getStockID() == stockId) {
				return temp.stockQty;
			}
		}
		return 0;
	}
	
	/**
	 * Search Stock 
	 * @param stockList
	 * @param id
	 */
	public boolean searchStock(List<Stock> stockList, double id) {
		for(Stock temp : stockList) {
			if(temp.getStockID() == id) {
				return true;
			}
		}
		return false;
	}
}
