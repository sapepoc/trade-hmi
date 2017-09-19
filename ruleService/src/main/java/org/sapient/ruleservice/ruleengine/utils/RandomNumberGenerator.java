package org.sapient.ruleservice.ruleengine.utils;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.springframework.stereotype.Component;

@Component
public class RandomNumberGenerator {

	private Random random;

	public RandomNumberGenerator() {
		random = new Random();
	}

	public Set<Integer> generateNumbers(int size, int lowerLimit,
			int uppperLimit) {
		final Set<Integer> randomNumbers = new HashSet<>();
		for (int i = 0; i < size; i++) {
			randomNumbers.add(random.nextInt(uppperLimit - lowerLimit)
					+ lowerLimit);
		}
		return randomNumbers;
	}
}
