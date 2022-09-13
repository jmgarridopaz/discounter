package io.github.jmgarridopaz.discounter.outside.foradiscounting.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;
import java.math.BigDecimal;
import java.util.AbstractMap;
import java.util.Map;


public class TestDiscounterApp {

	@Test
	public void givenTheseRatesByAmountIntervalsThenDiscountOf20ShouldBe0() {
		// given
		String[][] ratesByAmountIntervals = { {"20.01","100.00","0.15"} , {"100.01","","0.35"} };
		TestCases.getDiscounterApp().initializeRates ( ratesByAmountIntervals );
		BigDecimal expectedDiscount = new BigDecimal("0.00");
		// when
		BigDecimal currentDiscount = TestCases.getDiscounterApp().calculateDiscount(new BigDecimal("20.00"));
		// then
		Assert.assertTrue(currentDiscount.compareTo(expectedDiscount)==0);
	}

	@Test
	public void givenTheseRatesByAmountIntervalsThenDiscountOf60ShouldBe9() {
		// given
		String[][] ratesByAmountIntervals = { {"20.01","100.00","0.15"} , {"100.01","","0.35"} };
		TestCases.getDiscounterApp().initializeRates ( ratesByAmountIntervals );
		BigDecimal expectedDiscount = new BigDecimal("9.00");
		// when
		BigDecimal currentDiscount = TestCases.getDiscounterApp().calculateDiscount(new BigDecimal("60.00"));
		// then
		Assert.assertTrue(currentDiscount.compareTo(expectedDiscount)==0);
	}

	@Test
	public void givenTheseRatesByAmountIntervalsThenDiscountOf200ShouldBe70() {
		// given
		String[][] ratesByAmountIntervals = { {"20.01","100.00","0.15"} , {"100.01","","0.35"} };
		TestCases.getDiscounterApp().initializeRates ( ratesByAmountIntervals );
		BigDecimal expectedDiscount = new BigDecimal("70.00");
		// when
		BigDecimal currentDiscount = TestCases.getDiscounterApp().calculateDiscount(new BigDecimal("200.00"));
		// then
		Assert.assertTrue(currentDiscount.compareTo(expectedDiscount)==0);
	}

}
