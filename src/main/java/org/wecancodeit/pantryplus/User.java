package org.wecancodeit.pantryplus;

public class User {

	private int familySize;

	public User(int familySize) {
		this.familySize = familySize;
	}

	public int getFamilySize() {
		return familySize;
	}

	public int calculateCouponTotal() {
		if (familySize <= 2) {
			return 10;
		}
		return 15;
	}

}
