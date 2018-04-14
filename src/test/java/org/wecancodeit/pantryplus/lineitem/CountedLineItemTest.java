package org.wecancodeit.pantryplus.lineitem;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.wecancodeit.pantryplus.category.Category;
import org.wecancodeit.pantryplus.product.PricedProduct;
import org.wecancodeit.pantryplus.product.Product;

public class CountedLineItemTest {

	private Product product;
	private Product product2;
	private CountedLineItem lineItem;
	private Category category;

	@Before
	public void setUp() {
		category = new Category("");
		product = new Product("testFruit", category);
		product2 = new Product("testFruit2", category);
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
		Product product = new Product("product", category);
		CountedLineItem underTest = new CountedLineItem(null, product, 1);
		assertThat(underTest.getQuantity(), is(1));
	}

	@Ignore
	@Test
	public void shouldCheckIfProductIsCouponProductTrue() {
		Product couponProduct = new PricedProduct("product", null, 1);
		CountedLineItem underTest = new CountedLineItem(null, couponProduct, 0);
		boolean actual = underTest.hasCouponProduct();
		assertThat(actual, is(true));
	}

	@Test
	public void shouldCheckIfProductIsNotCouponProduct() {
		Product product = new Product("product", category);
		CountedLineItem underTest = new CountedLineItem(null, product, 0);
		boolean actual = underTest.hasCouponProduct();
		assertThat(actual, is(false));
	}

	@Test
	public void shouldReturnZeroIfNotCouponProduct() {
		Product product = new Product("product", category);
		CountedLineItem underTest = new CountedLineItem(null, product, 0);
		int sum = underTest.getCouponsUsed();
		assertThat(sum, is(0));
	}

	@Ignore
	@Test
	public void shouldReturnSixIfTwoProductsOfThreePoints() {
		Product couponProduct = new PricedProduct("product", null, 3);
		CountedLineItem underTest = new CountedLineItem(null, couponProduct, 0);
		underTest.setQuantity(2);
		int sum = underTest.getCouponsUsed();
		assertThat(sum, is(6));
	}

	@Ignore
	@Test
	public void shouldReturnTwelveIfThreeProductsOfFourPoints() {
		Product product = new PricedProduct("product", null, 4);
		CountedLineItem underTest = new CountedLineItem(null, product, 0);
		underTest.setQuantity(3);
		int sum = underTest.getCouponsUsed();
		assertThat(sum, is(12));
	}

	@Test
	public void shouldNotBeReducedToNegativeNumber() {
		CountedLineItem underTest = new CountedLineItem(null, product, 1);
		underTest.reduceQuantity(5);
		int actual = underTest.getQuantity();
		assertThat(actual, is(0));
	}

	@Test
	public void shouldNotSetQuantityToNegativeNumber() {
		CountedLineItem underTest = new CountedLineItem(null, product, 1);
		underTest.setQuantity(-5);
		int actual = underTest.getQuantity();
		assertThat(actual, is(0));
	}
	
	@Test
	public void shouldNotPassNegativeNumberToReduceQuantity() {
		CountedLineItem underTest = new CountedLineItem(null, product, 5);
		int check = underTest.getQuantity();
		underTest.reduceQuantity(-1);
		int actual = underTest.getQuantity();
		assertThat(actual, is(check));
	}
	
	@Test
	public void shouldNotPassNegativeNumberToIncreaseQuantity() {
		CountedLineItem underTest = new CountedLineItem(null, product, 5);
		int check = underTest.getQuantity();
		underTest.increaseQuantity(-1);
		int actual = underTest.getQuantity();
		assertThat(actual, is(check));
	}
}
