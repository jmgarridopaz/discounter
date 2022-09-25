package io.github.jmgarridopaz.discounter.application;

import java.math.BigDecimal;

public class Rate {

    private final Amount amount;

    private Rate ( Amount amount ) {
        if ( amount==null ) {
            throw new NullPointerException("Amount must be provided");
        }
        if ( amount.value().compareTo(BigDecimal.ONE) > 0 ) {
            throw new IllegalArgumentException("The amount value cannot be greater than one");
        }
        this.amount = amount;
    }

    public static Rate parse ( String characters ) {
        return new Rate ( Amount.parse(characters) );
    }

    public BigDecimal value() {
        return this.amount.value();
    }

    @Override
    public String toString() {
        return this.amount.toString();
    }

}
