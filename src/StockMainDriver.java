import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class StockMainDriver {
	private static Scanner scan = new Scanner(System.in); 
	public static List<Trader> tradersList = new ArrayList<Trader>();
	public static List<Stock> stockList = new ArrayList<Stock>();
	public static List<watchList> watchlist = new ArrayList<watchList>();
	public static Map<Double , List<StockPurchase>> invoiceTable = new HashMap <Double, List<StockPurchase>>();
	
	public static void main(String[] args) {
		
		//populate Trader
		populateTrader(); 
		//displayAllTraderInfromation();
		
		//populate stock;
		populateStock();
		//displayAllStockInformation();
		
		//populate Watchlist
		populateWatchList();
		
		//populate invoice table; 
		
		int option = 0; 
		do {
			option = menu(); 
			switch(option) {
			case 1: 
				Buy();
				break;
			case 2: 
			case 3: 
				displayAllTraderInfromation();
				break;
			case 4: 
				displayAllStockInformation();
				break;
			default: 
					System.exit(0);
			}
			
		}while(option > 0 && option < 5);
		
		scan.close();
	}
	
	/**
	 * Menu Function
	 * @return
	 */
	private static int menu() {
		System.out.println("\n\n===============MENU==================");
		System.out.println("1- Buy Stock"); 
		System.out.println("2- Sell Stock"); 
		System.out.println("3- Display All Trader Information"); 
		System.out.println("4- Display All Stock Information"); 
		System.out.println("\n\nSelect an option : "); 
		return scan.nextInt();
	}
	
	/** 
	 * Populate or Create 5 concrete trader information 
	 */
	private static void populateTrader() {
		tradersList.add(new Trader("doe", "john", "doejohn", "12345"));
		tradersList.add(new Trader("doe", "jane", "doejane", "12345"));
		tradersList.add(new Trader("doe", "joanne", "doejoanne", "12345"));
		tradersList.add(new Trader("doe", "joseph", "doejoseph", "12345"));
		tradersList.add(new Trader("doe", "jerico", "doejerico", "12345"));
	}
	
	/** 
	 * Populate or Create 3 concrete stock information 
	 */
	private static void populateStock() {
		stockList.add(new Stock("jawwal", 100, 1000, 100, 100));
		stockList.add(new Stock("ooredoo",  180, 1500, 180, 200));
		stockList.add(new Stock("gas",  200, 2000, 200, 20));
	}
	
	/** 
	 * Populate WatchList
	 */
	private static void populateWatchList() {
		watchlist.add(new watchList(stockList.get(0).getStockID(), tradersList.get(0).getTraderId()));
		watchlist.add(new watchList(stockList.get(0).getStockID(), tradersList.get(2).getTraderId()));
		watchlist.add(new watchList(stockList.get(0).getStockID(), tradersList.get(3).getTraderId()));
		watchlist.add(new watchList(stockList.get(1).getStockID(), tradersList.get(0).getTraderId()));
		watchlist.add(new watchList(stockList.get(1).getStockID(), tradersList.get(1).getTraderId()));
		watchlist.add(new watchList(stockList.get(2).getStockID(), tradersList.get(4).getTraderId()));
	}
	
	/**
	 * Initialize invoice table
	 */
	private static void populateInvoiceTable() {
		for(Trader trader: tradersList) {
			invoiceTable.put(trader.getTraderId(), null);
		}
	}
	/**
	 * Display all TraderInformation
	 */
	public static void displayAllTraderInfromation() {
		System.out.println("\n\n***********************************************"); 
		System.out.println("Traders Information : "); 
		
		for(Trader trader: tradersList) {
			trader.displayTraderInfo();
			System.out.println("------\n");
		}
	}
	
	/**
	 * Display all StockInfromation
	 */
	public static void displayAllStockInformation() {
		System.out.println("\n\n***********************************************"); 
		System.out.println("Stock Information : "); 
		
		for(Stock stock: stockList) {
			stock.viewStock();
			System.out.println("------\n");
		}
	}
	
	/**
	 * Buy Stocks
	 */
	public static void Buy() {
		displayAllTraderInfromation(); 
		System.out.println("\n\n Buy for Trader. Please enter ID : ");
		Double traderID = Double.parseDouble(scan.next());
		
		displayAllStockInformation(); 
		System.out.println("\n\n Buy Stock . Please enter ID : ");
		Double stockID = Double.parseDouble(scan.next().trim());
		System.out.println("QTY : ");
		int stockQty = scan.nextInt(); 
		Stock data = getStockByID (stockID);
		
		if(data != null) {
			Double stockPrice = data.getCurrPrice() * stockQty;
			
			StockPurchase invoice = new StockPurchase(stockID, traderID, stockQty,stockPrice);
			addInvoice(invoice, traderID); 
			
			//notify changes
			watchList temp = new watchList(); 
			temp.notifyBuy(invoice, stockList, tradersList, watchlist);
		}
		
	}
	
	/**
	 *Add to invoice table
	 * @param invoice
	 * @param traderID
	 */
	public static void addInvoice(StockPurchase invoice, double traderID) {
		List<StockPurchase> purchaseList = invoiceTable.get(traderID);
		if(purchaseList ==null) {
			purchaseList = new ArrayList<StockPurchase>();
		}
		purchaseList.add(invoice);
	}
	
	/**
	 * Buy Stocks
	 */
	public static void Sell() {
		displayAllTraderInfromation(); 
		System.out.println("\n\n Sell for Trader. Please enter ID : ");
		Double traderID = Double.parseDouble(scan.next());
		
		//get purchases from trader 
		List<StockPurchase> purchaseList = invoiceTable.get(traderID);
		System.out.println("*********************************************"); 
		System.out.println("Stock Available for Selling"); 
		for(StockPurchase data : purchaseList) {
			Stock stock = getStockByID(data.getStockID());
			stock.viewStock();
		}
		System.out.println("*********************************************");
		System.out.println("Enter ID of stock to sell:");
		Double stockId = Double.parseDouble(scan.next());
		System.out.println("QTY: ");
		int qty = scan.nextInt();
		
		
		if(verifyStockQty(stockId, qty)) {
			System.out.println("*********************************************");
			System.out.println("Enter price: ");
			Double price = Double.parseDouble(scan.next());
			StockPurchase invoice = new StockPurchase(stockId, traderID, qty,price);
			addInvoice(invoice, traderID); 
			
			//notify changes
			watchList temp = new watchList(); 
			temp.notifySell(invoice, stockList, tradersList, watchlist);
		
		}
	}
	
	/**
	 * Get Stock by ID
	 * @param id
	 * @return
	 */
	public static Stock getStockByID(double id) {
		Stock data = null;
		for(Stock temp : stockList) {
			if(temp.getStockID() == id) {
				data= temp; 
				break;
			}
		}
		
		return data;
	}

	public static boolean verifyStockQty(double stockId, int qty) {
		boolean retval = false;
		Stock s = getStockByID(stockId);
		if(s.getStockQty() <= qty && qty > 0) {
			retval = true;
		}
		return retval;  
	}
}
