package io.github.jmgarridopaz.discounter.outside.foradiscounting.testcases;

import io.github.jmgarridopaz.discounter.application.Amount;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.math.BigDecimal;


public class TestForDiscounting {

	private final static String MAX_AMOUNT = Integer.MAX_VALUE + ".00";

	@Test
	public void givenTheseRatesByAmountIntervalsThenDiscountOf20ShouldBe0() {
		// given
		String[][] ratesByAmountIntervals = { {"20.01","100.00","0.15"} , {"100.01",MAX_AMOUNT,"0.35"} };
		TestDriver.getDiscounterApp().initializeRates ( ratesByAmountIntervals );
		Amount expectedDiscount = Amount.parse("0.00");
		// when
		Amount currentDiscount = TestDriver.getDiscounterApp().calculateDiscount( Amount.parse("20.00") );
		// then
		Assert.assertTrue(currentDiscount.value().compareTo(expectedDiscount.value())==0);
	}

	@Test
	public void givenTheseRatesByAmountIntervalsThenDiscountOf60ShouldBe9() {
		// given
		String[][] ratesByAmountIntervals = { {"20.01","100.00","0.15"} , {"100.01",MAX_AMOUNT,"0.35"} };
		TestDriver.getDiscounterApp().initializeRates ( ratesByAmountIntervals );
		Amount expectedDiscount = Amount.parse("9.00");
		// when
		Amount currentDiscount = TestDriver.getDiscounterApp().calculateDiscount ( Amount.parse("60.00") );
		// then
		Assert.assertTrue(currentDiscount.value().compareTo(expectedDiscount.value())==0);
	}

	@Test
	public void givenTheseRatesByAmountIntervalsThenDiscountOf200ShouldBe70() {
		// given
		String[][] ratesByAmountIntervals = { {"20.01","100.00","0.15"} , {"100.01",MAX_AMOUNT,"0.35"} };
		TestDriver.getDiscounterApp().initializeRates ( ratesByAmountIntervals );
		Amount expectedDiscount = Amount.parse("70.00");
		// when
		Amount currentDiscount = TestDriver.getDiscounterApp().calculateDiscount ( Amount.parse("200.00") );
		// then
		Assert.assertTrue(currentDiscount.value().compareTo(expectedDiscount.value())==0);
	}

}
