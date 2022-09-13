package io.github.jmgarridopaz.discounter.application;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Driven Port
 */
public interface ForObtainingRates {

    public BigDecimal getRateOfAmount ( BigDecimal amount );

    public void init(String[][] ratesByAmountIntervals);

}
