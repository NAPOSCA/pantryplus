package org.wecancodeit.pantryplus;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class UserTest {

	@Test
	public void shouldCalculateCouponAmountForOneOrTwoPersonFamily() {
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

	@Test
	public void shouldCalculateMeatAmountForOneOrTwoPersonFamily() {
		User user = new User(1);
		int meatTotal = user.calculateMeatTotal();

		assertThat(meatTotal, is(4));
	}

	@Test
	public void shouldCalculateMeatAmountForThreeToFivePersonFamily() {
		User user = new User(3);
		int meatTotal = user.calculateMeatTotal();

		assertThat(meatTotal, is(6));
	}

	@Test
	public void shouldCalculateMeatAmountForSixOrMorePersonFamily() {
		User user = new User(6);
		int meatTotal = user.calculateMeatTotal();

		assertThat(meatTotal, is(8));
	}

}
