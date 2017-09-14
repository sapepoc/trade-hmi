package com.sap.data;

import java.io.Serializable;
import java.util.List;

public class AlertResponse implements Serializable {

	private String message;
	private List<TradeData> trades;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<TradeData> getTrades() {
		return trades;
	}

	public void setTrades(List<TradeData> trades) {
		this.trades = trades;
	}

	public AlertResponse(String string, List<TradeData> trades) {
		this.message=string;
		this.trades=trades;
	}

	
}
