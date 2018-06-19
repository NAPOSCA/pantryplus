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

	@Override
	public void run(String... args) throws Exception {

		Category hygiene = categoryRepo.save(new Category("Personal Hygiene"));
		productRepo
				.save(new Product("Bathroom Tissue", hygiene, "/images/bathroom-tissue.png"));
		productRepo.save(new Product("Diapers", hygiene, "/images/diaper.png"));

		Category mealStarter = categoryRepo.save(new Category("Meal Starter"));
		productRepo.save(new Product("Sausage Pasta", mealStarter, "/images/recipe.png"));

		Category dairy = categoryRepo.save(new Category("Dairy"));
		productRepo.save(new Product("Milk", dairy, "/images/milk.png"));
		productRepo.save(new Product("Eggs", dairy, "/images/eggs.png"));

		Category produce = categoryRepo.save(new Category("Produce"));
		productRepo.save(new Product("Seasonal Produce", produce, "/images/produce.png"));

		Category bread = categoryRepo.save(new Category("Bakery Item"));
		productRepo.save(new Product("Bread", bread, "/images/bread.png"));

		Category snacks = categoryRepo.save(new Category("After School Snacks"));
		productRepo.save(new Product("Yogurt", snacks, "/images/yogurt.png"));
		productRepo
				.save(new Product("Cheese Stick", snacks, "/images/string-cheese.png"));
		productRepo
				.save(new Product("Peanut Butter", snacks, "/images/peanut-butter.png"));
		productRepo.save(new Product("Jelly", snacks, "/images/jelly.png"));
		productRepo.save(new Product("Canned Goods", snacks, "/images/canned-food.png"));
		productRepo.save(new Product("Fruit", snacks, "/images/oranges-cartoon.png"));
		productRepo.save(new Product("Snacks", snacks, "/images/applesauce-cartoon.png"));

		Category meat = categoryRepo.save(new Category("Meat"));
		productRepo.save(new LimitedProduct("Ground Chicken", meat, "/images/chicken.png", 8));
		productRepo.save(new LimitedProduct("Ground Turkey", meat, "/images/turkey.png", 8));
		productRepo.save(new LimitedProduct("Ground Beef", meat, "/images/beef.png", 2));

		Category coupon = categoryRepo.save(new Category("Choice Items"));
		productRepo.save(new PricedProduct("Canned Applesauce", coupon, 5, 1));
		productRepo.save(new PricedProduct("Canned Beef Stew", coupon, 5, 1));
		productRepo.save(new PricedProduct("Canned Black Beans", coupon, 5, 1));
		productRepo.save(new PricedProduct("Canned Chicken", coupon, 5, 2));
		productRepo.save(new PricedProduct("Canned Corn", coupon, 5, 1));
		productRepo.save(new PricedProduct("Canned Great Northern Beans", coupon, 5, 1));
		productRepo.save(new PricedProduct("Canned Green Beans", coupon, 5, 1));
		productRepo.save(new PricedProduct("Canned Kidney Beans", coupon, 5, 1));
		productRepo.save(new PricedProduct("Canned Pasta", coupon, 5, 1));
		productRepo.save(new PricedProduct("Canned Peaches", coupon, 5, 1));
		productRepo.save(new PricedProduct("Canned Pears", coupon, 5, 1));
		productRepo.save(new PricedProduct("Canned Spaghetti Sauce", coupon, 5, 1));
		productRepo.save(new PricedProduct("Canned Spinach", coupon, 5, 1));
		productRepo.save(new PricedProduct("Canned Tuna", coupon, 5, 1));
		productRepo.save(new PricedProduct("Chicken Rice Soup", coupon, 5, 1));
		productRepo.save(new PricedProduct("Chicken Noodle Soup", coupon, 5, 1));
		productRepo.save(new PricedProduct("Corn Flakes", coupon, 5, 1));
		productRepo.save(new PricedProduct("Dehydrated Potatoes", coupon, 5, 1));
		productRepo.save(new PricedProduct("Dry Pinto Beans", coupon, 5, 1));
		productRepo.save(new PricedProduct("Dry Kidney Beans", coupon, 5, 1));
		productRepo.save(new PricedProduct("Egg Noodles", coupon, 5, 1));
		productRepo.save(new PricedProduct("Grain O's Cereal", coupon, 5, 1));
		productRepo.save(new PricedProduct("Juice", coupon, 1, 3));
		productRepo.save(new PricedProduct("Mandarin Oranges", coupon, 5, 1));
		productRepo.save(new PricedProduct("Peanut Butter", coupon, 5, 2));
		productRepo.save(new PricedProduct("Rice", coupon, 5, 1));
		productRepo.save(new PricedProduct("Spaghetti", coupon, 5, 1));
		productRepo.save(new PricedProduct("Vegetable Oil", coupon, 1, 1));
	}

}
