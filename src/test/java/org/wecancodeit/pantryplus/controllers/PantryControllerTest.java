package org.wecancodeit.pantryplus.controllers;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.wecancodeit.pantryplus.cart.Cart;
import org.wecancodeit.pantryplus.cart.CartRepository;
import org.wecancodeit.pantryplus.category.Category;
import org.wecancodeit.pantryplus.category.CategoryRepository;
import org.wecancodeit.pantryplus.lineitem.CountedLineItem;
import org.wecancodeit.pantryplus.lineitem.LineItem;
import org.wecancodeit.pantryplus.user.User;
import org.wecancodeit.pantryplus.user.UserRepository;

public class PantryControllerTest {

	@InjectMocks
	private PantryController underTest;

	@Mock
	private Model model;

	@Mock
	private CategoryRepository categoryRepo;

	@Mock
	private CartRepository cartRepo;

	@Mock
	private UserRepository userRepo;

	@Mock
	private Category category;

	@Mock
	private Category anotherCategory;

	@Mock
	private Cart cart;
	private Long cartId = 1L;

	@Mock
	private User user;
	
	@Mock
	private LineItem lineItem;
	
	@Mock
	private CountedLineItem countedLineItem;


	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		Set<LineItem> lineItems = new HashSet<>();
		lineItems.add(lineItem);
		lineItems.add(countedLineItem);
		when(cart.getLineItems()).thenReturn(lineItems);
		when(cartRepo.findOne(cartId)).thenReturn(cart);
	}

	@Test
	public void shouldHaveDisplayUserFormReturnUserForm() {
		String templateName = underTest.displayUserForm(model);
		assertThat(templateName, is("user-form"));
	}

	@Test
	public void shouldHaveDisplayShoppingReturnShopping() {
		long cartId = 1L;
		String templateName = underTest.displayShopping(model, cartId);
		assertThat(templateName, is("shopping"));
	}

	@Test
	public void shouldHaveDisplayCartReturnCart() {
		String templateName = underTest.displayCart(model, cartId);
		assertThat(templateName, is("cart"));
	}

	@Test
	public void shouldHaveDisplayShoppingAddCategoriesAndCartAndUserToModel() {
		Iterable<Category> categories = asList(category, anotherCategory);
		when(categoryRepo.findAll()).thenReturn(categories);
		long cartId = 1L;
		when(cartRepo.findOne(cartId)).thenReturn(cart);
		underTest.displayShopping(model, cartId);
		verify(model).addAttribute("categories", categories);
		verify(model).addAttribute("cart", cart);
	}
	
	@Test
	public void shouldReturnInvalidZipCodeViewWhenGivenOtherAsZipCode() {
		PantryController underTest = new PantryController();
		String result = underTest.userFormProcessing(null, null, 0, 0, false, null, "Other", null, null);
		assertThat(result, is("redirect:/invalid-zipcode"));
	}
	
	@Test
	public void shouldReturnInvalidZipCodeView() {
		String templateName = underTest.displayIncorrectZipcode();
		assertThat(templateName, is("invalid-zipcode"));
	}
	
}
