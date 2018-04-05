package org.wecancodeit.pantryplus;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class UserTest {

	@Test
	public void shouldCalculateCouponAmountForOnePersonFamily() {
		User user = new User(1);
		int couponTotal = user.calculateCouponTotal();

		assertThat(couponTotal, is(10));
	}

	@Test
	public void shouldCalculateCouponAmountForThreePersonFamily() {
		User user = new User(3);
		int couponTotal = user.calculateCouponTotal();

		assertThat(couponTotal, is(15));
	}

}
