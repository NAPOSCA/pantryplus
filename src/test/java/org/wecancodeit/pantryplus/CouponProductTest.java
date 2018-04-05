package org.wecancodeit.pantryplus;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class CouponProductTest {
	
	@Test
	public void shouldHaveCouponCostOne() {
		int cost = 1;
		Product underTest = new CouponProduct("name", null, cost);
		int actual = ((CouponProduct) underTest).getCost();
		assertThat(actual, is(cost));
	}
}
