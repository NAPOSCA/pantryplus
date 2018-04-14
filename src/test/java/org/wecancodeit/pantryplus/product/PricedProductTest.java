package org.wecancodeit.pantryplus.product;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class PricedProductTest {

	@Test
	public void shouldHaveCouponCostOne() {
		int cost = 1;
		Product underTest = new PricedProduct("name", null, cost);
		int actual = ((PricedProduct) underTest).getPrice();
		assertThat(actual, is(cost));
	}

	@Test
	public void shouldHaveCouponCostTwo() {
		int cost = 2;
		PricedProduct underTest = new PricedProduct("name", null, cost);
		int actual = underTest.getPrice();
		assertThat(actual, is(cost));
	}

	@Test
	public void shouldHaveALimit() {
		int cost = 2;
		int limit = 3;
		LimitedProduct underTest = new PricedProduct("name", null, limit, cost);
		int actual = underTest.getMaximumQuantity();
		assertThat(actual, is(limit));
	}

}
