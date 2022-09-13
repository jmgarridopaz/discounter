package io.github.jmgarridopaz.discounter.application;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Driver Port
 */
public interface ForDiscounting {

    public BigDecimal calculateDiscount ( BigDecimal amount );

    public void initializeRates ( String[][] ratesByAmountIntervals );

}
