package io.github.jmgarridopaz.discounter.outside.forobtainingrates.testdouble;

import io.github.jmgarridopaz.discounter.application.ForObtainingRates;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class StubRateRepository implements ForObtainingRates {

    private List<String[]> ratesByAmountIntervals;

    public StubRateRepository() {
        System.out.println("StubRateRepository created...");
        this.ratesByAmountIntervals = new ArrayList<String[]>();
    }

    @Override
    public BigDecimal getRateOfAmount(BigDecimal amount ) {
        if ( amount==null || amount.compareTo(BigDecimal.ZERO)<=0 ) {
            throw new IllegalArgumentException("Amount must be greater than zero");
        }
        for ( String[] rateByAmountInterval : this.ratesByAmountIntervals ) {
            String minAmountAsString = rateByAmountInterval[0];
            String maxAmountAsString = rateByAmountInterval[1];
            BigDecimal rate = new BigDecimal(rateByAmountInterval[2]);
            if (amountBelongsToInterval(amount, minAmountAsString, maxAmountAsString)) {
                return rate;
            }
        }
        return new BigDecimal("0.00");
    }

    private boolean amountBelongsToInterval(BigDecimal amount, String minAmountAsString, String maxAmountAsString) {
        if ( minAmountAsString!=null && !minAmountAsString.trim().isEmpty() ) {
            if ( amount.compareTo(new BigDecimal(minAmountAsString)) < 0 ) {
                return false;
            }
        }
        if ( maxAmountAsString!=null && !maxAmountAsString.trim().isEmpty() ) {
            if ( amount.compareTo(new BigDecimal(maxAmountAsString)) > 0 ) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void init(String[][] ratesByAmountIntervals) {
        this.ratesByAmountIntervals = new ArrayList<String[]>();
        if ( ratesByAmountIntervals!=null && ratesByAmountIntervals.length>0 ) {
            for ( String[] rateByAmountInterval : ratesByAmountIntervals ) {
                if ( rateByAmountInterval==null || rateByAmountInterval.length!=3 ) {
                    throw new IllegalArgumentException("Three values expected: minAmount, maxAmount, rate");
                }
                String minAmount = rateByAmountInterval[0];
                String maxAmount = rateByAmountInterval[1];
                String rate = rateByAmountInterval[2];
                if ( rate==null || rate.trim().isEmpty() ) {
                    throw new IllegalArgumentException("Rate must be provided");
                }
                String[] amountIntervalWithRate = { minAmount, maxAmount, rate };
                this.ratesByAmountIntervals.add ( amountIntervalWithRate );
            }
        }
     }

}
