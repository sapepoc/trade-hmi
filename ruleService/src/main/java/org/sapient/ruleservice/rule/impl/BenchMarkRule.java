package org.sapient.ruleservice.rule.impl;

import java.sql.Time;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import java.util.Objects;

import org.sapient.ruleservice.dao.FixService;
import org.sapient.ruleservice.models.FIX;
import org.sapient.ruleservice.models.Trade;
import org.sapient.ruleservice.rule.AbstractRule;
import org.sapient.ruleservice.rule.RuleResult;
import org.sapient.ruleservice.rule.RuleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("benchMarkRule")
public class BenchMarkRule extends AbstractRule {

	public BenchMarkRule() {
		super("BenchMarkRule", RuleType.JAVA);
	}

	@Autowired
	private FixService fixService;

	@Override
	public List<RuleResult> applyRule(final List<Trade> trades) {

		List<RuleResult> ruleResults = new ArrayList<>();

		// fixService
		trades.forEach(trade -> {
			FIX fix = fixService.getFixData(trade.getInstrument());
			if (Objects.nonNull(fix)) {
				boolean checkIfTradeShouldBeCaught = Math
						.abs(ChronoUnit.SECONDS.between(convert(fix
								.getFixTime()), trade.getExecutionDate()
								.toLocalTime())) <= fix.getThresold();

				if (checkIfTradeShouldBeCaught && fix.isAllowed()) {
					ruleResults.add(new RuleResult("BenchMark",
							"Bench mark rule detected for "
									+ trade.getInstrument(), trade));
				}
			}

		});

		return ruleResults;
	}

	private LocalTime convert(Date date) {
		return ((Time) date).toLocalTime();
	}
}
