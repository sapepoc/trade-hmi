package com.sapient.hmi.dto;

import java.util.List;

public class RequestEntity {

	private List<String> selectedRules;
	private List<String> tradeIds;
	
	public RequestEntity() {
	}
	
	public RequestEntity(List<String> tradeIds, List<String> selectedRules) {
		this.tradeIds = tradeIds;
		this.selectedRules = selectedRules;
	}

	public List<String> getSelectedRules() {
		return selectedRules;
	}

	public List<String> getTradeIds() {
		return tradeIds;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RequestEntity [selectedRules=");
		builder.append(selectedRules);
		builder.append(", tradeIds=");
		builder.append(tradeIds);
		builder.append("]");
		return builder.toString();
	}

}
