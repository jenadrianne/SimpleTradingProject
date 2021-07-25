import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class watchList {
	/*
	 * State
	 */
	private int watchlistID; 
	private int stockId; 
	private int customerId; 
	private Date dateAdded;
	
	/*
	 * Constructor
	 */
	public watchList() {
		
	}
	
	public watchList(int stockId, int customerId) {
		Random random = new Random();
        this.watchlistID = Math.abs(random.nextInt());
		this.stockId = stockId;
		this.customerId = customerId;
		
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
		Date date = new Date(System.currentTimeMillis());
		this.dateAdded = date;
	}

	public int getWatchlistID() {
		return watchlistID;
	}

	public void setWatchlistID(int watchlistID) {
		this.watchlistID = watchlistID;
	}

	public int getStockId() {
		return stockId;
	}

	public void setStockId(int stockId) {
		this.stockId = stockId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
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
	public watchList add(int stockId, int customerId) {
		return new watchList(stockId, customerId); 
	}

	/**
	 * Add a new watchlist 
	 * @param stockId
	 * @param customerId
	 * @return
	 */
	public List<watchList> delete(List<watchList> list, int id) {
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
	public void edit(int stockId, int customerId) {
		this.stockId = stockId;
		this.customerId = customerId;		
	}
	
	/**
	 * Get List of watchlist by tradersID
	 * @param list
	 * @param id
	 * @return
	 */
	public List<watchList> getTradersList(List<watchList> list, int id){
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
	public List<Integer> getStockList(List<watchList> list, int id){
		List<Integer> newList = new ArrayList<Integer>();
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
		List<Integer> list = this.getStockList(watchList, invoice.getStockID());

		if(list.size() >0) {
			System.out.println("Attention for the following people :  "); 
			for(int traderId : list) {
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
		List<Integer> list = this.getStockList(watchList, invoice.getStockID());

		if(list.size() >0) {
			System.out.println("Attention for the following people :  "); 
			for(int traderId : list) {
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
	public Trader getTraderByID(List<Trader> tradersList, int id) {
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
	public Stock getStockByID(List<Stock> stockList, int id) {
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
