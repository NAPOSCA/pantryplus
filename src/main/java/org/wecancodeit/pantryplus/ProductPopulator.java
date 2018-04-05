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
		Product threeInOne = productRepo.save(new Product("Three-in-one", personalHygiene));
		Product soap = productRepo.save(new Product("Soap", personalHygiene));
		Product shampoo = productRepo.save(new Product("Shampoo", personalHygiene));

		Category mealStarter = categoryRepo.save(new Category("Meal Starter"));
		Product recipeOfTheDay = productRepo.save(new Product("Recipe of the day", mealStarter));

		Category dairy = categoryRepo.save(new Category("Dairy"));
		Product milk = productRepo.save(new Product("Milk", dairy));
		Product cheese = productRepo.save(new Product("Cheese", dairy));
		Product ranch = productRepo.save(new Product("Ranch", dairy));

		Category produce = categoryRepo.save(new Category("Produce"));
		Product apple = productRepo.save(new Product("Apple", produce));
		Product strawberry = productRepo.save(new Product("Strawberry", produce));
		Product pear = productRepo.save(new Product("Pear", produce));

		Category coupon = categoryRepo.save(new Category("Coupon"));
		Product coupon1 = productRepo.save(new CouponProduct("This can is worth 2 points", coupon, 2));
		Product coupon2 = productRepo.save(new CouponProduct("This can is worth 1 point", coupon, 1));

		Category bread = categoryRepo.save(new Category("Bread"));
		Product whiteBread = productRepo.save(new Product("White Bread", bread));
		Product healthyBread = productRepo.save(new Product("Healthy Bread", bread));

		Category goodies = categoryRepo.save(new Category("Goodies"));
		Product candy = productRepo.save(new Product("Candy", goodies));
		Product cookie = productRepo.save(new Product("Cookie", goodies));

		Category meat = categoryRepo.save(new Category("Meat"));
		Product chicken = productRepo.save(new Product("Chicken", meat));
		Product beef = productRepo.save(new Product("Beef", meat));
	}

}
