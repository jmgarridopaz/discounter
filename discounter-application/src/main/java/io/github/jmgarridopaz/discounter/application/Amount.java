package io.github.jmgarridopaz.discounter.application;

import java.math.BigDecimal;
import java.math.RoundingMode;


public class Amount {

    private final BigDecimal value;

    private Amount ( BigDecimal value ) {
        if ( value==null ) {
            throw new NullPointerException("Value cannot be null");
        }
        if ( value.compareTo(BigDecimal.ZERO) < 0 ) {
            throw new IllegalArgumentException("Value ("+value+") cannot be less than zero");
        }
        if ( value.scale() > 2 ) {
            throw new IllegalArgumentException("The number of decimal places ("+value.scale()+") cannot be greater than 2 digits");
        }
        this.value = value.setScale(2, RoundingMode.HALF_UP);
    }

    public static Amount parse ( String characters ) {
        if ( characters==null || characters.trim().isEmpty() ) {
            throw new IllegalArgumentException("Characters must be provided");
        }
        BigDecimal value;
        try {
            value = new BigDecimal(characters);
        } catch ( NumberFormatException nfe ) {
            throw new IllegalArgumentException("Characters ("+characters+") don't match a valid number format",nfe);
        }
        return new Amount(value);
    }

    public BigDecimal getValue() {
        return this.value;
    }

    public boolean isEqualTo ( Amount amount ) {
        if ( amount==null ) {
            throw new NullPointerException("Amount cannot be null");
        }
        return ( this.value.compareTo(amount.value) == 0 );
    }

    public boolean isGreaterThan ( Amount amount ) {
        if ( amount==null ) {
            throw new NullPointerException("Amount cannot be null");
        }
        return ( this.value.compareTo(amount.value) > 0 );
    }

    public boolean isGreaterThanOrEqualTo ( Amount amount ) {
        if ( amount==null ) {
            throw new NullPointerException("Amount cannot be null");
        }
        return ( this.value.compareTo(amount.value) >= 0 );
    }

    public Amount multiply ( Rate rate ) {
        if ( rate==null ) {
            throw new NullPointerException("Rate cannot be null");
        }
        BigDecimal resultValue = this.value.multiply ( rate.getValue() )
                                .setScale(2, RoundingMode.HALF_UP);
        return new Amount(resultValue);
    }

    @Override
    public boolean equals(Object other) {
        if ( other==null ) {
            return false;
        }
        if ( other==this ) {
            return true;
        }
        if ( !(other instanceof Amount) ) {
            return false;
        }
        Amount otherAmount = (Amount) other;
        return this.isEqualTo(otherAmount);
    }

    @Override
    public int hashCode() {
        return this.value.hashCode();
    }

    @Override
    public String toString() {
        return this.value.toString();
    }

}
