package org.wecancodeit.pantryplus;

public class User {

	private String name;
	private String address;
	private int familySize;
	private int schoolAgeChildren;
	private boolean hasInfants;

	public User(String name, String address, int familySize, int schoolAgeChildren, boolean hasInfants) {
		this.name = name;
		this.address = address;
		this.familySize = familySize;
		this.schoolAgeChildren = schoolAgeChildren;
		this.hasInfants = hasInfants;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public int getFamilySize() {
		return familySize;
	}

	public int getSchoolAgeChildren() {
		return schoolAgeChildren;
	}

	public boolean getHasInfants() {
		return hasInfants;
	}

	public int calculateCouponTotal() {
		if (familySize <= 2) {
			return 10;
		}
		return 15;
	}

	public int calculateMeatTotal() {
		if (familySize <= 2) {
			return 4;
		} else if (familySize <= 5) {
			return 6;
		} else {
			return 8;
		}
	}

	public int calculateCannedFoodTotalForKids() {
		if (schoolAgeChildren <= 2) {
			return 2;
		} else if (schoolAgeChildren <= 4) {
			return 4;
		} else {
			return 6;
		}
	}

	public int calculateSnackTotalForKids() {
		return schoolAgeChildren * 5;
	}

	public int calculateFruitTotalForKids() {
		return schoolAgeChildren * 3;
	}

	public int calculateDairyProductTotalForKids() {
		return schoolAgeChildren;
	}

}
