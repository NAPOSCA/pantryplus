package org.wecancodeit.pantryplus;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.wecancodeit.pantryplus.category.Category;
import org.wecancodeit.pantryplus.category.CategoryRepository;
import org.wecancodeit.pantryplus.product.CouponProduct;
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
		Product diapers = productRepo.save(new Product("Diapers", personalHygiene, "/items/diapers.png"));

		Category mealStarter = categoryRepo.save(new Category("Meal Starter"));
		Product recipeOfTheDay = productRepo
				.save(new Product("Recipe of the Month", mealStarter, "/images/recipe.png"));

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
		Product chicken = productRepo.save(new Product("Ground Chicken", meat, "/images/chicken.png"));
		Product turkey = productRepo.save(new Product("Ground Turkey", meat, "/images/turkey.png"));
		Product beef = productRepo.save(new Product("Ground Beef", meat, "/images/beef.png"));

		Category coupon = categoryRepo.save(new Category("Coupon"));
		Product coupon6 = productRepo.save(new CouponProduct("Canned Applesauce", coupon, 1, 5));
		Product coupon20 = productRepo.save(new CouponProduct("Canned Beef Stew", coupon, 1, 5));
		Product coupon24 = productRepo.save(new CouponProduct("Canned Black Beans", coupon, 1, 5));
		Product coupon2 = productRepo.save(new CouponProduct("Canned Chicken", coupon, 2, 5));
		Product coupon8 = productRepo.save(new CouponProduct("Canned Corn", coupon, 1, 5));
		Product coupon23 = productRepo.save(new CouponProduct("Canned Great Northern Beans", coupon, 1, 5));
		Product coupon9 = productRepo.save(new CouponProduct("Canned Green Beans", coupon, 1, 5));
		Product coupon22 = productRepo.save(new CouponProduct("Canned Kidney Beans", coupon, 1, 5));
		Product coupon19 = productRepo.save(new CouponProduct("Canned Pasta", coupon, 1, 5));
		Product coupon4 = productRepo.save(new CouponProduct("Canned Peaches", coupon, 1, 5));
		Product coupon5 = productRepo.save(new CouponProduct("Canned Pears", coupon, 1, 5));
		Product coupon21 = productRepo.save(new CouponProduct("Canned Spaghetti Sauce", coupon, 1, 5));
		Product coupon10 = productRepo.save(new CouponProduct("Canned Spinach", coupon, 1, 5));
		Product coupon27 = productRepo.save(new CouponProduct("Canned Tuna", coupon, 1, 5));
		Product coupon17 = productRepo.save(new CouponProduct("Chicken Rice Soup", coupon, 1, 5));
		Product coupon18 = productRepo.save(new CouponProduct("Chicken Noodle Soup", coupon, 1, 5));
		Product coupon12 = productRepo.save(new CouponProduct("Corn Flakes", coupon, 1, 5));
		Product coupon11 = productRepo.save(new CouponProduct("Dehydrated Potatoes", coupon, 1, 5));
		Product coupon25 = productRepo.save(new CouponProduct("Dry Pinto Beans", coupon, 1, 5));
		Product coupon26 = productRepo.save(new CouponProduct("Dry Kidney Beans", coupon, 1, 5));
		Product coupon15 = productRepo.save(new CouponProduct("Egg Noodles", coupon, 1, 5));
		Product coupon13 = productRepo.save(new CouponProduct("Grain O's Cereal", coupon, 1, 5));
		Product coupon1 = productRepo.save(new CouponProduct("Juice", coupon, 3, 1));
		Product coupon7 = productRepo.save(new CouponProduct("Mandarin Oranges", coupon, 1, 5));
		Product coupon3 = productRepo.save(new CouponProduct("Peanut Butter", coupon, 2, 5));
		Product coupon16 = productRepo.save(new CouponProduct("Rice", coupon, 1, 5));
		Product coupon14 = productRepo.save(new CouponProduct("Spaghetti", coupon, 1, 5));
		Product coupon28 = productRepo.save(new CouponProduct("Vegetable Oil", coupon, 1, 1));
	}

}
