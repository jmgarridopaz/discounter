package io.github.jmgarridopaz.discounter.application;

import java.math.BigDecimal;


public class Rate {

    private final Amount amount;

    private Rate ( Amount amount ) {
        if ( amount==null ) {
            throw new NullPointerException("Amount cannot be null");
        }
        if ( amount.isGreaterThan(Amount.parse("1.00")) ) {
            throw new IllegalArgumentException("Amount cannot be greater than one");
        }
        this.amount = amount;
    }

    public static Rate parse ( String characters ) {
        return new Rate ( Amount.parse(characters) );
    }

    public BigDecimal getValue() {
        return this.amount.getValue();
    }

    @Override
    public boolean equals(Object other) {
        if ( other==null ) {
            return false;
        }
        if ( other==this ) {
            return true;
        }
        if ( !(other instanceof Rate) ) {
            return false;
        }
        Rate otherRate = (Rate) other;
        return this.amount.equals(otherRate.amount);
    }

    @Override
    public int hashCode() {
        return this.amount.hashCode();
    }

    @Override
    public String toString() {
        return this.amount.toString();
    }

}
