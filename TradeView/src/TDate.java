
public class TDate {

	int month;
	int year;
	int day;
	String date;
	
	public TDate(String tradeDate) {
		date = tradeDate.substring(1);
		String [] dateBreakdown = date.split("/");
		
		month = Integer.parseInt(dateBreakdown[0]);
		day = Integer.parseInt(dateBreakdown[1]);
		year = Integer.parseInt(dateBreakdown[2]);
		
	}
	
	
}
