package io.github.jmgarridopaz.discounter.application;

import java.math.BigDecimal;
import java.math.RoundingMode;

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
	public Amount calculateDiscount(Amount amount) {
		if ( amount==null ) {
			throw new NullPointerException("Amount must be provided");
		}
		Rate rate = this.rateRepository.getRateOfAmount(amount);
		BigDecimal discount = amount.value().multiply(rate.value()).setScale(2, RoundingMode.HALF_UP);
		return Amount.ofValue(discount);
	}

	@Override
	public void initializeRates(String[][] ratesByAmountInterval) {
		this.rateRepository.initEmpty();
		if ( ratesByAmountInterval==null || ratesByAmountInterval.length==0 ) {
			return;
		}
		for (String[] rateByAmountInterval : ratesByAmountInterval) {
			if (rateByAmountInterval == null || rateByAmountInterval.length != 3) {
				throw new IllegalArgumentException("Three values expected: minAmount, maxAmount, rate");
			}
			Amount minAmount = Amount.parse(rateByAmountInterval[0]);
			Amount maxAmount = Amount.parse(rateByAmountInterval[1]);
			Rate rate = Rate.parse(rateByAmountInterval[2]);
			this.rateRepository.addRateOfAmountInterval(minAmount,maxAmount, rate);
		}
	}

}
