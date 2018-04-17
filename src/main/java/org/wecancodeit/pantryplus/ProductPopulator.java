package org.wecancodeit.pantryplus;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.wecancodeit.pantryplus.category.Category;
import org.wecancodeit.pantryplus.category.CategoryRepository;
import org.wecancodeit.pantryplus.product.LimitedProduct;
import org.wecancodeit.pantryplus.product.PricedProduct;
import org.wecancodeit.pantryplus.product.Product;
import org.wecancodeit.pantryplus.product.ProductRepository;

@Component
public class ProductPopulator implements CommandLineRunner {

	@Resource
	CategoryRepository categoryRepo;

	@Resource
	ProductRepository productRepo;

	@SuppressWarnings("unused")
	@Override
	public void run(String... args) throws Exception {

		Category personalHygiene = categoryRepo.save(new Category("Personal Hygiene"));
		Product bathroomTissue = productRepo
				.save(new Product("Bathroom Tissue", personalHygiene, "/images/bathroom-tissue.png"));
		Product diapers = productRepo.save(new Product("Diapers", personalHygiene, "/images/diaper.png"));

		Category mealStarter = categoryRepo.save(new Category("Meal Starter"));
		Product recipeOfTheDay = productRepo.save(new Product("Sausage Pasta", mealStarter, "/images/recipe.png"));

		Category dairy = categoryRepo.save(new Category("Dairy"));
		Product milk = productRepo.save(new Product("Milk", dairy, "/images/milk.png"));
		Product cheese = productRepo.save(new Product("Eggs", dairy, "/images/eggs.png"));

		Category produce = categoryRepo.save(new Category("Produce"));
		Product apple = productRepo.save(new Product("Seasonal Vegetables", produce, "/images/produce.png"));

		Category bread = categoryRepo.save(new Category("Bakery Item"));
		Product whiteBread = productRepo.save(new Product("Bread", bread, "/images/bread.png"));

		Category afterSchoolSnacks = categoryRepo.save(new Category("After School Snacks"));
		Product yogurt = productRepo.save(new Product("Yogurt", afterSchoolSnacks, "/images/yogurt.png"));
		Product cheeseStick = productRepo
				.save(new Product("Cheese Stick", afterSchoolSnacks, "/images/string-cheese.png"));
		Product peanutButter = productRepo
				.save(new Product("Peanut Butter", afterSchoolSnacks, "/images/peanut-butter.png"));
		Product jelly = productRepo.save(new Product("Jelly", afterSchoolSnacks, "/images/jelly.png"));
		Product cans = productRepo.save(new Product("Canned Goods", afterSchoolSnacks, "/images/canned-food.png"));
		Product fruit = productRepo.save(new Product("Fruit", afterSchoolSnacks, "/images/oranges-cartoon.png"));
		Product snacks = productRepo.save(new Product("Snacks", afterSchoolSnacks, "/images/applesauce-cartoon.png"));

		Category meat = categoryRepo.save(new Category("Meat"));
		Product chicken = productRepo.save(new LimitedProduct("Ground Chicken", meat, "/images/chicken.png", 8));
		Product turkey = productRepo.save(new LimitedProduct("Ground Turkey", meat, "/images/turkey.png", 8));
		Product beef = productRepo.save(new LimitedProduct("Ground Beef", meat, "/images/beef.png", 2));

		Category coupon = categoryRepo.save(new Category("Coupon"));
		Product coupon6 = productRepo.save(new PricedProduct("Canned Applesauce", coupon, 5, 1));
		Product coupon20 = productRepo.save(new PricedProduct("Canned Beef Stew", coupon, 5, 1));
		Product coupon24 = productRepo.save(new PricedProduct("Canned Black Beans", coupon, 5, 1));
		Product coupon2 = productRepo.save(new PricedProduct("Canned Chicken", coupon, 5, 2));
		Product coupon8 = productRepo.save(new PricedProduct("Canned Corn", coupon, 5, 1));
		Product coupon23 = productRepo.save(new PricedProduct("Canned Great Northern Beans", coupon, 5, 1));
		Product coupon9 = productRepo.save(new PricedProduct("Canned Green Beans", coupon, 5, 1));
		Product coupon22 = productRepo.save(new PricedProduct("Canned Kidney Beans", coupon, 5, 1));
		Product coupon19 = productRepo.save(new PricedProduct("Canned Pasta", coupon, 5, 1));
		Product coupon4 = productRepo.save(new PricedProduct("Canned Peaches", coupon, 5, 1));
		Product coupon5 = productRepo.save(new PricedProduct("Canned Pears", coupon, 5, 1));
		Product coupon21 = productRepo.save(new PricedProduct("Canned Spaghetti Sauce", coupon, 5, 1));
		Product coupon10 = productRepo.save(new PricedProduct("Canned Spinach", coupon, 5, 1));
		Product coupon27 = productRepo.save(new PricedProduct("Canned Tuna", coupon, 5, 1));
		Product coupon17 = productRepo.save(new PricedProduct("Chicken Rice Soup", coupon, 5, 1));
		Product coupon18 = productRepo.save(new PricedProduct("Chicken Noodle Soup", coupon, 5, 1));
		Product coupon12 = productRepo.save(new PricedProduct("Corn Flakes", coupon, 5, 1));
		Product coupon11 = productRepo.save(new PricedProduct("Dehydrated Potatoes", coupon, 5, 1));
		Product coupon25 = productRepo.save(new PricedProduct("Dry Pinto Beans", coupon, 5, 1));
		Product coupon26 = productRepo.save(new PricedProduct("Dry Kidney Beans", coupon, 5, 1));
		Product coupon15 = productRepo.save(new PricedProduct("Egg Noodles", coupon, 5, 1));
		Product coupon13 = productRepo.save(new PricedProduct("Grain O's Cereal", coupon, 5, 1));
		Product coupon1 = productRepo.save(new PricedProduct("Juice", coupon, 1, 3));
		Product coupon7 = productRepo.save(new PricedProduct("Mandarin Oranges", coupon, 5, 1));
		Product coupon3 = productRepo.save(new PricedProduct("Peanut Butter", coupon, 5, 2));
		Product coupon16 = productRepo.save(new PricedProduct("Rice", coupon, 5, 1));
		Product coupon14 = productRepo.save(new PricedProduct("Spaghetti", coupon, 5, 1));
		Product coupon28 = productRepo.save(new PricedProduct("Vegetable Oil", coupon, 1, 1));
	}

}
