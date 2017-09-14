package com.sap.routing;

import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;


public class Route extends RouteBuilder {

/*	@Override
	public void configure() throws Exception {
		from("jms:inbound.queue").process(ex -> {
			ex.getOut().setHeader(ex.HTTP_METHOD, "POST");
			ex.getOut().setBody(ex.getIn().getBody());
		})
//		.loop()
//		.simple("{{outbound.loop.count}}")
		.log("body").to("{{jms:inbound.queue}}")
//		.endDoTry().doCatch(Exception.class).process(this::catchException)
		;
	}*/

	List reveivedTrades;
	
	public void configure() {/*
		from("activemq:queuee")
		.process(ex -> {
			 reveivedTrades = (List) ex.getIn().getBody();
			
			System.out.println("----------- Processing in rule engine is complete .");
		})
		.setHeader("reveivedTrades", simple("22-07-2017"))
//		.to("bean:tradeService?method=applyRulesOnDateTrades(${body})");
		.to("bean:testBean2?method=testMethod");
      */}
	
	
	
	private void catchException(Exchange e) {
		e.getOut().setBody("");
	}

}