package org.wecancodeit.pantryplus;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CartJpaTest {

	@Resource
	private TestEntityManager entityManager;

	@Resource
	private CartRepository cartRepo;

	@Test
	public void shouldSaveAndLoadCart() {
		Cart underTest = new Cart();
		underTest = cartRepo.save(underTest);
		long cartId = underTest.getId();

		entityManager.flush();
		entityManager.clear();

		underTest = cartRepo.findOne(cartId);

		assertThat(underTest.getId(), is(greaterThan(0L)));
	}

}
