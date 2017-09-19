package com.sample;

import static org.hamcrest.core.Is.is;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
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
import org.kie.api.runtime.rule.FactHandle;
import org.kie.api.runtime.rule.Match;
import org.sapient.ruleengin.utils.KieContainerProvider;

import com.sample.model.Purchase;

public class PurchaseTest 
{
	private KieSession kieSession;

	@Before
	public void setup()
	{
		KieContainer kieContainer = KieContainerProvider.createKieContainer();
		kieSession = kieContainer.newKieSession("ksession-rules-product");
		kieSession.addEventListener(new DummyAgendaEventListener());
		kieSession.addEventListener(new DummyRuleRuntimeEventListener());
	}
	
	@Test
	public void whenPurchaseAmountMoreThen10_applyRules_discoundShouldBePointOnePercent()
	{
		Purchase purchase = new Purchase(new BigDecimal(12));
		kieSession.insert(purchase);
		kieSession.fireAllRules();
		kieSession.dispose();
		Assert.assertThat(.1, is(purchase.getDiscount()));
	}
	
	@Test
	public void whenInsertThePurchaseThenChangeThePrice_applyRules_discoundShouldBeReconsidered()
	{
		Purchase purchase = new Purchase(new BigDecimal(12));
		FactHandle factHandle = kieSession.insert(purchase);
		
		
		Set<String> rules = new HashSet<>();
		rules.add("Purchase over 10 dollars");
		rules.add("Purchase over 20 dollars");
		
		kieSession.fireAllRules(new DummyAgendaFilter(rules));
		Assert.assertThat(.1, is(purchase.getDiscount()));
		
		purchase.setTotal(new BigDecimal(30));
		kieSession.update(factHandle, purchase);
		kieSession.fireAllRules(new DummyAgendaFilter(rules));
		Assert.assertThat(.2, is(purchase.getDiscount()));
		
		kieSession.dispose();
	}
	
	class DummyRuleRuntimeEventListener implements RuleRuntimeEventListener
	{
		@Override
		public void objectUpdated(ObjectUpdatedEvent event) {
			System.out.println("Object was updated \n"
					+ "new Content \n" + event.getObject().toString());
		}
		
		@Override
		public void objectInserted(ObjectInsertedEvent event) {
			System.out.println("Object inserted \n"
					+ event.getObject().toString());
		}
		
		@Override
		public void objectDeleted(ObjectDeletedEvent event) {
			System.out.println("Object retracted \n"
					+ event.getOldObject().toString());
		}
	}
	
	class DummyAgendaFilter implements AgendaFilter
	{
		private Set<String> rules;

		public DummyAgendaFilter(Set<String> rules)
		{
			this.rules = rules;
		}
		
		@Override
		public boolean accept(Match match)
		{	
			return rules.contains(match.getRule().getName()) ? true : false;
		}
		
	}
	
	class DummyAgendaEventListener implements AgendaEventListener
	{
		 public void matchCreated(MatchCreatedEvent event) {
             System.out.println("The rule "
                     + event.getMatch().getRule().getName()
                     + " can be fired in agenda");
         }
         public void matchCancelled(MatchCancelledEvent event) {
             System.out.println("The rule "
                     + event.getMatch().getRule().getName()
                     + " cannot b in agenda");
         }
         public void beforeMatchFired(BeforeMatchFiredEvent event) {
             System.out.println("The rule "
                     + event.getMatch().getRule().getName()
                     + " will be fired");
         }
         public void afterMatchFired(AfterMatchFiredEvent event) {
             System.out.println("The rule "
                     + event.getMatch().getRule().getName()
                     + " has be fired");
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
}
