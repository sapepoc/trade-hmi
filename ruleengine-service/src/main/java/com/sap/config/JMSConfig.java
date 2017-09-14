package com.sap.config;

import javax.jms.ConnectionFactory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.connection.JmsTransactionManager;
import org.springframework.stereotype.Component;
import org.apache.camel.component.jms.JmsComponent;

@Component
public class JMSConfig {

	
	@Bean
	public JmsTransactionManager createJMSTransactionManager(final ConnectionFactory cf){
		
		JmsTransactionManager jmsTransactionManager = new JmsTransactionManager();
		jmsTransactionManager.setConnectionFactory(cf);
		
		return jmsTransactionManager;
	}
	
	@Bean
	JmsComponent createJmsComp(ConnectionFactory c){
		JmsComponent jmsComponent = JmsComponent.jmsComponentTransacted(c);
		jmsComponent.setMaxConcurrentConsumers(2);
		return jmsComponent;
	}
	
}
