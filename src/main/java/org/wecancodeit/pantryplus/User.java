package org.wecancodeit.pantryplus;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {

	@Id
	@GeneratedValue
	private long id;
	private String name;
	private String address;
	private int familySize;
	private int schoolAgeChildren;
	private boolean hasInfants;
	private LocalDate pickupDate;

	@SuppressWarnings("unused")
	private User() {
	}

	public User(String name, String address, int familySize, int schoolAgeChildren, boolean hasInfants, String pickupDateString) {
		this.name = name;
		this.address = address;
		this.familySize = familySize;
		this.schoolAgeChildren = schoolAgeChildren;
		this.hasInfants = hasInfants;
		pickupDate = LocalDate.parse(pickupDateString);
	}

	public long getId() {
		return id;
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

	public LocalDate getPickupDate() {
		return pickupDate;
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
