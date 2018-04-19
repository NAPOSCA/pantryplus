package org.wecancodeit.pantryplus.cart;

import static java.lang.Integer.MAX_VALUE;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import org.wecancodeit.pantryplus.category.Category;
import org.wecancodeit.pantryplus.category.CategoryRepository;
import org.wecancodeit.pantryplus.lineitem.CountedLineItem;
import org.wecancodeit.pantryplus.lineitem.LineItem;
import org.wecancodeit.pantryplus.lineitem.LineItemRepository;
import org.wecancodeit.pantryplus.product.LimitedProduct;
import org.wecancodeit.pantryplus.product.PricedProduct;
import org.wecancodeit.pantryplus.product.Product;
import org.wecancodeit.pantryplus.product.ProductRepository;
import org.wecancodeit.pantryplus.user.User;
import org.wecancodeit.pantryplus.user.UserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CartJpaTest {

	@Resource
	private TestEntityManager entityManager;

	@Resource
	private UserRepository userRepo;

	@Resource
	private CartRepository cartRepo;

	@Resource
	private ProductRepository productRepo;

	@Resource
	private LineItemRepository lineItemRepo;

	@Resource
	private CategoryRepository categoryRepo;

	private User user;
	private long userId;
	private Cart cart;
	private long cartId;
	private Cart anotherCart;
	private Product product;
	private long productId;
	private Product anotherProduct;
	private long anotherProductId;
	private LineItem lineItem;
	private LineItem anotherLineItem;
	private CountedLineItem countedLineItem;
	private CountedLineItem anotherCountedLineItem;
	private Category coupon;
	private Category meat;
	private Category otherCategory;

	@Before
	public void setUp() {
		user = new User("firstName", "lastName", 3, 1, false, "2018-04-09", "43201");
		cart = new Cart(user);
		anotherCart = new Cart(user);
		otherCategory = new Category("FOOOBAAAAR");
		product = new Product("grapefruit", otherCategory);
		anotherProduct = new Product("apple", otherCategory);
		lineItem = new LineItem(cart, product);
		anotherLineItem = new LineItem(cart, anotherProduct);
		countedLineItem = new CountedLineItem(cart, product, 1);
		anotherCountedLineItem = new CountedLineItem(cart, anotherProduct, 2);
		coupon = new Category("Coupon");
		meat = new Category("Meat");

		user = userRepo.save(user);
		userId = user.getId();
		cart = cartRepo.save(cart);
		cartId = cart.getId();
		product = productRepo.save(product);
		productId = product.getId();
		anotherProduct = productRepo.save(anotherProduct);
		anotherProductId = anotherProduct.getId();
		coupon = categoryRepo.save(coupon);
		meat = categoryRepo.save(meat);
		otherCategory = categoryRepo.save(otherCategory);
	}

	@Test
	public void shouldSaveAndLoadCart() {
		entityManager.flush();
		entityManager.clear();
		cart = cartRepo.findOne(cartId);
		assertThat(cart.getId(), is(greaterThan(0L)));
	}

	@Test
	public void shouldSaveManyCartsToOneUser() {
		anotherCart = cartRepo.save(anotherCart);
		entityManager.flush();
		entityManager.clear();
		user = userRepo.findOne(userId);
		assertThat(user.getCarts(), containsInAnyOrder(cart, anotherCart));
	}

	@Test
	public void shouldSaveAndLoadLineItem() {
		lineItem = lineItemRepo.save(lineItem);
		long lineItemId = lineItem.getId();
		entityManager.flush();
		entityManager.clear();
		lineItem = lineItemRepo.findOne(lineItemId);
		assertThat(lineItem.getProduct(), is(product));
	}

	@Test
	public void shouldSaveManyLineItemsToOneCart() {
		lineItem = lineItemRepo.save(lineItem);
		anotherLineItem = lineItemRepo.save(anotherLineItem);
		long cartId = cart.getId();
		entityManager.flush();
		entityManager.clear();
		cart = cartRepo.findOne(cartId);
		assertThat(cart.getLineItems(), containsInAnyOrder(lineItem, anotherLineItem));
	}

	@Test
	public void shouldSaveLineItemsToSameCart() {
		lineItem = lineItemRepo.save(lineItem);
		anotherLineItem = lineItemRepo.save(anotherLineItem);
		entityManager.flush();
		entityManager.clear();
		assertThat(lineItem.getCart(), is(anotherLineItem.getCart()));
	}

	@Test
	public void shouldCalculateSumOfAllLineItemQuantities() {
		countedLineItem = lineItemRepo.save(countedLineItem);
		anotherCountedLineItem = lineItemRepo.save(anotherCountedLineItem);
		long cartId = cart.getId();
		entityManager.flush();
		entityManager.clear();
		cart = cartRepo.findOne(cartId);
		assertThat(cart.getCartQuantity(), is(3));
	}

	@Test
	public void shouldGetLineItemByProductId() {
		lineItem = lineItemRepo.save(lineItem);
		entityManager.flush();
		entityManager.clear();
		cart = cartRepo.findOne(cartId);
		LineItem actual = cart.getLineItemByProductId(productId);
		assertThat(actual, is(lineItem));
	}

	@Test
	public void shouldGetCountedLineItemByProductId() {
		countedLineItem = lineItemRepo.save(countedLineItem);
		entityManager.flush();
		entityManager.clear();
		cart = cartRepo.findOne(cartId);
		LineItem actual = cart.getLineItemByProductId(productId);
		assertThat(actual, is(countedLineItem));
	}

	@Test
	public void shouldReturnNullIfLineItemWithProductIdIsNotFound() {
		anotherLineItem = lineItemRepo.save(anotherLineItem);
		entityManager.flush();
		entityManager.clear();
		cart = cartRepo.findOne(cartId);
		LineItem actual = cart.getLineItemByProductId(productId);
		assertThat(actual, nullValue());
	}

	@Test
	public void shouldReturnLineItemQuantityByProductIdOne() {
		countedLineItem = lineItemRepo.save(countedLineItem);
		entityManager.flush();
		entityManager.clear();
		cart = cartRepo.findOne(cartId);
		int actual = cart.getLineItemQuantityByProductId(productId);
		assertThat(actual, is(countedLineItem.getQuantity()));
	}

	@Test
	public void shouldReturnLineItemQuantityByProductIdTwo() {
		long productId = anotherProduct.getId();
		anotherCountedLineItem = lineItemRepo.save(anotherCountedLineItem);
		entityManager.flush();
		entityManager.clear();
		cart = cartRepo.findOne(cartId);
		int actual = cart.getLineItemQuantityByProductId(productId);
		assertThat(actual, is(anotherCountedLineItem.getQuantity()));
	}

	@Test
	public void shouldIncreaseProductQuantityByOne() {
		countedLineItem = lineItemRepo.save(countedLineItem);
		int check = countedLineItem.getQuantity();
		entityManager.flush();
		entityManager.clear();
		cart = cartRepo.findOne(cartId);
		int actual = cart.increaseProductByOne(productId).getQuantity();
		assertThat(actual, is(check + 1));
	}

	@Test
	public void shouldDecreaseProductQuantityByOne() {
		countedLineItem = lineItemRepo.save(countedLineItem);
		int check = countedLineItem.getQuantity();
		entityManager.flush();
		entityManager.clear();
		cart = cartRepo.findOne(cartId);
		int actual = cart.decreaseProductByOne(productId).getQuantity();
		assertThat(actual, is(check - 1));
	}

	@Test
	public void shouldUpdateQuantityOfProduct() {
		countedLineItem = lineItemRepo.save(countedLineItem);
		entityManager.flush();
		entityManager.clear();
		cart = cartRepo.findOne(cartId);
		int check = 5;
		cart.updateQuantityOfProduct(productId, check);
		int actual = cart.getLineItemQuantityByProductId(productId);
		assertThat(actual, is(check));
	}

	@Test
	public void shouldNotFindLineItemIfRemoved() {
		lineItem = lineItemRepo.save(lineItem);
		entityManager.flush();
		entityManager.clear();
		cart = cartRepo.findOne(cartId);
		LineItem orphan = cart.popItemByProductId(productId);
		lineItemRepo.save(orphan);
		entityManager.flush();
		entityManager.clear();
		cart = cartRepo.findOne(cartId);
		LineItem actual = cart.getLineItemByProductId(productId);
		assertThat(actual, is(nullValue()));
	}

	@Test
	public void shouldNotFindAnyLineItemsIfAllAreRemoved() {
		lineItem = lineItemRepo.save(lineItem);
		anotherLineItem = lineItemRepo.save(anotherLineItem);
		entityManager.flush();
		entityManager.clear();
		cart = cartRepo.findOne(cartId);
		cart.removeAllItems();
		lineItem = cart.getLineItemByProductId(productId);
		anotherLineItem = cart.getLineItemByProductId(anotherProductId);
		assertThat(lineItem, is(nullValue()));
		assertThat(anotherLineItem, is(nullValue()));
	}

	@Test
	public void shouldNotHaveLineItem() {
		entityManager.flush();
		entityManager.clear();
		cart = cartRepo.findOne(cartId);
		boolean has = cart.has(productId);
		assertThat(has, is(false));
	}

	@Test
	public void shouldNotHaveLineItemWhenItsDeletedFromLineItemRepo() {
		lineItemRepo.save(countedLineItem);
		lineItemRepo.delete(countedLineItem);
		entityManager.flush();
		entityManager.clear();
		cart = cartRepo.findOne(cartId);
		boolean has = cart.has(productId);
		assertThat(has, is(false));
	}

	@Test
	public void shouldNotHaveLineItemWhenItDetachesItself() {
		lineItemRepo.save(countedLineItem);
		long countedLineItemId = countedLineItem.getId();
		entityManager.flush();
		entityManager.clear();
		countedLineItem = (CountedLineItem) lineItemRepo.findOne(countedLineItemId);
		countedLineItem.detachFromCart();
		lineItemRepo.save(countedLineItem);
		entityManager.flush();
		entityManager.clear();
		cart = cartRepo.findOne(cartId);
		boolean has = cart.has(productId);
		assertThat(has, is(false));
	}

	@Test
	public void shouldDetachLineItemFromCart() {
		lineItemRepo.save(countedLineItem);
		entityManager.flush();
		entityManager.clear();
		cart = cartRepo.findOne(cartId);
		CountedLineItem orphan = (CountedLineItem) cart.popItemByProductId(productId);
		lineItemRepo.save(orphan);
		entityManager.flush();
		entityManager.clear();
		cart = cartRepo.findOne(cartId);
		boolean has = cart.has(productId);
		assertThat(has, is(false));
	}

	@Test
	public void shouldRemoveLineItemIfQuantityIsSetToZero() {
		lineItemRepo.save(countedLineItem);
		entityManager.flush();
		entityManager.clear();
		cart = cartRepo.findOne(cartId);
		CountedLineItem orphan = cart.updateQuantityOfProduct(productId, 0);
		lineItemRepo.save(orphan);
		entityManager.flush();
		entityManager.clear();
		cart = cartRepo.findOne(cartId);
		boolean has = cart.has(productId);
		assertThat(has, is(false));
	}

	@Test
	public void shouldRemoveLineItemIfQuantityIsReducedToZero() {
		lineItemRepo.save(countedLineItem);
		entityManager.flush();
		entityManager.clear();
		cart = cartRepo.findOne(cartId);
		CountedLineItem orphan = cart.decreaseProductByOne(productId);
		lineItemRepo.save(orphan);
		entityManager.flush();
		entityManager.clear();
		cart = cartRepo.findOne(cartId);
		boolean has = cart.has(productId);
		assertThat(has, is(false));
	}

	@Test
	public void shouldNotRemoveLineItemIfQuantityIsReducedToOne() {
		lineItemRepo.save(anotherCountedLineItem);
		entityManager.flush();
		entityManager.clear();
		cart = cartRepo.findOne(cartId);
		CountedLineItem child = cart.decreaseProductByOne(anotherProductId);
		lineItemRepo.save(child);
		entityManager.flush();
		entityManager.clear();
		cart = cartRepo.findOne(cartId);
		boolean has = cart.has(anotherProductId);
		assertThat(has, is(true));
	}

	@Test
	public void shouldReturnTotalCouponsUsedInTheCartAs16() {
		PricedProduct couponProduct = new PricedProduct("", coupon, 8);
		couponProduct = productRepo.save(couponProduct);
		PricedProduct anotherCouponProduct = new PricedProduct("", coupon, 4);
		anotherCouponProduct = productRepo.save(anotherCouponProduct);
		CountedLineItem countedLineItem = new CountedLineItem(cart, couponProduct, 1);
		lineItemRepo.save(countedLineItem);
		CountedLineItem anotherCountedLineItem = new CountedLineItem(cart, anotherCouponProduct, 2);
		lineItemRepo.save(anotherCountedLineItem);
		entityManager.flush();
		entityManager.clear();
		cart = cartRepo.findOne(cartId);
		cart.refreshCouponsUsed();
		cartRepo.save(cart);
		entityManager.flush();
		entityManager.clear();
		cart = cartRepo.findOne(cartId);
		int actual = cart.getCouponsUsed();
		assertThat(actual, is(16));
	}

	@Test
	public void shouldReturnTotalCouponsUsedInTheCartAs24() {
		PricedProduct couponProduct = new PricedProduct("", coupon, 6);
		couponProduct = productRepo.save(couponProduct);
		PricedProduct anotherCouponProduct = new PricedProduct("", coupon, 3);
		anotherCouponProduct = productRepo.save(anotherCouponProduct);
		CountedLineItem countedLineItem = new CountedLineItem(cart, couponProduct, 2);
		lineItemRepo.save(countedLineItem);
		CountedLineItem anotherCountedLineItem = new CountedLineItem(cart, anotherCouponProduct, 4);
		lineItemRepo.save(anotherCountedLineItem);
		entityManager.flush();
		entityManager.clear();
		cart = cartRepo.findOne(cartId);
		cart.refreshCouponsUsed();
		cartRepo.save(cart);
		cart = cartRepo.findOne(cartId);
		int actual = cart.getCouponsUsed();
		assertThat(actual, is(24));
	}

	@Test
	public void shouldReturnTotalCouponsUsedEvenWhenNotACountedLineItem() {
		lineItemRepo.save(lineItem);
		PricedProduct couponProduct = new PricedProduct("", coupon, 6);
		couponProduct = productRepo.save(couponProduct);
		PricedProduct anotherCouponProduct = new PricedProduct("", coupon, 3);
		anotherCouponProduct = productRepo.save(anotherCouponProduct);
		CountedLineItem countedLineItem = new CountedLineItem(cart, couponProduct, 2);
		lineItemRepo.save(countedLineItem);
		CountedLineItem anotherCountedLineItem = new CountedLineItem(cart, anotherCouponProduct, 4);
		lineItemRepo.save(anotherCountedLineItem);
		entityManager.flush();
		entityManager.clear();
		cart = cartRepo.findOne(cartId);
		cart.refreshCouponsUsed();
		cartRepo.save(cart);
		entityManager.flush();
		entityManager.clear();
		cart = cartRepo.findOne(cartId);
		int actual = cart.getCouponsUsed();
		assertThat(actual, is(24));
	}

	@Test
	public void shouldNotIncreaseProductIfQuantityIsAtLimit() {
		int quantity = 2;
		PricedProduct couponProduct = new PricedProduct("", coupon, quantity, 0);
		couponProduct = productRepo.save(couponProduct);
		long couponProductId = couponProduct.getId();
		CountedLineItem countedLineItem = new CountedLineItem(cart, couponProduct, quantity);
		lineItemRepo.save(countedLineItem);
		entityManager.flush();
		entityManager.clear();
		cart = cartRepo.findOne(cartId);
		countedLineItem = cart.increaseProductByOne(couponProductId);
		int actual = countedLineItem.getQuantity();
		assertThat(actual, is(quantity));
	}

	@Test
	public void shouldHaveMeatPoundsUsedAsOne() {
		lineItemRepo.save(countedLineItem);
		entityManager.flush();
		entityManager.clear();
		cart = cartRepo.findOne(cartId);
		cart.refreshMeatPoundsUsed();
		int meatPoundsUsed = cart.getMeatPoundsUsed();
		assertThat(meatPoundsUsed, is(1));
	}

	@Test
	public void shouldHaveMeatPoundsUsedAsThree() {
		lineItemRepo.save(countedLineItem);
		lineItemRepo.save(anotherCountedLineItem);
		entityManager.flush();
		entityManager.clear();
		cart = cartRepo.findOne(cartId);
		cart.refreshMeatPoundsUsed();
		int meatPoundsUsed = cart.getMeatPoundsUsed();
		assertThat(meatPoundsUsed, is(3));
	}

	@Test
	public void shouldHaveMeatPoundsUsedAsThreeDespiteThereBeingCouponProducts() {
		lineItemRepo.save(countedLineItem);
		lineItemRepo.save(anotherCountedLineItem);
		PricedProduct couponProduct = new PricedProduct("", coupon, 2);
		couponProduct = productRepo.save(couponProduct);
		lineItemRepo.save(new CountedLineItem(cart, couponProduct, 2));
		entityManager.flush();
		entityManager.clear();
		cart = cartRepo.findOne(cartId);
		cart.refreshMeatPoundsUsed();
		int meatPoundsUsed = cart.getMeatPoundsUsed();
		assertThat(meatPoundsUsed, is(3));
	}

	@Test
	public void shouldHaveTheProductQuantityIncrease() {
		PricedProduct couponProduct = new PricedProduct("", coupon, 1);
		couponProduct = productRepo.save(couponProduct);
		long couponProductId = couponProduct.getId();
		int quantity = 1;
		countedLineItem = new CountedLineItem(cart, couponProduct, quantity);
		countedLineItem = lineItemRepo.save(countedLineItem);
		entityManager.flush();
		entityManager.clear();
		cart = cartRepo.findOne(cartId);
		cart.increaseProductByOne(couponProductId);
		int actual = cart.getLineItemQuantityByProductId(couponProductId);
		assertThat(actual, is(quantity + 1));
	}

	@Ignore
	@Test
	public void shouldHaveCartNotAddAnyMoreCouponsIfAlreadyBeyondLimit() {
		PricedProduct couponProduct = new PricedProduct("", coupon, 100);
		couponProduct = productRepo.save(couponProduct);
		long couponProductId = couponProduct.getId();
		int quantity = 1;
		countedLineItem = new CountedLineItem(cart, couponProduct, quantity);
		countedLineItem = lineItemRepo.save(countedLineItem);
		entityManager.flush();
		entityManager.clear();
		cart = cartRepo.findOne(cartId);
		cart.increaseProductByOne(couponProductId);
		int actual = cart.getLineItemQuantityByProductId(couponProductId);
		assertThat(actual, is(quantity));
	}

	@Test
	public void shouldHaveCartNotAddAnyMoreCouponsIfWouldPutCouponsJustPastLimit() {
		int cost = 3;
		PricedProduct couponProduct = new PricedProduct("", coupon, MAX_VALUE, cost);
		couponProduct = productRepo.save(couponProduct);
		long couponProductId = couponProduct.getId();
		int quantity = user.calculateCouponLimit() / cost;
		System.out.println(quantity);
		countedLineItem = new CountedLineItem(cart, couponProduct, quantity);
		countedLineItem = lineItemRepo.save(countedLineItem);
		entityManager.flush();
		entityManager.clear();
		System.out.println();
		cart = cartRepo.findOne(cartId);
		cart.increaseProductByOne(couponProductId);
		int actual = cart.getLineItemQuantityByProductId(couponProductId);
		assertThat(actual, is(quantity));
	}

	@Test
	public void shouldHaveCartAddOneMoreCouponIfThatWouldPutCouponsJustAtLimit() {
		int cost = 2;
		PricedProduct couponProduct = new PricedProduct("", coupon, MAX_VALUE, cost);
		couponProduct = productRepo.save(couponProduct);
		long couponProductId = couponProduct.getId();
		int quantity = user.calculateCouponLimit() / cost - 1;
		System.out.println(quantity);
		countedLineItem = new CountedLineItem(cart, couponProduct, quantity);
		countedLineItem = lineItemRepo.save(countedLineItem);
		entityManager.flush();
		entityManager.clear();
		System.out.println();
		cart = cartRepo.findOne(cartId);
		cart.increaseProductByOne(couponProductId);
		int actual = cart.getLineItemQuantityByProductId(couponProductId);
		assertThat(actual, is(quantity + 1));
	}

	@Ignore
	@Test
	public void shouldHaveLineItemWithCouponProductNotAttachToCartIfCartIsAtCouponLimit() {
		PricedProduct couponProduct = new PricedProduct("", coupon, MAX_VALUE, 20);
		couponProduct = productRepo.save(couponProduct);
		PricedProduct anotherCouponProduct = new PricedProduct("", coupon, MAX_VALUE, 1);
		anotherCouponProduct = productRepo.save(anotherCouponProduct);
		long anotherCouponProductId = anotherCouponProduct.getId();
		CountedLineItem countedLineItem = new CountedLineItem(cart, couponProduct, 1);
		countedLineItem = lineItemRepo.save(countedLineItem);
		CountedLineItem anotherCountedLineItem = new CountedLineItem(cart, anotherCouponProduct, 1);
		anotherCountedLineItem = lineItemRepo.save(anotherCountedLineItem);
		entityManager.flush();
		entityManager.clear();
		cart = cartRepo.findOne(cartId);
		boolean actual = cart.has(anotherCouponProductId);
		assertThat(actual, is(false));
	}

	@Test
	public void shouldHaveCountedLineItemWithMeatProductNotIncreaseQuantityIfAtCartMeatLimit() {
		entityManager.flush();
		entityManager.clear();
		cart = cartRepo.findOne(cartId);
		int quantity = cart.getUser().calculateMeatLimit();
		Category meat = new Category("Meat");
		meat = categoryRepo.save(meat);
		LimitedProduct chicken = new LimitedProduct("Chicken", meat, MAX_VALUE);
		chicken = productRepo.save(chicken);
		productId = chicken.getId();
		CountedLineItem chickenLineItem = new CountedLineItem(cart, chicken, quantity);
		chickenLineItem = lineItemRepo.save(chickenLineItem);
		entityManager.flush();
		entityManager.clear();
		cart = cartRepo.findOne(cartId);
		cart.increaseProductByOne(productId);
		cartRepo.save(cart);
		entityManager.flush();
		entityManager.clear();
		cart = cartRepo.findOne(cartId);
		int actual = cart.getLineItemQuantityByProductId(productId);
		assertThat(actual, is(quantity));
	}

	@Test
	public void shouldHaveCountedLineItemWithMeatProductIncreaseIfCartIsNoWhereNearLimit() {
		entityManager.flush();
		entityManager.clear();
		cart = cartRepo.findOne(cartId);
		int quantity = 1;
		Category meat = new Category("Meat");
		meat = categoryRepo.save(meat);
		LimitedProduct chicken = new LimitedProduct("Chicken", meat, MAX_VALUE);
		chicken = productRepo.save(chicken);
		productId = chicken.getId();
		CountedLineItem chickenLineItem = new CountedLineItem(cart, chicken, quantity);
		chickenLineItem = lineItemRepo.save(chickenLineItem);
		entityManager.flush();
		entityManager.clear();
		cart = cartRepo.findOne(cartId);
		cart.increaseProductByOne(productId);
		cartRepo.save(cart);
		entityManager.flush();
		entityManager.clear();
		cart = cartRepo.findOne(cartId);
		int actual = cart.getLineItemQuantityByProductId(productId);
		assertThat(actual, is(quantity + 1));
	}

	@Ignore
	@Test
	public void shouldPrintGoods() {
		Product product = new Product("Product", coupon);
		productRepo.save(product);
		Product anotherProduct = new Product("Another Product", coupon);
		productRepo.save(anotherProduct);
		LineItem lineItem = new LineItem(cart, product);
		lineItemRepo.save(lineItem);
		LineItem anotherLineItem = new LineItem(cart, anotherProduct);
		lineItemRepo.save(anotherLineItem);
		CountedLineItem countedLineItem = new CountedLineItem(cart, product, 5);
		lineItemRepo.save(countedLineItem);
		CountedLineItem anotherCountedLineItem = new CountedLineItem(cart, anotherProduct, 2);
		lineItemRepo.save(anotherCountedLineItem);
		entityManager.flush();
		entityManager.clear();
		cart = cartRepo.findOne(cartId);
		// String message = cart.print();
		// assertEquals("<table><tr><th>Product</th><th>Quantity</th></tr><tr><td>Product</td><td>Included</td></tr><tr><td>Another Product</td><td>Included</td></tr><tr><td>Product</td><td>5</td></tr><tr><td>Another Product</td><td>2</td></tr></table>", message);
	}
}
