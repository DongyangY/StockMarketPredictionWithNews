import java.net.*;
import java.io.*;

public class YFCollector {
    public void showStocks(String[] symbols, String start, String end) {
	if (symbols == null || symbols.length == 0) return;

	String baseURL = "https://query.yahooapis.com/v1/public/yql?";

	StringBuilder sb = new StringBuilder();
	for (String s : symbols) {
	    sb.append("'" + s + "',");
	}
	sb.deleteCharAt(sb.length() - 1);
	
	String q = "select * from yahoo.finance.historicaldata where symbol in (" + sb.toString() + ") ";
	q += "and startDate = '" + start + "' and endDate = '" + end + "'";
	String env = "store://datatables.org/alltableswithkeys";
	    
	String url = null;
	try {
	    url = baseURL + "q=" + URLEncoder.encode(q, "UTF-8") + "&format=json&env=" + URLEncoder.encode(env, "UTF-8");
	} catch (Exception e) {
	    e.printStackTrace();
	}

	//System.out.println(url);

	if (url != null) {
	    try (BufferedReader br = new BufferedReader(new InputStreamReader(new URL(url).openStream()))) {
		String line = br.readLine();
		while (line != null) {
		    System.out.println(line);
		    line = br.readLine();
		}
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}
    }

    public static void main(String[] args) {
	YFCollector yfc = new YFCollector();
	String[] symbols = new String[] {"GOOG", "MSFT"};
	yfc.showStocks(symbols, "2016-08-01", "2016-08-10");
    }
}
