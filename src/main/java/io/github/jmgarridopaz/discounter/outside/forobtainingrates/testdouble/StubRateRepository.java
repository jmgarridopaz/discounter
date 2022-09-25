package io.github.jmgarridopaz.discounter.outside.forobtainingrates.testdouble;

import io.github.jmgarridopaz.discounter.application.Amount;
import io.github.jmgarridopaz.discounter.application.ForObtainingRates;
import io.github.jmgarridopaz.discounter.application.Rate;
import java.util.ArrayList;
import java.util.List;


public class StubRateRepository implements ForObtainingRates {

    private List<RateOfAmountInterval> ratesByAmountIntervals;

    public StubRateRepository() {
        System.out.println("StubRateRepository created...");
        initEmpty();
    }

    @Override
    public Rate getRateOfAmount(Amount amount) {
        if ( amount==null ) {
            throw new NullPointerException("Amount cannot be null");
        }
        for ( RateOfAmountInterval rateOfAmountInterval : this.ratesByAmountIntervals ) {
            if ( rateOfAmountInterval.includes(amount) ) {
                return rateOfAmountInterval.getRate();
            }
        }
        return Rate.parse("0.00");
    }

    @Override
    public void initEmpty() {
        this.ratesByAmountIntervals = new ArrayList<RateOfAmountInterval>();
    }

    @Override
    public void addRateOfAmountInterval(Amount minAmount, Amount maxAmount, Rate rate) {
        RateOfAmountInterval newRateOfAmountInterval = new RateOfAmountInterval(minAmount,maxAmount,rate);
        for ( RateOfAmountInterval rateOfAmountInterval : this.ratesByAmountIntervals ) {
            if ( newRateOfAmountInterval.overlapsWith(rateOfAmountInterval) ) {
                throw new RuntimeException("Amount interval overlapping when adding rate to repository");
            }
        }
        this.ratesByAmountIntervals.add(newRateOfAmountInterval);
    }

}
