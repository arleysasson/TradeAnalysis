import java.util.ArrayList;

public class TradeAnalysis {
	TradeReader TR;
	
	
	public TradeAnalysis() {
		this.TR =  new TradeReader("trades4.csv");
		
	}
	
	
	public void allTrades() {
		
		for (Trade trade : TR.blotter) {
			System.out.print(trade.getDate().year + "/" + trade.getDate().month + "/" + trade.getDate().day + " ");
			System.out.print(trade.getProceeds());
			System.out.print(trade.getDirection());
			System.out.println(trade.getTicker());		
		}
		
	}// end of allTrades method
	
	/**
	 * Method to create arraylist of all distinct tickers
	 * @return
	 */
	public ArrayList<String> distinctTickers(){
		ArrayList<String> allTickers = new ArrayList<String>();
		for (Trade trade : TR.blotter) {
			if( allTickers.contains(trade.getTicker())) {
				
			} else {
				allTickers.add(trade.getTicker());
			}
		} // end of for loop
		
		//System.out.println(allTickers);
		return allTickers;
		
	} //end of alltickers
	
	/**
	 * method to calculate current position for any given ticker
	 * @param ticker
	 * @return
	 */
	public int positionCalc(String ticker) {
		int currentPosition =0;
		for (Trade trade : TR.blotter) {
			if(trade.getTicker().equals(ticker)) {
				if(trade.getDirection().equals("Bought")) {
					currentPosition = currentPosition + trade.getSize();
				} else {
					currentPosition = currentPosition - trade.getSize();
				}
				
			}
			
		}
		//System.out.println("Position in " + ticker + " is " + currentPosition + " shares");
		return currentPosition;
	}
	
	/**
	 * method to calculate pnl in closed trades in one ticker
	 * @param ticker
	 * @return
	 */
	public double pnlClosed(String ticker) {
		
		double tickerPnl = 0.00;
		if(positionCalc(ticker) ==0) {
			for (Trade trade : TR.blotter) {
				if(trade.getTicker().equals(ticker)) {
					if(trade.getDirection().equals("Bought")) {
						tickerPnl = tickerPnl - trade.getProceeds();
					} else {
						tickerPnl = tickerPnl + trade.getProceeds();
					}
				}
		}
		
		
		}
		tickerPnl = Math.round(tickerPnl * 100);
		tickerPnl = tickerPnl/100;
		
		//System.out.println("PNL in " + ticker + " is " + tickerPnl);
		return tickerPnl;
	}
	
	/**
	 * Calculates pnl on all closed trades
	 * @param allTickers
	 * @return
	 */
	public double pnlTotalClosed(ArrayList<String> allTickers) {
		double totalClosedPnl =0;
		System.out.println("TICKER \t PNL" );
		System.out.println("====== \t =========" );
		for (String ticker : allTickers) {
			double pnl = pnlClosed(ticker);
			totalClosedPnl += pnl;
			if(pnl != 0) {
				
				System.out.printf("%s \t %.2f \n", ticker, pnl);
			}
			
			
		}
		
		totalClosedPnl = Math.round(totalClosedPnl * 100);
		totalClosedPnl = totalClosedPnl/100;
		System.out.println();
		System.out.printf("The total pnl in closed trades is : %.2f \n", totalClosedPnl);
		System.out.printf("This is inclusive of comissions paid of : %.2f \n", comissionPaid());
		System.out.println();
		return totalClosedPnl;
		
	}
	
	/**
	 * Calculates pnl on all closed trades
	 * @param allTickers
	 * @return
	 */
	public double sortedPnlTotalClosed(ArrayList<String> allTickers) {
		double totalClosedPnl =0;
		ArrayList<String> sortedTickers = new ArrayList<String>();
		ArrayList<Double> unsortedPnl = new ArrayList<Double>();
		ArrayList<Double> sortedPnl = new ArrayList<Double>();
		for (String ticker : allTickers) {
			double pnl = pnlClosed(ticker);
			totalClosedPnl += pnl;
			if(pnl != 0) {
				unsortedPnl.add(pnl);
				//System.out.println("PNL in " + ticker + " is " + pnl);
			}
			
			
		}
		
		for(int x = 0; x < unsortedPnl.size(); x++) {
			double largest = unsortedPnl.get(0);
			int index = 0;
			for (int i = 1; i < unsortedPnl.size(); i++) { //loop that will add the largest remaining number to list
				if ( unsortedPnl.get(i) > largest ) {
					largest = unsortedPnl.get(i);
					index = i;
		      } 
		}
			
			sortedPnl.add(unsortedPnl.get(index));
			sortedTickers.add(allTickers.get(index)); // uses index from above to add to sorted list of strings
			unsortedPnl.set(index, (double) -1000000); //  // changes current largest to -1 to exclude from further calculation
		}			
			
			
		for(int i = 0; i < sortedTickers.size(); i++) {
			System.out.println("PNL in " + sortedTickers.get(i) + " is " + sortedPnl.get(i));
		}
		
		totalClosedPnl = Math.round(totalClosedPnl * 100);
		totalClosedPnl = totalClosedPnl/100;
		System.out.println();
		System.out.println("The total ytd pnl in closed trades is : " + totalClosedPnl);
		System.out.println("This is inclusive of comissions paid of :" + comissionPaid());
		System.out.println();
		return totalClosedPnl;
		
	}
	
	/*
	public ArrayList<String> wordSorter(ArrayList<String> unsortedStrings, ArrayList<Double> unsortedNumbers){
		ArrayList<Double> sortedNumbers = new ArrayList<Double>();
		ArrayList<String> sortedStrings = new ArrayList<String>();
		for (int x = 0; x<unsortedNumbers.size(); x++) {
			double largest = unsortedNumbers.get(0);
			int index = 0;
			for (int i = 1; i < unsortedNumbers.size(); i++) { //loop that will add the largest remaining number to list
				if ( unsortedNumbers.get(i) > largest ) {
					largest = unsortedNumbers.get(i);
					index = i;
		      } 
		}
			
			sortedNumbers.add(unsortedNumbers.get(index));
			sortedStrings.add(unsortedStrings.get(index)); // uses index from above to add to sorted list of strings
			unsortedNumbers.set(index, (double) -1); //  // changes current largest to -1 to exclude from further calculation
		}
		
		return sortedStrings;
		
	} // end of wordSorter method */
	
	
	public void openPositions(ArrayList<String> tickers) {
		System.out.println("The following are your current open positions: ");
		for (String ticker : tickers) {
			int position = positionCalc(ticker);
			if(position != 0) {
				System.out.printf("%s \t %d shares \n", ticker, position);
			}
			
		}
	}
	
	public double comissionPaid() {
		double compaid = 0.00;
		for (Trade trade : TR.blotter) {
			double comission = trade.getComission();
			compaid += comission;
		}
		
		compaid = Math.round(compaid * 100);
		compaid = compaid/100;
		return compaid;
	}
	
	
	

	
	public static void main(String[] args) {
		
		TradeAnalysis TA = new TradeAnalysis();
		//TA.allTrades();
		//TA.distinctTickers();
		//TA.positionCalc("ROKU");
		//TA.pnlClosed("TVIX");
		//TA.positionCalc("LULU");
		
		TA.pnlTotalClosed(TA.distinctTickers());
		TA.openPositions(TA.distinctTickers());
		
		
		
		//TDate testDate = new TDate("05/09/2001");
		//System.out.println(testDate.day);
		//System.out.println(testDate.month);
		//System.out.println(testDate.year);
		
		
		
	}
	
}
