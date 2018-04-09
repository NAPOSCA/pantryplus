package org.wecancodeit.pantryplus.controllers;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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

	@Mock
	private User user;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void shouldHaveDisplayUserFormReturnUserForm() {
		String templateName = underTest.displayUserForm(model);
		assertThat(templateName, is("user-form"));
	}

	@Test
	public void shouldHaveDisplayShoppingReturnShopping() {
		String templateName = underTest.displayShopping(model);
		assertThat(templateName, is("shopping"));
	}

	@Test
	public void shouldHaveDisplayCartReturnCart() {
		String templateName = underTest.displayCart(model);
		assertThat(templateName, is("cart"));
	}

	@Test
	public void shouldHaveDisplayShoppingAddCategoriesAndCartAndUserToModel() {
		Iterable<Category> categories = asList(category, anotherCategory);
		when(categoryRepo.findAll()).thenReturn(categories);
		long cartId = 1L;
		when(cartRepo.findOne(cartId)).thenReturn(cart);
		long userId = 1L;
		when(userRepo.findOne(userId)).thenReturn(user);

		underTest.displayShopping(model);

		verify(model).addAttribute("categories", categories);
		verify(model).addAttribute("cart", cart);
		verify(model).addAttribute("user", user);
	}
}
