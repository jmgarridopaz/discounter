package io.github.jmgarridopaz.discounter.application;

/**
 * Driven Port
 */
public interface ForObtainingRates {

    public Rate getRateOfAmount ( Amount amount );

    public void initEmpty();

    public void addRateOfAmountInterval ( Amount minAmount, Amount maxAmount, Rate rate );

}
