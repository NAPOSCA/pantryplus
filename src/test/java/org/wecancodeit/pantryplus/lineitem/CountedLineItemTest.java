package org.wecancodeit.pantryplus.lineitem;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.wecancodeit.pantryplus.lineitem.CountedLineItem;
import org.wecancodeit.pantryplus.product.CouponProduct;
import org.wecancodeit.pantryplus.product.Product;

public class CountedLineItemTest {

	private Product product;
	private Product product2;
	private CountedLineItem lineItem;

	@Before
	public void setUp() {
		product = new Product("testFruit", null);
		product2 = new Product("testFruit2", null);
		lineItem = new CountedLineItem(null, product, 1);
	}

	@Test
	public void lineItemShouldHaveProduct() {
		assertThat(lineItem.getProduct().getName(), is("testFruit"));
	}

	@Test
	public void lineItemShouldHaveQuantity() {
		assertThat(lineItem.getQuantity(), is(1));
	}

	@Test
	public void shouldHaveEqualsReturnFalseIfNull() {
		assertThat(lineItem.equals(null), is(false));
	}

	@SuppressWarnings("unlikely-arg-type")
	@Test
	public void shouldHaveEqualsReturnFalseIFDifferentClass() {
		assertThat(lineItem.equals(product2), is(false));
	}

	@Test
	public void shouldCreateSingleElementLineItem() {
		Product product = new Product("product", null);
		CountedLineItem underTest = new CountedLineItem(null, product, 1);
		assertThat(underTest.getQuantity(), is(1));
	}

	@Test
	public void shouldCheckIfProductIsCouponProductTrue() {
		Product couponProduct = new CouponProduct("product", null, 1);
		CountedLineItem underTest = new CountedLineItem(null, couponProduct, 0);
		boolean actual = underTest.hasCouponProduct();
		assertThat(actual, is(true));
	}

	@Test
	public void shouldCheckIfProductIsNotCouponProduct() {
		Product product = new Product("product", null);
		CountedLineItem underTest = new CountedLineItem(null, product, 0);
		boolean actual = underTest.hasCouponProduct();
		assertThat(actual, is(false));
	}

	@Test
	public void shouldReturnZeroIfNotCouponProduct() {
		Product product = new Product("product", null);
		CountedLineItem underTest = new CountedLineItem(null, product, 0);
		int sum = underTest.totalCouponsUsed();
		assertThat(sum, is(0));
	}

	@Test
	public void shouldReturnSixIfTwoProductsOfThreePoints() {
		Product couponProduct = new CouponProduct("product", null, 3);
		CountedLineItem underTest = new CountedLineItem(null, couponProduct, 0);
		underTest.setQuantity(2);
		int sum = underTest.totalCouponsUsed();
		assertThat(sum, is(6));
	}

	@Test
	public void shouldReturnTwelveIfThreeProductsOfFourPoints() {
		Product product = new CouponProduct("product", null, 4);
		CountedLineItem underTest = new CountedLineItem(null, product, 0);
		underTest.setQuantity(3);
		int sum = underTest.totalCouponsUsed();
		assertThat(sum, is(12));
	}

	@Test
	public void shouldNotBeReducedToNegativeNumber() {
		CountedLineItem underTest = new CountedLineItem(null, null, 1);
		underTest.reduceQuantity(5);
		int actual = underTest.getQuantity();
		assertThat(actual, is(0));
	}

	@Test
	public void shouldNotSetQuantityToNegativeNumber() {
		CountedLineItem underTest = new CountedLineItem(null, null, 1);
		underTest.setQuantity(-5);
		int actual = underTest.getQuantity();
		assertThat(actual, is(0));
	}
	
	@Test
	public void shouldNotPassNegativeNumberToReduceQuantity() {
		CountedLineItem underTest = new CountedLineItem(null, null, 5);
		int check = underTest.getQuantity();
		underTest.reduceQuantity(-1);
		int actual = underTest.getQuantity();
		assertThat(actual, is(check));
	}
	
	@Test
	public void shouldNotPassNegativeNumberToIncreaseQuantity() {
		CountedLineItem underTest = new CountedLineItem(null, null, 5);
		int check = underTest.getQuantity();
		underTest.increaseQuantity(-1);
		int actual = underTest.getQuantity();
		assertThat(actual, is(check));
	}
}
