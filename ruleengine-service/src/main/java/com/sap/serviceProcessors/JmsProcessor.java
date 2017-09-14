package com.sap.serviceProcessors;

import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;

import com.sap.data.AlertResponse;
import com.sap.data.TradeData;
import com.sap.service.ITradeService;
import com.sap.serviceImpl.TradeServiceImpl;

@ComponentScan
public class JmsProcessor implements Processor {

	// rule-engine processes and saves

	@Autowired
	@Qualifier("tradeService")
	private ITradeService tradeService;

	@SuppressWarnings("unchecked")
	@Override
	public void process(Exchange exchange) throws Exception {
		try{
		List<TradeData> allTrades = (List<TradeData>) exchange.getIn().getBody();
		AlertResponse response = tradeService.applyRulesOnDateTradeList(allTrades);
		int savedApiId = saveApiResponse(allTrades);
		exchange.getIn().setBody(response.getTrades());

//		System.out.println("----------- Processing in rule engine is complete .${body}" + (List) (exchange.getIn().getBody()));
		System.out.println("____________done");
		}catch (Exception e) {
			throw new Exception("Error in JMS Processor");
		}
	}


	public ITradeService getTradeService() {
		return tradeService;
	}

	public void setTradeService(ITradeService tradeService) {
		this.tradeService = tradeService;
	}
	private ITradeService getTradesSerice() {
		return new TradeServiceImpl();
	}

	
	private int saveApiResponse(List<TradeData> allTrades) {
//		tradeService.saveAlertResponse(allTrades);
		return 0;
	}

}
