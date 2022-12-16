package io.github.jmgarridopaz.discounter.outside.foradiscounting.testcases;

import io.github.jmgarridopaz.discounter.application.Amount;
import io.github.jmgarridopaz.discounter.application.DiscounterApp;
import io.github.jmgarridopaz.discounter.application.ports.driving.ForDiscounting;
import io.github.jmgarridopaz.discounter.outside.forobtainingrates.file.FileRateAdapter;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

/**
 * File rate table
 *
 *  Break_Point (€)     Discount_Rate (%)
 * 			 0.00           0
 *         100.00			5
 *       1,000.00			7
 *      10,000.00	       10
 */
public class FileRateRepositoryTests {

	private final static String TMP_RATE_FILE_NAME =
										System.getProperty("user.home") + File.separator
										+ "tmpratefile.txt";

	@Test
	public void discount_of_0_should_be_0() {
		// given
		ForDiscounting discounterApp = new DiscounterApp ( new FileRateAdapter(TMP_RATE_FILE_NAME,"0 0","100 0.05","1000 0.07","10000 0.1") );
		// when
		Amount currentDiscount = discounterApp.calculateDiscount( Amount.parse("0") );
		Amount expectedDiscount = Amount.parse("0");
		// then
		Assert.assertTrue ( currentDiscount.isEqualTo(expectedDiscount) );
	}

	@Test
	public void discount_of_50_should_be_0() {
		// given
		ForDiscounting discounterApp = new DiscounterApp ( new FileRateAdapter(TMP_RATE_FILE_NAME,"0 0","100 0.05","1000 0.07","10000 0.1") );
		// when
		Amount currentDiscount = discounterApp.calculateDiscount( Amount.parse("50") );
		Amount expectedDiscount = Amount.parse("0");
		// then
		Assert.assertTrue ( currentDiscount.isEqualTo(expectedDiscount) );
	}

	@Test
	public void discount_of__99_99__should_be_0() {
		// given
		ForDiscounting discounterApp = new DiscounterApp ( new FileRateAdapter(TMP_RATE_FILE_NAME,"0 0","100 0.05","1000 0.07","10000 0.1") );
		// when
		Amount currentDiscount = discounterApp.calculateDiscount( Amount.parse("99.99") );
		Amount expectedDiscount = Amount.parse("0");
		// then
		Assert.assertTrue ( currentDiscount.isEqualTo(expectedDiscount) );
	}

	@Test
	public void discount_of_100_should_be_5() {
		// given
		ForDiscounting discounterApp = new DiscounterApp ( new FileRateAdapter(TMP_RATE_FILE_NAME,"0 0","100 0.05","1000 0.07","10000 0.1") );
		// when
		Amount currentDiscount = discounterApp.calculateDiscount( Amount.parse("100") );
		Amount expectedDiscount = Amount.parse("5");
		// then
		Assert.assertTrue ( currentDiscount.isEqualTo(expectedDiscount) );
	}

	@Test
	public void discount_of_550_should_be__27_5() {
		// given
		ForDiscounting discounterApp = new DiscounterApp ( new FileRateAdapter(TMP_RATE_FILE_NAME,"0 0","100 0.05","1000 0.07","10000 0.1") );
		// when
		Amount currentDiscount = discounterApp.calculateDiscount( Amount.parse("550") );
		Amount expectedDiscount = Amount.parse("27.5");
		// then
		Assert.assertTrue ( currentDiscount.isEqualTo(expectedDiscount) );
	}

	@Test
	public void discount_of__999_99__should_be_50() {
		// given
		ForDiscounting discounterApp = new DiscounterApp ( new FileRateAdapter(TMP_RATE_FILE_NAME,"0 0","100 0.05","1000 0.07","10000 0.1") );
		// when
		Amount currentDiscount = discounterApp.calculateDiscount( Amount.parse("999.99") );
		Amount expectedDiscount = Amount.parse("50");
		// then
		Assert.assertTrue ( currentDiscount.isEqualTo(expectedDiscount) );
	}

	@Test
	public void discount_of_1000_should_be_70() {
		// given
		ForDiscounting discounterApp = new DiscounterApp ( new FileRateAdapter(TMP_RATE_FILE_NAME,"0 0","100 0.05","1000 0.07","10000 0.1") );
		// when
		Amount currentDiscount = discounterApp.calculateDiscount( Amount.parse("1000") );
		Amount expectedDiscount = Amount.parse("70");
		// then
		Assert.assertTrue ( currentDiscount.isEqualTo(expectedDiscount) );
	}

	@Test
	public void discount_of_5500_should_be_385() {
		// given
		ForDiscounting discounterApp = new DiscounterApp ( new FileRateAdapter(TMP_RATE_FILE_NAME,"0 0","100 0.05","1000 0.07","10000 0.1") );
		// when
		Amount currentDiscount = discounterApp.calculateDiscount( Amount.parse("5500") );
		Amount expectedDiscount = Amount.parse("385");
		// then
		Assert.assertTrue ( currentDiscount.isEqualTo(expectedDiscount) );
	}

	@Test
	public void discount_of__9999_99__should_be_700() {
		// given
		ForDiscounting discounterApp = new DiscounterApp ( new FileRateAdapter(TMP_RATE_FILE_NAME,"0 0","100 0.05","1000 0.07","10000 0.1") );
		// when
		Amount currentDiscount = discounterApp.calculateDiscount( Amount.parse("9999.99") );
		Amount expectedDiscount = Amount.parse("700");
		// then
		Assert.assertTrue ( currentDiscount.isEqualTo(expectedDiscount) );
	}

	@Test
	public void discount_of_10000_should_be_1000() {
		// given
		ForDiscounting discounterApp = new DiscounterApp ( new FileRateAdapter(TMP_RATE_FILE_NAME,"0 0","100 0.05","1000 0.07","10000 0.1") );
		// when
		Amount currentDiscount = discounterApp.calculateDiscount( Amount.parse("10000") );
		Amount expectedDiscount = Amount.parse("1000");
		// then
		Assert.assertTrue ( currentDiscount.isEqualTo(expectedDiscount) );
	}

	@Test
	public void discount_of_20500_should_be_2050() {
		// given
		ForDiscounting discounterApp = new DiscounterApp ( new FileRateAdapter(TMP_RATE_FILE_NAME,"0 0","100 0.05","1000 0.07","10000 0.1") );
		// when
		Amount currentDiscount = discounterApp.calculateDiscount( Amount.parse("20500") );
		Amount expectedDiscount = Amount.parse("2050");
		// then
		Assert.assertTrue ( currentDiscount.isEqualTo(expectedDiscount) );
	}

}
