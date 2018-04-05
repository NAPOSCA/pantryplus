package org.wecancodeit.pantryplus;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class UserTest {

	@Test
	public void shouldCalculateCouponTotalForOneOrTwoPersonFamily() {
		User user = new User("name", "address", 1, 0, false, "2018-04-04");
		int couponTotal = user.calculateCouponTotal();

		assertThat(couponTotal, is(10));
	}

	@Test
	public void shouldCalculateCouponTotalForThreePersonFamily() {
		User user = new User("name", "address", 3, 0, false, "2018-04-04");
		int couponTotal = user.calculateCouponTotal();

		assertThat(couponTotal, is(15));
	}

	@Test
	public void shouldCalculateMeatTotalForOneOrTwoPersonFamily() {
		User user = new User("name", "address", 1, 0, false, "2018-04-04");
		int meatTotal = user.calculateMeatTotal();

		assertThat(meatTotal, is(4));
	}

	@Test
	public void shouldCalculateMeatTotalForThreeToFivePersonFamily() {
		User user = new User("name", "address", 3, 0, false, "2018-04-04");
		int meatTotal = user.calculateMeatTotal();

		assertThat(meatTotal, is(6));
	}

	@Test
	public void shouldCalculateMeatTotalForSixOrMorePersonFamily() {
		User user = new User("name", "address", 6, 0, false, "2018-04-04");
		int meatTotal = user.calculateMeatTotal();

		assertThat(meatTotal, is(8));
	}

	@Test
	public void shouldCalculateCannedFoodTotalForFamilyWithOneOrTwoSchoolAgeKids() {
		User user = new User("name", "address", 6, 1, false, "2018-04-04");
		int cannedFoodTotal = user.calculateCannedFoodTotalForKids();

		assertThat(cannedFoodTotal, is(2));
	}

	@Test
	public void shouldCalculateCannedFoodTotalForFamilyWithThreeOrFourSchoolAgeKids() {
		User user = new User("name", "address", 6, 3, false, "2018-04-04");
		int cannedFoodTotal = user.calculateCannedFoodTotalForKids();

		assertThat(cannedFoodTotal, is(4));
	}

	@Test
	public void shouldCalculateCannedFoodTotalForFamilyWithFiveOrMoreSchoolAgeKids() {
		User user = new User("name", "address", 6, 5, false, "2018-04-04");
		int cannedFoodTotal = user.calculateCannedFoodTotalForKids();

		assertThat(cannedFoodTotal, is(6));
	}

	@Test
	public void shouldCalculateSnackTotalForFamilyWithSchoolAgeKids() {
		User user = new User("name", "address", 6, 3, false, "2018-04-04");
		int snackTotal = user.calculateSnackTotalForKids();

		assertThat(snackTotal, is(15));
	}

	@Test
	public void shouldCalculateFruitTotalForFamilyWithSchoolAgeKids() {
		User user = new User("name", "address", 6, 3, false, "2018-04-04");
		int snackTotal = user.calculateFruitTotalForKids();

		assertThat(snackTotal, is(9));
	}

	@Test
	public void shouldCalculateDairyProductTotalForFamilyWithSchoolAgeKids() {
		User user = new User("name", "address", 6, 3, false, "2018-04-04");
		int snackTotal = user.calculateDairyProductTotalForKids();

		assertThat(snackTotal, is(3));
	}

}
