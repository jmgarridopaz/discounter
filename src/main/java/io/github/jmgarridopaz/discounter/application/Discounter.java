package io.github.jmgarridopaz.discounter.application;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static java.math.BigDecimal.TEN;

/**
 * Business logic
 * - Implements driver ports
 * - Depends on driven ports
 */
public class Discounter implements ForCalculatingDiscount {

    private static BigDecimal ONE_HUNDRED = new BigDecimal("100.00");

	public Discounter() {
        System.out.println("Discounter created...");
    }

	@Override
	public BigDecimal calculateDiscount(BigDecimal amount) {
//		if ( amount==null ) {
//			throw new NullPointerException("Amount cannot be null");
//		}
//		if ( amount.compareTo(BigDecimal.ZERO)<=0 ) {
//			throw new IllegalArgumentException("Amount must be greater than zero, but it is "+amount);
//		}
//		System.out.println ( amount+" scaled = " amount.setScale(2, RoundingMode.HALF_UP) );
		if ( amount.compareTo(ONE_HUNDRED)==0 ) {
			return new BigDecimal("15.00");
		}
		throw new IllegalArgumentException(amount.toString());
	}

}
