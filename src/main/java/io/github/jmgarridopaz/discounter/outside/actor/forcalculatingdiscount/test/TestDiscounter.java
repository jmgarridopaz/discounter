package io.github.jmgarridopaz.discounter.outside.actor.forcalculatingdiscount.test;

import io.github.jmgarridopaz.discounter.application.ForCalculatingDiscount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.math.BigDecimal;

public class TestDiscounter {

	@Test
	public void shouldBeTrue() {
		Assert.assertTrue(true);
	}

	@Test
	public void givenNoRateRepoThenDiscountOf100ShouldBe15() {
		BigDecimal expectedDiscount = new BigDecimal("15.00");
		BigDecimal currentDiscount = TestCases.getDiscountCalculator().calculateDiscount(new BigDecimal("100.00"));
		Assert.assertTrue(currentDiscount.compareTo(expectedDiscount)==0);
	}

}
