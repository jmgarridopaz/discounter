package io.github.jmgarridopaz.discounter.application;

import io.github.jmgarridopaz.discounter.application.ports.driven.ForObtainingRates;
import io.github.jmgarridopaz.discounter.application.ports.driving.ForDiscounting;
import java.util.ListIterator;

/**
 * Business logic
 * - Implements driving ports
 * - Depends on driven ports
 */
public class DiscounterApp implements ForDiscounting {

	private static final Rate CONSTANT_RATE_FOR_ANY_AMOUNT = Rate.parse("0.08");

	private final ForObtainingRates rateRepository;

	public DiscounterApp ( ForObtainingRates rateRepository ) {
		this.rateRepository = rateRepository;
	}


	@Override
	public Amount calculateDiscount ( Amount amount ) {
		if (amount == null) {
			throw new NullPointerException("Amount cannot be null");
		}
		Rate rate = findRateOfAmount ( amount );
		return amount.multiply ( rate );
	}


	private Rate findRateOfAmount ( Amount amount ) {
		if ( this.rateRepository==null ) {
			return CONSTANT_RATE_FOR_ANY_AMOUNT;
		}
		ListIterator<BreakPointWithRate> breakPointWithRateIterator = this.rateRepository.getDescendingBreakPointIterator();
		while ( breakPointWithRateIterator.hasPrevious() ) {
			BreakPointWithRate breakPointWithRate = breakPointWithRateIterator.previous();
			if ( amount.isGreaterThanOrEqualTo ( breakPointWithRate.getBreakPoint()) ) {
				return breakPointWithRate.getRate();
			}
		}
		throw new RuntimeException("No rate found for the amount " + amount);
	}

}
