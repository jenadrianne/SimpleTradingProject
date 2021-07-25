import java.util.List;
import java.util.Random;

public class Trader {

	/*
	 * State Variables
	 */
	private double TraderId; 
	private String lastName; 
	private String firstName;
	private String userName; 
	private String password; 
	
	/*
	 * Constructors
	 */
	public Trader() {
	}
	
	/**
	 * Custome constructor
	 * @param lastName
	 * @param firstName
	 * @param userName
	 * @param password
	 */
	public Trader(String lastName, String firstName, String userName, String  password) {
		this.lastName = lastName; 
		this.firstName = firstName; 
		this.userName = userName; 
		this.password = password;
		Random random = new Random();
        this.TraderId = random.nextInt();
		
	}

	public double getTraderId() {
		return TraderId;
	}

	public void setTraderId(double traderId) {
		TraderId = traderId;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * Display Traders Info
	 */
	public void displayTraderInfo() {
		System.out.format("TraderID : %f\n",  this.TraderId); 
		System.out.format("Name : %s %s\n",  this.firstName,  this.lastName); 
		System.out.format("username : %s\n",  this.userName); 
	}
	
	/**
	 * Edit Trader Info
	 * @param lastName
	 * @param firstName
	 * @param userName
	 * @param password
	 */
	public void editPortfolio(String lastName, String firstName, String userName, String  password) {
		this.lastName = lastName; 
		this.firstName = firstName; 
		this.userName = userName; 
		this.password = password;
		
		System.out.println("Info Updated Successfully!"); 
	}
	
	/**
	 * Delete Portfolio by ID
	 * @param tradersList
	 * @param trader
	 */
	public List<Trader> deletePortfolio(List<Trader> tradersList, double id) {
		for(Trader temp : tradersList) {
			if(temp.getTraderId() == id) {
				tradersList.remove(temp); 
				System.out.println("Info Deleted Successfully");
				break; 
			}
		}
		
		return tradersList;
	}
	
	
}
