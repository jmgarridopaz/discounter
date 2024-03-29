package io.github.jmgarridopaz.discounter.outside.foradiscounting.testcases;

import io.github.jmgarridopaz.discounter.application.Amount;
import io.github.jmgarridopaz.discounter.application.DiscounterApp;
import io.github.jmgarridopaz.discounter.application.ports.driving.ForDiscounting;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Hardcoded rate is 8% for any amount
 *
 * 	Break_Point (€)		Discount_Rate (%)
 * 			0.00			8
 */
public class NoRateRepositoryTests {

	@Test
	public void discount_of_0_should_be_0() {
		// given
		ForDiscounting discounterApp = new DiscounterApp(null);
		// when
		Amount currentDiscount = discounterApp.calculateDiscount( Amount.parse("0.00") );
		Amount expectedDiscount = Amount.parse("0.00");
		// then
		Assert.assertTrue ( currentDiscount.isEqualTo(expectedDiscount) );
	}

	@Test
	public void discount_of_100_should_be_8() {
		// given
		ForDiscounting discounterApp = new DiscounterApp(null);
		// when
		Amount currentDiscount = discounterApp.calculateDiscount( Amount.parse("100.00") );
		Amount expectedDiscount = Amount.parse("8.00");
		// then
		Assert.assertTrue ( currentDiscount.isEqualTo(expectedDiscount) );
	}

	@Test
	public void discount_of_50_should_be_4() {
		// given
		ForDiscounting discounterApp = new DiscounterApp(null);
		// when
		Amount currentDiscount = discounterApp.calculateDiscount( Amount.parse("50.00") );
		Amount expectedDiscount = Amount.parse("4.00");
		// then
		Assert.assertTrue ( currentDiscount.isEqualTo(expectedDiscount) );
	}

	@Test
	public void discount_of_200_should_be_16() {
		// given
		ForDiscounting discounterApp = new DiscounterApp(null);
		// when
		Amount currentDiscount = discounterApp.calculateDiscount( Amount.parse("200.00") );
		Amount expectedDiscount = Amount.parse("16.00");
		// then
		Assert.assertTrue ( currentDiscount.isEqualTo(expectedDiscount) );
	}

}
