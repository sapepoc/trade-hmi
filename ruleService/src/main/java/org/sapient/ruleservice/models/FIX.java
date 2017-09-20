package org.sapient.ruleservice.models;

import java.sql.Time;

public class FIX {

	private String currencyPair;
	private Integer thresold;
	private Time fixTime;
	private boolean isAllowed;

	public FIX(String currencyPair, Time fixTime, Integer thresold, 
			boolean isAllowed) {
		super();
		this.currencyPair = currencyPair;
		this.thresold = thresold;
		this.fixTime = fixTime;
		this.isAllowed = isAllowed;
	}

	public String getCurrencyPair() {
		return currencyPair;
	}

	public Time getFixTime() {
		return fixTime;
	}

	public boolean isAllowed() {
		return isAllowed;
	}

	public Integer getThresold() {
		return thresold;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FIX [currencyPair=").append(currencyPair)
				.append(", thresold=").append(thresold).append(", fixTime=")
				.append(fixTime).append(", isAllowed=").append(isAllowed)
				.append("]");
		return builder.toString();
	}
}
