import java.util.Date;

public class Trade {
	
	private TDate tDate;
	private String direction;
	private String ticker;
	private int size;
	private double price;
	private double comission;
	private double proceeds;
	
	
	
	public Trade(TDate tradeDate, String tradeDirection, String tradeTicker, int tradeSize, double tradePrice, double comissionPaid, double tradeProceeds) {
		tDate = tradeDate;
		direction = tradeDirection;
		ticker = tradeTicker;
		size = tradeSize;
		price = tradePrice;
		comission = comissionPaid;
		proceeds = tradeProceeds;
		
		
		
	}
	
	
	public String getDirection() {
		return direction;
	}


	public int getSize() {
		return size;
	}


	public double getPrice() {
		return price;
	}


	public double getComission() {
		return comission;
	}


	public double getProceeds() {
		return proceeds;
	}


	public String getTicker() {
		return ticker;
	}


	public TDate getDate() {
		return tDate;
	}


	public static void main(String[] args) {
		
		
		
	}
	
	
}
