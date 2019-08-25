import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class TradeReader {

	ArrayList<Trade> blotter;
	
	public TradeReader(String filename){
		File file = new File(filename);
		blotter = new ArrayList<Trade>();
		
		try {
			Scanner scanner = new Scanner(file);
			scanner.nextLine();
			
			TDate date;
			String direction;
			String ticker;
			int size;
			double price;
			double comission;
			double proceeds;
			
			
			while (scanner.hasNextLine()) {
				String tradeRow = scanner.nextLine();
				String[] columnData = tradeRow.split(",");
				
				if((columnData[2].substring(0, 1)).equals("9")) {
				} else {
					
				
				date = new TDate((columnData[0]));
				direction = columnData[1];
				ticker = columnData[2];
				size = Integer.parseInt(columnData[5]);
				price = Double.parseDouble(columnData[6]);
				comission = Double.parseDouble(columnData[7]);
				proceeds = Double.parseDouble((columnData[8]).substring(0, columnData[8].length()-1));
				
				Trade trade = new Trade(date, direction, ticker, size, price, comission, proceeds);
				blotter.add(trade);
				
				
				
				
				}
			
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
	
	
}
