package org.wecancodeit.pantryplus.user;

import java.time.ZonedDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {

	@Id
	@GeneratedValue
	private long id;
	private int familySize;
	private int schoolAgeChildren;
	private boolean hasInfants;
	private ZonedDateTime pickupDateTime;

	@SuppressWarnings("unused")
	private User() {
	}

	public User(int familySize, int schoolAgeChildren, boolean hasInfants, String pickupDateString) {
		this.familySize = familySize;
		this.schoolAgeChildren = schoolAgeChildren;
		this.hasInfants = hasInfants;

		pickupDateTime = ZonedDateTime.parse(pickupDateString + "T12:00:00-04:00[US/Eastern]");
	}

	public long getId() {
		return id;
	}

	public boolean getHasInfants() {
		return hasInfants;
	}

	public ZonedDateTime getPickupDateTime() {
		return pickupDateTime;
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
		}
		return 8;
	}

	public int calculateCannedFoodTotalForKids() {
		if (schoolAgeChildren <= 2) {
			return 2;
		} else if (schoolAgeChildren <= 4) {
			return 4;
		}
		return 6;
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
