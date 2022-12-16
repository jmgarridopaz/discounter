package io.github.jmgarridopaz.discounter.outside.forobtainingrates.testdouble;

import io.github.jmgarridopaz.discounter.application.Amount;
import io.github.jmgarridopaz.discounter.application.BreakPointWithRate;
import io.github.jmgarridopaz.discounter.application.Rate;
import io.github.jmgarridopaz.discounter.application.ports.driven.ForObtainingRates;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * Hardcoded rate table
 *
 * 	Break_Point (€)		Discount_Rate (%)
 * 			0.00			0
 * 		  500.00			3
 * 		5,000.00			5
 */
public class StubRateAdapter implements ForObtainingRates {

    private List<BreakPointWithRate> rateTable;

    public StubRateAdapter() {
        this.rateTable = new ArrayList<BreakPointWithRate>();
        BreakPointWithRate breakPoint__0__rate__0 = new BreakPointWithRate(Amount.parse("0"), Rate.parse("0"));
        BreakPointWithRate breakPoint__500__rate__0_03 = new BreakPointWithRate(Amount.parse("500"), Rate.parse("0.03"));
        BreakPointWithRate breakPoint__5000__rate__0_05 = new BreakPointWithRate(Amount.parse("5000"), Rate.parse("0.05"));
        this.rateTable.add(breakPoint__0__rate__0);
        this.rateTable.add(breakPoint__500__rate__0_03);
        this.rateTable.add(breakPoint__5000__rate__0_05);
    }

    @Override
    public ListIterator<BreakPointWithRate> getDescendingBreakPointIterator() {
        return this.rateTable.listIterator ( this.rateTable.size() );
    }

}
