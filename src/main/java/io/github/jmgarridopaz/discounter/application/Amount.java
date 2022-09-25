package io.github.jmgarridopaz.discounter.application;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Amount {

    private final BigDecimal value;

    private Amount ( BigDecimal value ) {
        if ( value==null ) {
            throw new NullPointerException("Value must be provided");
        }
        if ( value.compareTo(BigDecimal.ZERO)<0 ) {
            throw new IllegalArgumentException("Value cannot be a negative number");
        }
        if ( value.scale()>2 ) {
            throw new IllegalArgumentException("The number of decimal places cannot be greater than 2 digits");
        }
        this.value = value.setScale(2, RoundingMode.HALF_UP);
    }

    public static Amount parse ( String characters ) {
        if ( characters==null || characters.trim().isEmpty() ) {
            throw new NullPointerException("Characters must be provided");
        }
        BigDecimal value;
        try {
            value = new BigDecimal(characters);
        } catch ( NumberFormatException nfe ) {
            throw new IllegalArgumentException("Characters don't match any number format",nfe);
        }
        return new Amount(value);
    }

    public static Amount ofValue(BigDecimal number) {
        return new Amount(number);
    }

    public BigDecimal value() {
        return this.value;
    }


    @Override
    public String toString() {
        return this.value.toString();
    }

}
