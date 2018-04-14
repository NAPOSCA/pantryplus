package org.wecancodeit.pantryplus.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.wecancodeit.pantryplus.cart.Cart;
import org.wecancodeit.pantryplus.cart.CartRepository;
import org.wecancodeit.pantryplus.category.CategoryRepository;
import org.wecancodeit.pantryplus.lineitem.LineItemRepository;
import org.wecancodeit.pantryplus.user.User;
import org.wecancodeit.pantryplus.user.UserRepository;

@RunWith(SpringRunner.class)
@WebMvcTest(PantryController.class)
public class PantryControllerMockMvcTest {

	@Resource
	private MockMvc mvc;

	@MockBean
	private UserRepository userRepo;

	@MockBean
	private CartRepository cartRepo;

	@MockBean
	private CategoryRepository categoryRepo;

	@MockBean
	private LineItemRepository lineItemRepo;

	@Mock
	Cart cart;

	@Mock
	User user;

	long cartId = 1L;

	@Before
	public void setup() {
		when(cartRepo.findOne(cartId)).thenReturn(cart);
		when(userRepo.save(user)).thenReturn(user);
		when(cart.getUser()).thenReturn(user);
	}

	@Test
	public void shouldLoadIndexOk() throws Exception {
		mvc.perform(get("/")).andExpect(status().isOk());
	}

	@Ignore
	@Test
	public void shouldRedirectFromUserFormToShoppingView() throws Exception {
		mvc.perform(get("/user-form?familySize=1&schoolkidsCount=1&infants=false&pickUpDate=2018-04-08&zipCode=00000"))
				.andExpect(status().is3xxRedirection());
	}

	@Test
	public void shouldLoadCartOk() throws Exception {
		String path = "/carts/"+cartId;
		System.out.println(path);
		mvc.perform(get(path)).andExpect(status().isOk());
	}
}
