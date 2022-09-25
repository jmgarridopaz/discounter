package io.github.jmgarridopaz.discounter.application;

/**
 * Driver Port
 */
public interface ForDiscounting {

    public Amount calculateDiscount ( Amount amount );

    public void initializeRates ( String[][] ratesByAmountInterval );

}
