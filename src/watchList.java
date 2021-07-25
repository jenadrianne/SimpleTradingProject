import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class watchList {
	/*
	 * State
	 */
	private double watchlistID; 
	private double stockId; 
	private double customerId; 
	private Date dateAdded;
	
	/*
	 * Constructor
	 */
	public watchList() {
		
	}
	
	public watchList(double stockId, double customerId) {
		super();
		this.watchlistID = Math.random();
		this.stockId = stockId;
		this.customerId = customerId;
		
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
		Date date = new Date(System.currentTimeMillis());
		this.dateAdded = date;
	}

	public double getWatchlistID() {
		return watchlistID;
	}

	public void setWatchlistID(double watchlistID) {
		this.watchlistID = watchlistID;
	}

	public double getStockId() {
		return stockId;
	}

	public void setStockId(double stockId) {
		this.stockId = stockId;
	}

	public double getCustomerId() {
		return customerId;
	}

	public void setCustomerId(double customerId) {
		this.customerId = customerId;
	}

	public Date getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	} 
	
	/**
	 * Add a new watchlist 
	 * @param stockId
	 * @param customerId
	 * @return
	 */
	public watchList add(double stockId, double customerId) {
		return new watchList(stockId, customerId); 
	}

	/**
	 * Add a new watchlist 
	 * @param stockId
	 * @param customerId
	 * @return
	 */
	public List<watchList> delete(List<watchList> list, double id) {
		for(watchList item : list) {
			if(item.getWatchlistID() == id) {
				list.remove(item); 
				System.out.println("Item Removed Successfully"); 
				break;
			}
		}
		
		return list;
	}
	
	/**
	 * Edit information
	 * @param stockId
	 * @param customerId
	 */
	public void edit(double stockId, double customerId) {
		this.stockId = stockId;
		this.customerId = customerId;		
	}
	
	/**
	 * Get List of watchlist by tradersID
	 * @param list
	 * @param id
	 * @return
	 */
	public List<watchList> getTradersList(List<watchList> list, double id){
		List<watchList> newList = new ArrayList<watchList>();
		for(watchList item : list) {
			if(item.getCustomerId() == id) {
				newList.add(item);
			}
		}
		
		return newList;
	}
	
	/**
	 * Get List of watchlist by stockID
	 * @param list
	 * @param id
	 * @return
	 */
	public List<Double> getStockList(List<watchList> list, double id){
		List<Double> newList = new ArrayList<Double>();
		for(watchList item : list) {
			if(item.getStockId() == id) {
				newList.add(item.getCustomerId());
			}
		}
		
		return newList;
	}
	
	/**
	 * Notify Sell
	 * @param invoice
	 * @param stocks
	 * @param traders
	 * @param watchList
	 */
	public void notifySell(StockPurchase invoice, List<Stock> stocks , List<Trader> traders, List<watchList> watchList) {
		Trader seller = getTraderByID(traders, invoice.getTraderId()); 
		Stock stock = getStockByID(stocks, invoice.getStockID());
		Trader temp = new Trader(); 
		List<Double> list = this.getStockList(watchList, invoice.getStockID());

		if(list.size() >0) {
			System.out.println("Attention for the following people :  "); 
			for(double traderId : list) {
				temp = getTraderByID(traders, traderId);
				System.out.format("%s %s\n", temp.getFirstName() , temp.getLastName());
			}
		}
		
		System.out.format("\n\n %s %s sold %s stock at %s price!!", seller.getFirstName() , seller.getLastName(), stock.getStockName() , invoice.getStockPrice()); 
	}
	
	/**
	 * Notify Sell
	 * @param invoice
	 * @param stocks
	 * @param traders
	 * @param watchList
	 */
	public void notifyBuy(StockPurchase invoice, List<Stock> stocks , List<Trader> traders, List<watchList> watchList) {
		Trader buyer = getTraderByID(traders, invoice.getTraderId()); 
		Stock stock = getStockByID(stocks, invoice.getStockID());
		Trader temp = new Trader(); 
		List<Double> list = this.getStockList(watchList, invoice.getStockID());

		if(list.size() >0) {
			System.out.println("Attention for the following people :  "); 
			for(double traderId : list) {
				temp = getTraderByID(traders, traderId);
				System.out.format("%s %s\n", temp.getFirstName() , temp.getLastName());
			}
		}
		
		System.out.format("\n\n %s %s bought %s stock at %s price!!", buyer.getFirstName() , buyer.getLastName(), stock.getStockName() , invoice.getStockPrice()); 
	}
	
	
	/**
	 * Get Trader by ID
	 * @param tradersList
	 * @param id
	 * @return
	 */
	public Trader getTraderByID(List<Trader> tradersList, double id) {
		Trader data = null;
		for(Trader temp : tradersList) {
			if(temp.getTraderId() == id) {
				data= temp; 
				break;
			}
		}
		
		return data;
	}
	
	/**
	 * Get Trader by ID
	 * @param tradersList
	 * @param id
	 * @return
	 */
	public Stock getStockByID(List<Stock> stockList, double id) {
		Stock data = null;
		for(Stock temp : stockList) {
			if(temp.getStockID() == id) {
				data= temp; 
				break;
			}
		}
		
		return data;
	}
}
