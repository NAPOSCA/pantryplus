package org.wecancodeit.pantryplus;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class UserTest {

	@Test
	public void shouldCalculateCouponAmountForOneOrTwoPersonFamily() {
		User user = new User(1, 0);
		int couponTotal = user.calculateCouponTotal();

		assertThat(couponTotal, is(10));
	}

	@Test
	public void shouldCalculateCouponAmountForThreePersonFamily() {
		User user = new User(3, 0);
		int couponTotal = user.calculateCouponTotal();

		assertThat(couponTotal, is(15));
	}

	@Test
	public void shouldCalculateMeatAmountForOneOrTwoPersonFamily() {
		User user = new User(1, 0);
		int meatTotal = user.calculateMeatTotal();

		assertThat(meatTotal, is(4));
	}

	@Test
	public void shouldCalculateMeatAmountForThreeToFivePersonFamily() {
		User user = new User(3, 0);
		int meatTotal = user.calculateMeatTotal();

		assertThat(meatTotal, is(6));
	}

	@Test
	public void shouldCalculateMeatAmountForSixOrMorePersonFamily() {
		User user = new User(6, 0);
		int meatTotal = user.calculateMeatTotal();

		assertThat(meatTotal, is(8));
	}

	@Test
	public void shouldCalculateCannedFoodAmountForFamilyWithOneOrTwoSchoolAgeKids() {
		User user = new User(6, 1);
		int cannedFoodTotal = user.calculateCannedFoodTotalForKids();

		assertThat(cannedFoodTotal, is(2));
	}

	@Test
	public void shouldCalculateCannedFoodAmountForFamilyWithThreeOrFourSchoolAgeKids() {
		User user = new User(6, 3);
		int cannedFoodTotal = user.calculateCannedFoodTotalForKids();

		assertThat(cannedFoodTotal, is(4));
	}

	@Test
	public void shouldCalculateCannedFoodAmountForFamilyWithFiveOrMoreSchoolAgeKids() {
		User user = new User(6, 5);
		int cannedFoodTotal = user.calculateCannedFoodTotalForKids();

		assertThat(cannedFoodTotal, is(6));
	}

}
