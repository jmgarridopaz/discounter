package io.github.jmgarridopaz.discounter.outside.forobtainingrates.testdouble;

import io.github.jmgarridopaz.discounter.application.Amount;
import io.github.jmgarridopaz.discounter.application.Rate;

import java.util.Objects;

public class RateOfAmountInterval {

    private final Amount minAmount;
    private final Amount maxAmount;
    private Rate rate;

    public RateOfAmountInterval(Amount minAmount, Amount maxAmount, Rate rate) {
        if ( minAmount==null || maxAmount==null || rate==null ) {
            throw new NullPointerException("minAmount, maxAmount and rate cannot be null");
        }
        if ( minAmount.value().compareTo(maxAmount.value()) > 0 ) {
            throw new IllegalArgumentException("Min amount cannot be greater than max amount");
        }
        this.minAmount = minAmount;
        this.maxAmount = maxAmount;
        this.rate = rate;
    }

    public Rate getRate() {
        return this.rate;
    }

    public boolean overlapsWith(RateOfAmountInterval other) {
        return ( !this.isBefore(other) && !this.isAfter(other) );
    }

    public boolean includes(Amount amount) {
        if ( amount==null ) {
            throw new NullPointerException("Amount cannot be null");
        }
        return  (
                    (amount.value().compareTo(this.minAmount.value())>=0)
                    &&
                    (amount.value().compareTo(this.maxAmount.value())<=0)
                );
    }

    private boolean isBefore(RateOfAmountInterval other) {
        if ( other==null ) {
            return false;
        }
        return ( this.maxAmount.value().compareTo(other.minAmount.value()) < 0 );
    }

    private boolean isAfter(RateOfAmountInterval other) {
        if ( other==null ) {
            return true;
        }
        return ( this.minAmount.value().compareTo(other.maxAmount.value()) > 0 );
    }

    @Override
    public boolean equals(Object other) {
        if ( other==null ) {
            return false;
        }
        if (this==other) {
            return true;
        }
        if ( ! (other instanceof RateOfAmountInterval) ) {
            return false;
        }
        RateOfAmountInterval that = (RateOfAmountInterval) other;
        return this.minAmount.equals(that.minAmount) && this.maxAmount.equals(that.maxAmount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.minAmount, this.maxAmount);
    }

}
