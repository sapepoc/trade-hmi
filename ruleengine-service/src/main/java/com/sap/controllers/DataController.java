package com.sap.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.support.ErrorMessage;
import org.springframework.stereotype.Component;

import com.sap.data.Trade;
import com.sap.data.TradeData;
import com.sap.exception.SapException;
import com.sap.service.ITradeService;

@Component
@Path("/search")
public class DataController {

	@Autowired
	@Qualifier("tradeService")
	private ITradeService tradeService;

	@Path("/applyRule/{date}")
	@GET
	@Produces("application/json")
	public Response getAllTradesOfDate(@PathParam("date") final String date) {
		List<TradeData> appliedRuleList = new ArrayList<TradeData>();
		try {
			appliedRuleList = tradeService.applyRulesOnDateTrades(date);
		} catch (SapException ex) {
			return Response.status(400)
					.entity(new ErrorMessage(ex))
					.type(MediaType.APPLICATION_JSON).build();
		}
		return Response.ok(appliedRuleList).build();
	}
	
	/*@GET
	@Path("/applyRule")
	@Produces("application/json")
	public Response getAllTrades() {
		List<Trade> appliedRuleList = new ArrayList<Trade>();
		try {
			appliedRuleList = tradeService.fetchAllTradesForDate("");
		} catch (SapException ex) {
			return Response.status(400)
					.entity(new ErrorMessage(ex))
					.type(MediaType.APPLICATION_JSON).build();
		}
		return Response.ok(appliedRuleList).build();
	}*/

}



	
	
