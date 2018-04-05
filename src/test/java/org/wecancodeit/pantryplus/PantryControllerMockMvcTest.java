package org.wecancodeit.pantryplus;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(PantryController.class)
public class PantryControllerMockMvcTest {
	
	@Resource
	private MockMvc mvc;
	
	@MockBean
	private CartRepository cartRepo;
	
	@MockBean
	private CategoryRepository categoryRepo;
	
	@Mock
	Cart cart;
	
	long cartId = 1L;
	
	@Before
	public void setup() {
		when(cartRepo.findOne(cartId)).thenReturn(cart);
	}
	
	@Test
	public void shouldLoadIndexOk() throws Exception {
		mvc.perform(get("/")).andExpect(status().isOk());
	}
	
	@Test
	public void shouldLoadCartOk() throws Exception {
		mvc.perform(get("/cart")).andExpect(status().isOk());
	}
}
