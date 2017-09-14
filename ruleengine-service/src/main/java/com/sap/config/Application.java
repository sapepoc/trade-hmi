package com.sap.config;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.spring.SpringCamelContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class Application {

	public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

        ConfigurableApplicationContext appContext = new ClassPathXmlApplicationContext(
				"camel-context.xml");
        
        CamelContext context = null;
		try {
			context = SpringCamelContext.springCamelContext(appContext, false);
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		try {

			ProducerTemplate template = context.createProducerTemplate();
			try {
				context.start();
				/*for (int i = 0; i < 2; i++) {
					template.sendBody("activemq:queue", "____direct_text_message____");
				}*/

				Thread.sleep(2000);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	
	}finally {
		try {
//			context.stop();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	}
	
	
	
	/*{
		// If to create basic activemq component using java
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616?broker.persistent=false");
        context.addComponent("activemq", JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));
		context.addComponent("activemq", ActiveMQComponent.activeMQComponent("vm://localhost?broker.persistent=false"));
	}*/
	
	/*{
//		The following example shows how to send an exchange asynchronously to the direct:start endpoint. The asyncSend() method returns a java.util.concurrent.Future object, which is used to retrieve the invocation result at a later time.

		Exchange exchange = new DefaultExchange(context);
		exchange.getIn().setBody("testMethod");

		Future<Exchange> future = template.asyncSend("direct:start", exchange);

		// You can do other things
	}*/
	
	/*{
		Exchange exchange = context.getEndpoint("direct:start").createExchange();
		exchange.getIn().setBody("testMethod");

		Future<Exchange> future = template.asyncCallback("direct:start", exchange, new SynchronizationAdapter() {
		    @Override
		    public void onComplete(Exchange exchange) {
		        assertEquals("testMethod World", exchange.getIn().getBody());
		    }
		});
	}*/
	
//	@Bean
//	public JmsTransactionManager createJMSTransactionManager(final ConnectionFactory cf){
//		
//		JmsTransactionManager jmsTransactionManager = new JmsTransactionManager();
//		jmsTransactionManager.setConnectionFactory(cf);
//		
//		return jmsTransactionManager;
//	}
//	
//	@Bean
//	JmsComponent createJmsComp(ConnectionFactory c){
//		JmsComponent jmsComponent = JmsComponent.jmsComponentTransacted(c);
//		jmsComponent.setMaxConcurrentConsumers(2);
//		return jmsComponent;
//	}
//	
//		
//	@Bean
//	public SpringCamelContext camelContext(ApplicationContext applicationContext) throws Exception {
//		SpringCamelContext camelContext = new SpringCamelContext(applicationContext);
//		camelContext.addRoutes(routeBuilder());
//		return camelContext;
//	}
//	
//	@Bean
//	public RouteBuilder routeBuilder() {
//		return new Route();
//	}
//	
//	@Bean
//	public ConnectionFactory ConnectionFactory(@Value("${broker.url}") String url) {
//		ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
//		activeMQConnectionFactory.setBrokerURL(url);
//		return activeMQConnectionFactory;
//	}
//	
	
	
	
//	@Bean
//	public SpringCamelContext camelContext(ApplicationContext applicationContext) throws Exception {
//		SpringCamelContext camelContext = new SpringCamelContext(applicationContext);
//		camelContext.addRoutes(routeBuilder());
//		return camelContext;
//	}
//	
//	@Bean
//	public RouteBuilder routeBuilder() {
//		return new Route();
//	}
//	
//	@Bean
//	public ConnectionFactory ConnectionFactory(@Value("${broker.url}") String url) {
//		ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
//		activeMQConnectionFactory.setBrokerURL(url);
//		return activeMQConnectionFactory;
//	}
}