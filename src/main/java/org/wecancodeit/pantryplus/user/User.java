package org.wecancodeit.pantryplus.user;

import java.time.ZonedDateTime;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.wecancodeit.pantryplus.cart.Cart;

@Entity
public class User {

	@Id
	@GeneratedValue
	private long id;

	@OneToMany(mappedBy = "user")
	private Collection<Cart> carts;

	private int familySize;
	private int schoolAgeChildren;
	private boolean hasInfants;
	private ZonedDateTime pickupDateTime;
	private String zipCode;

	@SuppressWarnings("unused")
	private User() {
	}

	public User(int familySize, int schoolAgeChildren, boolean hasInfants, String pickupDateString, String zipCode) {
		this.familySize = familySize;
		this.schoolAgeChildren = schoolAgeChildren;
		this.hasInfants = hasInfants;
		this.zipCode = zipCode;
		pickupDateTime = ZonedDateTime.parse(pickupDateString + "T12:00:00-04:00[US/Eastern]");
	}

	public long getId() {
		return id;
	}

	public Collection<Cart> getCarts() {
		return carts;
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
		} else if (familySize <= 4) {
			return 20;
		} else if (familySize <= 6) {
			return 25;
		} else if (familySize <= 8) {
			return 30;
		} else {
			return 35;
		}
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

	public String getZipCode() {
		return zipCode;
	}

}
