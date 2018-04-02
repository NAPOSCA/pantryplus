package org.wecancodeit.pantryplus;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProductJpaTest {
	
	@Resource
	private TestEntityManager entityManager;
	
	@Test
	public void shouldSaveAndLoadCategory() {
		Category underTest = new Category("Fruit");
		underTest = categoryRepo.save(underTest);
		long categoryId = underTest.getId();
		
		
		entityManager.flush();
		entityManager.clear();
		
		underTest = categoryRepo.findOne(categoryId);
		
		assertThat(underTest.getName(), is("Fruit"));
		
		
		
		
		
	}

}
