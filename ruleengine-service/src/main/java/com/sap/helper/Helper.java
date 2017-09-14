package com.sap.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sap.data.AvailableDateFormats;
import com.sap.data.Market;
import com.sap.data.Trade;

public class Helper {
	
	private static final String format_YYYYMMDDHHMMss = AvailableDateFormats.YYYYMMDDHHMMss;
	private static final String format_YYYYMMDD = AvailableDateFormats.YYYYMMDD;

	
	public static String getDateString(Date date){
		String dateStr = new SimpleDateFormat(format_YYYYMMDDHHMMss).format(date);
		return dateStr;
	}
	
	public static Date getDateFromString(String dateStr) throws ParseException{
		dateStr = convertFormat(dateStr);
		Date d = new SimpleDateFormat(format_YYYYMMDDHHMMss).parse(dateStr);
		return d;
	}

	private static String convertFormat(String dateStr) {
		return dateStr =  dateStr.split(":").length == 1 ? dateStr +" 00:00:00": dateStr;
	}

	public static long getDifferenceInMins(Date d1, Date d2) {
		// TODO NULL checks
		long diff = 0;
		try {
			diff = d2.getTime() - d1.getTime();

			long diffMinutes = diff / (60 * 1000) % 60;
			diff = diffMinutes;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return diff;
	}
	
	public static boolean checkAvailableDateFormats(String date) {
		return true;
	}
	
	private final static Map<String, Market> dateStrMktMap = new HashMap<>();

	private static SimpleDateFormat sdf = new SimpleDateFormat(format_YYYYMMDDHHMMss);
	public static List<Trade> createAllTradedata() throws ParseException {


		List<Trade> list = new ArrayList<Trade>();
		Trade t1, t2, t3, t4, t5, t6, t7, t8;
		t1 = new Trade("A101", 10, 20000, "Buy", "X101", "USD",
				sdf.parse("2017-08-26 00:00:00"));
		t2 = new Trade("A102", 10, 20000, "Sell", "X102", "USD",
				sdf.parse("2017-08-26 00:00:00"));
		t3 = new Trade("A103", 10, 200, "Buy", "X103", "USD",
				sdf.parse("2017-08-26 00:00:00"));
		t4 = new Trade("A104", 10, 200, "Buy", "X104", "USD",
				sdf.parse("2017-08-26 00:00:00"));
		t5 = new Trade("A105", 10, 200, "Buy", "X105", "INR",
				sdf.parse("2017-08-27 00:00:00"));
		t6 = new Trade("A106", 10, 200, "Buy", "X106", "INR",
				sdf.parse("2017-08-27 00:00:00"));
		t7 = new Trade("A107", 10, 200, "Buy", "X107", "INR",
				sdf.parse("2017-08-27 00:00:00"));
		
		return Arrays.asList(t1, t2, t3, t4, t5, t6, t7);
	}

	public static Map<String, Market> createMarketData() throws ParseException{
		Trade t1, t2, t3, t4, t5, t6, t7, t8;
		t1 = new Trade("A101", 10, 200, "Buy", "X101", "USD",
				sdf.parse("2017-08-26 00:00:00"));
		t2 = new Trade("A102", 10, 200, "Buy", "X102", "USD",
				sdf.parse("2017-08-26 00:00:00"));
		t3 = new Trade("A103", 10, 200, "Buy", "X103", "USD",
				sdf.parse("2017-08-26 00:00:00"));
		t4 = new Trade("A104", 10, 200, "Buy", "X104", "USD",
				sdf.parse("2017-08-26 00:00:00"));
		t5 = new Trade("A105", 10, 200, "Buy", "X105", "INR",
				sdf.parse("2017-08-27 00:00:00"));
		t6 = new Trade("A106", 10, 200, "Buy", "X106", "INR",
				sdf.parse("2017-08-27 00:00:00"));
		t7 = new Trade("A107", 10, 200, "Buy", "X107", "INR",
				sdf.parse("2017-08-27 00:00:00"));
		List<Trade> firstMktLst = new ArrayList<>();
		Market mktEquity = new Market();
		mktEquity.setTradeList(firstMktLst);
		firstMktLst.add(t1);
		firstMktLst.add(t2);
		firstMktLst.add(t3);
		firstMktLst.add(t4);

		Market mktCommodity = new Market();
		List<Trade> secondMktLst = new ArrayList<>();
		secondMktLst.add(t5);
		secondMktLst.add(t6);
		secondMktLst.add(t7);

		dateStrMktMap.put("2017-08-26", mktEquity);// TODO static data
		dateStrMktMap.put("2017-08-27", mktCommodity);
		
		return dateStrMktMap;
	}
}
