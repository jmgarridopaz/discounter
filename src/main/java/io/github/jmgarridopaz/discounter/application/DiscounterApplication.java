package io.github.jmgarridopaz.discounter.application;

/**
 * Business logic
 * - Implements driver ports
 * - Depends on driven ports
 */
public class DiscounterApplication implements DiscounterApi {

	private final ForObtainingRates rateProvider;

	public DiscounterApplication  ( ForObtainingRates rateProvider ) {
		this.rateProvider = rateProvider;
	}

	@Override
	public ForCalculatingDiscount discountCalculator() {
		return null;
	}

	@Override
	public ForConfiguringDiscounter discounterConfigurator() {
		return null;
	}
}
