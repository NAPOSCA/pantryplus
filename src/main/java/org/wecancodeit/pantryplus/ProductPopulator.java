package org.wecancodeit.pantryplus;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ProductPopulator implements CommandLineRunner {

	@Resource
	CartRepository cartRepo;

	@Resource
	CategoryRepository categoryRepo;

	@Resource
	ProductRepository productRepo;

	@SuppressWarnings("unused")
	@Override
	public void run(String... args) throws Exception {
		Cart cart = cartRepo.save(new Cart());

		Category personalHygiene = categoryRepo.save(new Category("Personal Hygiene"));
		Product bathroomTissue = productRepo.save(new Product("Bathroom Tissue", personalHygiene, "/images/bathroom-tissue.png"));
		

		Category mealStarter = categoryRepo.save(new Category("Meal Starter"));
		Product recipeOfTheDay = productRepo.save(new Product("Recipe of the Month", mealStarter, "/images/recipe.png"));

		Category dairy = categoryRepo.save(new Category("Dairy"));
		Product milk = productRepo.save(new Product("Milk", dairy, "/images/milk.png"));
		Product cheese = productRepo.save(new Product("Eggs", dairy, "/images/eggs.png"));

		Category produce = categoryRepo.save(new Category("Produce"));
		Product apple = productRepo.save(new Product("Seasonal Vegetables", produce, "/images/produce.png"));
		
		Category bread = categoryRepo.save(new Category("Bakery Item"));
		Product whiteBread = productRepo.save(new Product("Bread", bread, "/images/bread.png"));
		
		Category coupon = categoryRepo.save(new Category("Coupon"));
		Product coupon1 = productRepo.save(new Product("I think this is a can or something", coupon));
		Product coupon2 = productRepo.save(new Product("This is a new can", coupon));

		

		Category goodies = categoryRepo.save(new Category("Goodies"));
		Product candy = productRepo.save(new Product("Candy", goodies));
		Product cookie = productRepo.save(new Product("Cookie", goodies));

		Category meat = categoryRepo.save(new Category("Meat"));
		Product chicken = productRepo.save(new Product("Chicken", meat));
		Product beef = productRepo.save(new Product("Beef", meat));
	}

}
