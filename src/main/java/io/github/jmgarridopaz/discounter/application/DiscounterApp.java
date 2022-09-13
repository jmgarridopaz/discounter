package io.github.jmgarridopaz.discounter.application;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

/**
 * Business logic
 * - Implements driver ports
 * - Depends on driven ports
 */
public class DiscounterApp implements ForDiscounting {

	private final ForObtainingRates rateRepository;

	public DiscounterApp(ForObtainingRates rateRepository) {
		System.out.println("DiscounterApp created...");
		this.rateRepository = rateRepository;
	}

	@Override
	public BigDecimal calculateDiscount(BigDecimal amount) {
		BigDecimal roundedAmount = amount.setScale(2,RoundingMode.HALF_UP);
		BigDecimal rate = this.rateRepository.getRateOfAmount(roundedAmount);
		return roundedAmount.multiply(rate).setScale(2, RoundingMode.HALF_UP);
	}

	@Override
	public void initializeRates(String[][] ratesByAmountIntervals) {
		this.rateRepository.init(ratesByAmountIntervals);
	}

}
