package org.wecancodeit.pantryplus.user;

import static javax.persistence.GenerationType.SEQUENCE;

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import org.wecancodeit.pantryplus.cart.Cart;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = "HashTagIDGenerator")
	@SequenceGenerator(name = "HashTagIDGenerator", sequenceName = "SEQ_HASHTAG_ID")
	private long id;

	@OneToMany(mappedBy = "user")
	private Collection<Cart> carts;

	private String firstName;
	private String lastName;
	private int familySize;
	private int schoolAgeChildren;
	private boolean hasInfants;
	private ZonedDateTime pickupDateTime;
	private String zipCode;

	private String birthdate;

	private String address;

	@SuppressWarnings("unused")
	private User() {
	}

	public User(String firstName, String lastName, int familySize, int schoolAgeChildren, boolean hasInfants,
			String pickupDateString, String zipCode, String address, String birthdate) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.familySize = familySize;
		this.schoolAgeChildren = schoolAgeChildren;
		this.hasInfants = hasInfants;
		this.zipCode = zipCode;
		pickupDateTime = ZonedDateTime.parse(pickupDateString + "T12:00:00-04:00[US/Eastern]");
		this.birthdate = birthdate;
		this.address = address;
	}

	public long getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public Collection<Cart> getCarts() {
		return carts;
	}

	public int getSchoolAgeChildren() {
		return schoolAgeChildren;
	}

	public boolean hasInfants() {
		return hasInfants;
	}

	public ZonedDateTime getPickupDateTime() {
		return pickupDateTime;
	}

	public int calculateCouponLimit() {
		if (familySize <= 2) {
			return 10;
		} else if (familySize <= 4) {
			return 20;
		} else if (familySize <= 6) {
			return 25;
		} else if (familySize <= 8) {
			return 30;
		}
		return 35;
	}

	public int calculateMeatLimit() {
		if (familySize <= 2) {
			return 4;
		} else if (familySize <= 5) {
			return 6;
		}
		return 8;
	}

	public String getZipCode() {
		return zipCode;
	}

	public int getFamilySize() {
		return familySize;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public String getAddress() {
		return address;
	}

	public Map<String, Object> toModel() {
		Map<String, Object> model = new HashMap<>();
		model.put("firstName", getFirstName());
		model.put("lastName", getLastName());
		model.put("familySize", getFamilySize());
		model.put("birthdate", getBirthdate());
		model.put("address", getAddress());
		model.put("schoolAgeChildren", getSchoolAgeChildren());
		return model;
	}

}
