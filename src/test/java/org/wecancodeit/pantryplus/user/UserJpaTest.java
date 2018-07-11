package org.wecancodeit.pantryplus.user;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import org.wecancodeit.pantryplus.cart.CartRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserJpaTest {

	@Resource
	private TestEntityManager entityManager;

	@Resource
	private CartRepository cartRepo;

	@Resource
	private UserRepository userRepo;

	@Test
	public void shouldSaveAndLoadUser() {
		PantryUser user = new PantryUser("firstName", "lastName", 1, 1, true, "1954-02-15", "00000", "1234 Main St", "January 1st, 1969");
		user = userRepo.save(user);
		long userId = user.getId();

		entityManager.flush();
		entityManager.clear();

		user = userRepo.findOne(userId);
		assertThat(user.getId(), is(greaterThan(0L)));
	}
}
