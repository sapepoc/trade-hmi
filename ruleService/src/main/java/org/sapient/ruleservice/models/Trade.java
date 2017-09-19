package org.sapient.ruleservice.models;

import java.util.Date;

public class Trade {
	
	private String id;
	private String instrument;
	private String party1;
	private String party2;
	private Date executionDate;
	private String direction;
	private double rate;
	private double volume;
	
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
	public Date getExecutionDate() {
		return executionDate;
	}



	/**
	 * @param executionDate the executionDate to set
	 */
	public void setExecutionDate(Date executionDate) {
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
}
