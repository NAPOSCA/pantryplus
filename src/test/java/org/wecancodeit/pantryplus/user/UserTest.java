package org.wecancodeit.pantryplus.user;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class UserTest {

	private String date = "2018-04-04";

	@Test
	public void shouldCalculateCouponTotalForOneOrTwoPersonFamily() {
		User user = new User("firstName", "lastName", 1, 0, false, date, "00000", "1234 Main St", "January 1st, 1969");
		int couponTotal = user.calculateCouponLimit();

		assertThat(couponTotal, is(10));
	}

	@Test
	public void shouldCalculateCouponTotalForThreePersonFamily() {
		User user = new User("firstName", "lastName", 3, 0, false, date, "00000", "1234 Main St", "January 1st, 1969");
		int couponTotal = user.calculateCouponLimit();

		assertThat(couponTotal, is(20));
	}

	@Test
	public void shouldCalculateMeatTotalForOneOrTwoPersonFamily() {
		User user = new User("firstName", "lastName", 1, 0, false, date, "00000", "1234 Main St", "January 1st, 1969");
		int meatTotal = user.calculateMeatLimit();

		assertThat(meatTotal, is(4));
	}

	@Test
	public void shouldCalculateMeatTotalForThreeToFivePersonFamily() {
		User user = new User("firstName", "lastName", 3, 0, false, date, "00000", "1234 Main St", "January 1st, 1969");
		int meatTotal = user.calculateMeatLimit();

		assertThat(meatTotal, is(6));
	}

	@Test
	public void shouldCalculateMeatTotalForSixOrMorePersonFamily() {
		User user = new User("firstName", "lastName", 6, 0, false, date, "00000", "1234 Main St", "January 1st, 1969");
		int meatTotal = user.calculateMeatLimit();

		assertThat(meatTotal, is(8));
	}

	@Test
	public void shouldTakeZipCode12345() {
		String zip = "12345";
		User underTest = new User("firstName", "lastName", 1, 1, true, date, zip, "1234 Main St", "January 1st, 1969");
		String check = underTest.getZipCode();

		assertThat(check, is(zip));
	}

	@Test
	public void shouldTakeZipCode54321() {
		String zip = "54321";
		User underTest = new User("firstName", "lastName", 1, 1, true, date, zip, "1234 Main St", "January 1st, 1969");
		String check = underTest.getZipCode();

		assertThat(check, is(zip));
	}

	@Test
	public void shouldAddFamilySizeToModel() {
		int familySize = 4;
		User underTest = new User("firstName", "lastName", familySize, 0, true, date, "", "", "January 1st, 1969");
		int actual = (int) underTest.toModel().get("familySize");
		assertThat(actual, is(familySize));
	}

	@Test
	public void shouldAddBirthdateToModel() {
		String birthdate = "January 1st, 1969";
		User underTest = new User("firstName", "lastName", 0, 0, true, date, "", "", birthdate);
		String actual = (String) underTest.toModel().get("birthdate");
		assertThat(actual, is(birthdate));
	}
	
	@Test
	public void shouldAddAddressToModel() {
		String address = "your house";
		User underTest = new User("firstName", "lastName", 0, 0, true, date, "", address, "January 1st, 1969");
		String actual = (String) underTest.toModel().get("address");
		assertThat(actual, is(address));
	}
	
	@Test
	public void shouldAddSacToModel() {
		int sacs = 2;
		User underTest = new User("firstName", "lastName", 6, sacs, true, date, "", "", "January 1st, 1969");
		int actual = (int) underTest.toModel().get("schoolAgeChildren");
		assertThat(actual, is(sacs));
	}

}
