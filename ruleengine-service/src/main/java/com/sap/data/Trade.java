package com.sap.data;

import java.io.Serializable;
import java.util.Date;

//import javax.xml.bind.annotation.XmlAccessType;
//import javax.xml.bind.annotation.XmlAccessorType;

//@XmlAccessorType(XmlAccessType.FIELD)
public class Trade implements Serializable {

	private String tradeId;
	private float price;
	private int volume;
	private String direction;
	private String custId;
	private String instrument;
	private Date executionDate;

	public Trade(String tradeId, float price, int volume, String direction,
			String custId, String instrument, Date executionDate) {
		super();
		this.tradeId = tradeId;
		this.price = price;
		this.volume = volume;
		this.direction = direction;
		this.custId = custId;
		this.instrument = instrument;
		this.setExecutionDate(executionDate);
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getInstrument() {
		return instrument;
	}

	public void setInstrument(String instrument) {
		this.instrument = instrument;
	}

	public String getTradeId() {
		return tradeId;
	}

	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}

	public Date getExecutionDate() {
		return executionDate;
	}

	public void setExecutionDate(Date executionDate) {
		this.executionDate = executionDate;
	}

}
