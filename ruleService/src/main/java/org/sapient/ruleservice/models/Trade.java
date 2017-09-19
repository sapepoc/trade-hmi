package org.sapient.ruleservice.models;

import java.time.LocalDateTime;
import java.util.Date;

public class Trade {
	
	private String id;
	private String instrument;
	private String party1;
	private String party2;
	private LocalDateTime executionDate;
	private String direction;
	private double rate;
	private double volume;
	private boolean inHouse;
	private String productId;
	private Date settlementDate;
	
	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	
	public Date getSettlementDate() {
		return settlementDate;
	}

	public void setSettlementDate(Date settlementDate) {
		this.settlementDate = settlementDate;
	}

	public boolean isInHouse() {
		return inHouse;
	}

	public void setInHouse(boolean inHouse) {
		this.inHouse = inHouse;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}



	/**
	 * @return the instrument
	 */
	public String getInstrument() {
		return instrument;
	}



	/**
	 * @param instrument the instrument to set
	 */
	public void setInstrument(String instrument) {
		this.instrument = instrument;
	}



	/**
	 * @return the party1
	 */
	public String getParty1() {
		return party1;
	}



	/**
	 * @param party1 the party1 to set
	 */
	public void setParty1(String party1) {
		this.party1 = party1;
	}



	/**
	 * @return the party2
	 */
	public String getParty2() {
		return party2;
	}



	/**
	 * @param party2 the party2 to set
	 */
	public void setParty2(String party2) {
		this.party2 = party2;
	}



	/**
	 * @return the executionDate
	 */
	public LocalDateTime getExecutionDate() {
		return executionDate;
	}



	/**
	 * @param executionDate the executionDate to set
	 */
	public void setExecutionDate(LocalDateTime executionDate) {
		this.executionDate = executionDate;
	}



	/**
	 * @return the direction
	 */
	public String getDirection() {
		return direction;
	}



	/**
	 * @param direction the direction to set
	 */
	public void setDirection(String direction) {
		this.direction = direction;
	}



	/**
	 * @return the rate
	 */
	public double getRate() {
		return rate;
	}



	/**
	 * @param rate the rate to set
	 */
	public void setRate(double rate) {
		this.rate = rate;
	}



	/**
	 * @return the volume
	 */
	public double getVolume() {
		return volume;
	}



	/**
	 * @param volume the volume to set
	 */
	public void setVolume(double volume) {
		this.volume = volume;
	}



	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Trade [id=");
		builder.append(id);
		builder.append(", instrument=");
		builder.append(instrument);
		builder.append(", party1=");
		builder.append(party1);
		builder.append(", party2=");
		builder.append(party2);
		builder.append(", executionDate=");
		builder.append(executionDate);
		builder.append(", direction=");
		builder.append(direction);
		builder.append(", rate=");
		builder.append(rate);
		builder.append(", volume=");
		builder.append(volume);
		builder.append(", inHouse=");
		builder.append(inHouse);
		builder.append("]");
		return builder.toString();
	}
	
	
}
