package org.sapient.ruleservice.ruleengine;

import java.util.List;
import java.util.Set;

import org.kie.api.event.rule.AfterMatchFiredEvent;
import org.kie.api.event.rule.AgendaEventListener;
import org.kie.api.event.rule.AgendaGroupPoppedEvent;
import org.kie.api.event.rule.AgendaGroupPushedEvent;
import org.kie.api.event.rule.BeforeMatchFiredEvent;
import org.kie.api.event.rule.MatchCancelledEvent;
import org.kie.api.event.rule.MatchCreatedEvent;
import org.kie.api.event.rule.ObjectDeletedEvent;
import org.kie.api.event.rule.ObjectInsertedEvent;
import org.kie.api.event.rule.ObjectUpdatedEvent;
import org.kie.api.event.rule.RuleFlowGroupActivatedEvent;
import org.kie.api.event.rule.RuleFlowGroupDeactivatedEvent;
import org.kie.api.event.rule.RuleRuntimeEventListener;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.AgendaFilter;
import org.kie.api.runtime.rule.Match;
import org.sapient.ruleservice.models.Trade;
import org.sapient.ruleservice.ruleengine.utils.KieContainerProvider;
import org.sapient.ruleservice.ruleengine.utils.KieSessionHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractRuleApplier implements RuleApplier
{
	private static Logger LOGGER = LoggerFactory.getLogger(AbstractRuleApplier.class);
	private String sessionName;
	private Set<String> washTradeRules;
	
	public AbstractRuleApplier(String sessionName, Set<String> washTradeRules)
	{
		this.washTradeRules = washTradeRules;
		this.sessionName = sessionName;
	}

	public void addRules(String... rules)
	{
		for (String rule : rules) 
		{
			washTradeRules.add(rule);
		}
	}
	
	public void removeRules(String... rules)
	{
		for (String rule : rules) 
		{
			washTradeRules.remove(rule);
		}
	}
	
	@Override
	public void applyRule(List<Trade> entities) 
	{
		KieSession kieSession = initSession();
		for(Trade entity: entities)
		{
			kieSession.insert(entity);
		}
		kieSession.fireAllRules(new RuleFilterAgenda(washTradeRules));
	}

	private KieSession initSession() 
	{
		KieContainer kieContainer = KieContainerProvider.createKieContainer();
		KieSession kieSession = KieSessionHelper.createSession(kieContainer, sessionName);
		kieSession.addEventListener(new TradeAgendaEventListener());
		kieSession.addEventListener(new TradeRuleRuntimeEventListener());
		addFactProcessor(kieSession);
		return kieSession;
	}
	
	protected abstract void addFactProcessor(KieSession kieSession);
	
	private class TradeRuleRuntimeEventListener implements RuleRuntimeEventListener
	{
		@Override
		public void objectUpdated(ObjectUpdatedEvent event)
		{
			LOGGER.info("Object was updated \n"
					+ "new Content \n" + event.getObject().toString());
		}
		
		@Override
		public void objectInserted(ObjectInsertedEvent event) 
		{
			LOGGER.info("Object inserted \n"
					+ event.getObject().toString());
		}
		
		@Override
		public void objectDeleted(ObjectDeletedEvent event)
		{
			LOGGER.info("Object retracted \n"
					+ event.getOldObject().toString());
		}
	}

	private class TradeAgendaEventListener implements AgendaEventListener
	{
		 public void matchCreated(MatchCreatedEvent event)
		 {
			 LOGGER.info("The rule '"
                     + event.getMatch().getRule().getName()
                     + "' can be fired in agenda");
         }
         public void matchCancelled(MatchCancelledEvent event) 
         {
        	 LOGGER.info("The rule '" + event.getMatch().getRule().getName() 
            		 + "' cannot b in agenda");
         }
         public void beforeMatchFired(BeforeMatchFiredEvent event) 
         {
        	 LOGGER.info("The rule '"
                     + event.getMatch().getRule().getName()
                     + "' will be fired");
         }
         public void afterMatchFired(AfterMatchFiredEvent event) 
         {
        	 LOGGER.info("The rule '"
                     + event.getMatch().getRule().getName()
                     + "' has be fired");
         }
         
		@Override
		public void afterRuleFlowGroupActivated(RuleFlowGroupActivatedEvent arg0) {
		}
		
		@Override
		public void afterRuleFlowGroupDeactivated(RuleFlowGroupDeactivatedEvent arg0) {
		}
		
		@Override
		public void agendaGroupPopped(AgendaGroupPoppedEvent arg0) {
		}
		
		@Override
		public void agendaGroupPushed(AgendaGroupPushedEvent arg0) {
		}
		
		@Override
		
		public void beforeRuleFlowGroupActivated(RuleFlowGroupActivatedEvent arg0) {
		}
		
		@Override
		public void beforeRuleFlowGroupDeactivated(RuleFlowGroupDeactivatedEvent arg0) {
		}
	}
	
	private class RuleFilterAgenda implements AgendaFilter
	{
		private Set<String> acceptedRules;
		public RuleFilterAgenda(Set<String> washTradeRules)
		{
			this.acceptedRules = washTradeRules;
		}
		
		@Override
		public boolean accept(Match match)
		{
			String ruleName = match.getRule().getName();
			LOGGER.info("Rule try to apply="+ ruleName);
			return acceptedRules.contains(ruleName) ? true : false;
		}
	}
}
