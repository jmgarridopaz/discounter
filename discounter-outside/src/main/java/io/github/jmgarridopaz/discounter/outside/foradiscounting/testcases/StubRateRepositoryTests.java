package io.github.jmgarridopaz.discounter.outside.foradiscounting.testcases;

import io.github.jmgarridopaz.discounter.application.Amount;
import io.github.jmgarridopaz.discounter.application.DiscounterApp;
import io.github.jmgarridopaz.discounter.application.ports.driving.ForDiscounting;
import io.github.jmgarridopaz.discounter.outside.forobtainingrates.testdouble.StubRateAdapter;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Hardcoded rate table
 *
 * 	Break_Point (€)		Discount_Rate (%)
 * 			0.00			0
 * 		  500.00			3
 * 		5,000.00			5
 */
public class StubRateRepositoryTests {

	@Test
	public void discount_of_0_should_be_0() {
		// given
		ForDiscounting discounterApp = new DiscounterApp ( new StubRateAdapter() );
		// when
		Amount currentDiscount = discounterApp.calculateDiscount( Amount.parse("0") );
		Amount expectedDiscount = Amount.parse("0");
		// then
		Assert.assertTrue ( currentDiscount.isEqualTo(expectedDiscount) );
	}

	@Test
	public void discount_of_250_should_be_0() {
		// given
		ForDiscounting discounterApp = new DiscounterApp ( new StubRateAdapter() );
		// when
		Amount currentDiscount = discounterApp.calculateDiscount( Amount.parse("250") );
		Amount expectedDiscount = Amount.parse("0");
		// then
		Assert.assertTrue ( currentDiscount.isEqualTo(expectedDiscount) );
	}

	@Test
	public void discount_of__499_99__should_be_0() {
		// given
		ForDiscounting discounterApp = new DiscounterApp ( new StubRateAdapter() );
		// when
		Amount currentDiscount = discounterApp.calculateDiscount( Amount.parse("499.99") );
		Amount expectedDiscount = Amount.parse("0");
		// then
		Assert.assertTrue ( currentDiscount.isEqualTo(expectedDiscount) );
	}

	@Test
	public void discount_of_500_should_be_15() {
		// given
		ForDiscounting discounterApp = new DiscounterApp ( new StubRateAdapter() );
		// when
		Amount currentDiscount = discounterApp.calculateDiscount( Amount.parse("500") );
		Amount expectedDiscount = Amount.parse("15");
		// then
		Assert.assertTrue ( currentDiscount.isEqualTo(expectedDiscount) );
	}

	@Test
	public void discount_of_2750_should_be__82_5() {
		// given
		ForDiscounting discounterApp = new DiscounterApp ( new StubRateAdapter() );
		// when
		Amount currentDiscount = discounterApp.calculateDiscount( Amount.parse("2750") );
		Amount expectedDiscount = Amount.parse("82.5");
		// then
		Assert.assertTrue ( currentDiscount.isEqualTo(expectedDiscount) );
	}

	@Test
	public void discount_of__4999_99__should_be_150() {
		// given
		ForDiscounting discounterApp = new DiscounterApp ( new StubRateAdapter() );
		// when
		Amount currentDiscount = discounterApp.calculateDiscount( Amount.parse("4999.99") );
		Amount expectedDiscount = Amount.parse("150");
		// then
		Assert.assertTrue ( currentDiscount.isEqualTo(expectedDiscount) );
	}

	@Test
	public void discount_of_5000_should_be_250() {
		// given
		ForDiscounting discounterApp = new DiscounterApp ( new StubRateAdapter() );
		// when
		Amount currentDiscount = discounterApp.calculateDiscount( Amount.parse("5000") );
		Amount expectedDiscount = Amount.parse("250");
		// then
		Assert.assertTrue ( currentDiscount.isEqualTo(expectedDiscount) );
	}

	@Test
	public void discount_of_10000_should_be_500() {
		// given
		ForDiscounting discounterApp = new DiscounterApp ( new StubRateAdapter() );
		// when
		Amount currentDiscount = discounterApp.calculateDiscount( Amount.parse("10000") );
		Amount expectedDiscount = Amount.parse("500");
		// then
		Assert.assertTrue ( currentDiscount.isEqualTo(expectedDiscount) );
	}

}
