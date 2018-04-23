package org.wecancodeit.pantryplus.cart;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.wecancodeit.pantryplus.lineitem.CountedLineItem;
import org.wecancodeit.pantryplus.lineitem.LineItem;
import org.wecancodeit.pantryplus.product.LimitedProduct;
import org.wecancodeit.pantryplus.product.PricedProduct;
import org.wecancodeit.pantryplus.product.Product;
import org.wecancodeit.pantryplus.user.User;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Cart {

	@Id
	@GeneratedValue
	private long id;

	@JsonIgnore
	@ManyToOne
	private User user;

	@OneToMany(mappedBy = "cart", orphanRemoval = true)
	Set<LineItem> lineItems;

	private int couponsUsed;

	private int meatPoundsUsed;

	public User getUser() {
		return user;
	}

	public long getId() {
		return id;
	}

	public Set<LineItem> getLineItems() {
		return lineItems;
	}

	public LineItem getLineItemByProductId(long productId) {
		for (LineItem lineItem : lineItems) {
			if (lineItem.getProduct().getId() == productId) {
				return lineItem;
			}
		}
		return null;
	}

	public int getLineItemQuantityByProductId(long productId) {
		LineItem lineItem = getLineItemByProductId(productId);
		if (lineItem == null) {
			return 0;
		}
		if (isCountedLineItem(lineItem)) {
			CountedLineItem countedLineItem = (CountedLineItem) lineItem;
			return countedLineItem.getQuantity();
		}
		return 1;
	}

	public int getCartQuantity() {
		int totalQuantity = 0;
		for (LineItem item : lineItems) {
			if (isCountedLineItem(item)) {
				totalQuantity += ((CountedLineItem) item).getQuantity();
			}
		}
		return totalQuantity;
	}

	public int getCouponsUsed() {
		return couponsUsed;
	}

	public int getMeatPoundsUsed() {
		return meatPoundsUsed;
	}

	@SuppressWarnings("unused")
	private Cart() {
	}

	public Cart(User user) {
		this.user = user;
	}

	public void refreshStats() {
		refreshCouponsUsed();
		refreshMeatPoundsUsed();
	}

	public void refreshMeatPoundsUsed() {
		meatPoundsUsed = 0;
		for (LineItem item : getLineItems()) {
			if (isCountedLineItem(item)) {
				CountedLineItem countedLineItem = (CountedLineItem) item;
				if (!countedLineItem.hasCouponProduct()) {
					meatPoundsUsed += countedLineItem.getQuantity();
				}
			}
		}
	}

	public void refreshCouponsUsed() {
		Stream<LineItem> lineItemStream = getLineItems().stream().filter(item -> isCountedLineItem(item));
		Stream<CountedLineItem> countedLineItemStream = lineItemStream.map(item -> (CountedLineItem) item);
		couponsUsed = countedLineItemStream.mapToInt(item -> item.getCouponsUsed()).sum();
	}

	private boolean isCountedLineItem(LineItem item) {
		return item instanceof CountedLineItem;
	}

	public CountedLineItem increaseProductByOne(long productId) {
		CountedLineItem countedLineItem = (CountedLineItem) getLineItemByProductId(productId);
		Product product = countedLineItem.getProduct();
		if (product.getCategory().getName() == "Meat") {
			if (willAddingQuantityBeWithinMeatLimit())
				if (product.getName() == "Ground Beef") {
					if (countedLineItem.getQuantity() < 2)
						countedLineItem.increaseQuantity(1);
				} else {
					countedLineItem.increaseQuantity(1);
				}
		} else if (product instanceof PricedProduct) {
			PricedProduct couponProduct = (PricedProduct) product;
			refreshCouponsUsed();
			int quantityBeingAdded = 1;
			if (willAddingQuantityBeWithinCouponLimit(couponProduct, quantityBeingAdded)) {
				if (this.couponsUsed < this.user.calculateCouponLimit()) {
					if (isCouponProductWithinQuantityLimit(countedLineItem, couponProduct)) {
						countedLineItem.increaseQuantity(1);
					}
				}
			}
		} else {
			countedLineItem.increaseQuantity(1);
		}
		refreshStats();
		return countedLineItem;
	}

	public boolean willAddingQuantityBeWithinMeatLimit() {
		refreshMeatPoundsUsed();
		return getUser().calculateMeatLimit() > meatPoundsUsed;
	}

	public boolean willAddingQuantityBeWithinCouponLimit(PricedProduct couponProduct, int quantityBeingAdded) {
		return (couponProduct.getPrice() * quantityBeingAdded + couponsUsed) <= user.calculateCouponLimit();
	}

	private boolean isCouponProductWithinQuantityLimit(CountedLineItem countedLineItem, LimitedProduct couponProduct) {
		return couponProduct.getMaximumQuantity() > countedLineItem.getQuantity();
	}

	public CountedLineItem decreaseProductByOne(long productId) {
		CountedLineItem countedLineItem = (CountedLineItem) getLineItemByProductId(productId);
		countedLineItem.reduceQuantity(1);
		refreshStats();
		return countedLineItem;
	}

	public CountedLineItem updateQuantityOfProduct(long productId, int quantity) {
		CountedLineItem countedLineItem = (CountedLineItem) getLineItemByProductId(productId);
		countedLineItem.setQuantity(quantity);
		refreshStats();
		return countedLineItem;
	}

	public LineItem popItemByProductId(long productId) {
		LineItem lineItem = getLineItemByProductId(productId);
		lineItem.detachFromCart();
		return lineItem;
	}

	public void removeAllItems() {
		lineItems.removeIf((lineItem) -> {
			return true;
		});
	}

	public boolean has(long productId) {
		for (LineItem lineItem : lineItems) {
			if (lineItem.getProduct().getId() == productId) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
		return ((Long) id).hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		if (getClass() != obj.getClass()) {
			return false;
		}

		if (id == ((Cart) obj).id) {
			return true;
		}

		return false;
	}

	public Map<String, Object> toModel() {
		Map<String, Object> model = new HashMap<>();
		User u = getUser();
		model.put("user", u);
		Set<LineItem> lineItems = getLineItems();
		lineItems.removeIf(lineItem -> lineItem instanceof CountedLineItem);
		model.put("lineItems", lineItems);
//		Set<LineItem> lineItems = new HashSet<>();
//		Set<CountedLineItem> countedLineItems = new HashSet<>();
//		for(LineItem lineItem : getLineItems()) {
//			if(lineItem instanceof CountedLineItem) {
//				CountedLineItem countedLineItem = (CountedLineItem) lineItem;
//				countedLineItems.add(countedLineItem);
//			} else {
//				lineItems.add(lineItem);
//			}
//		}
//		model.put("lineItems", lineItems);
//		model.put("countedLineItems", countedLineItems);
		return model;
	}

}
