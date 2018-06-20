package org.wecancodeit.pantryplus.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.wecancodeit.pantryplus.category.Category;
import org.wecancodeit.pantryplus.category.CategoryRepository;
import org.wecancodeit.pantryplus.product.LimitedProduct;
import org.wecancodeit.pantryplus.product.PricedProduct;
import org.wecancodeit.pantryplus.product.Product;
import org.wecancodeit.pantryplus.product.ProductRepository;

@Controller
public class AdministrationController {

	@Resource
	private CategoryRepository categoryRepo;

	@Resource
	private ProductRepository productRepo;

	@Resource
	private EntityManager entityManager;

	@RequestMapping(value = "/admin/categories", method = GET)
	public String displayAdminCategoriesView(Model model) {
		Iterable<Category> categories = categoryRepo.findAll();
		model.addAttribute("categories", categories);
		return "admin/categories";
	}

	@RequestMapping(value = "/admin/categories/{categoryId}", method = GET)
	public String displayAdminCategoryView(Model model, @PathVariable Long categoryId) {
		Category category = categoryRepo.findOne(categoryId);
		model.addAttribute("category", category);
		return "admin/category";
	}

	@RequestMapping(value = "/admin/categories/{categoryId}/products/{productId}", method = GET)
	public String displayAdminProductView(Model model, @PathVariable Long categoryId, @PathVariable Long productId) {
		Category category = categoryRepo.findOne(categoryId);
		model.addAttribute("category", category);
		Product product = productRepo.findOne(productId);
		model.addAttribute("product", product);
		if (product.getClass().equals(PricedProduct.class)) {
			return "admin/priced-product";
		}
		if (product.getClass().equals(LimitedProduct.class)) {
			return "admin/limited-product";
		}
		return "admin/product";
	}

	@RequestMapping(value = "/admin/categories", method = POST)
	public String receiveAPostRequestOnCategories(@RequestParam String categoryName) {
		categoryRepo.save(new Category(categoryName));
		return "redirect:/admin/categories";
	}

	@Transactional
	@RequestMapping(value = "/admin/categories/{categoryId}/products", method = POST)
	public String receiveAPostRequestOnACategorysProducts(Model model, @PathVariable Long categoryId,
			@RequestParam String type, @RequestParam String productName, @RequestParam(required = false) String image,
			@RequestParam(required = false) int maximumQuantity, @RequestParam(required = false) int price) {
		Category category = categoryRepo.findOne(categoryId);
		if (type == "Product") {
			Product product = new Product(productName, category, image);
			productRepo.save(product);
		} else if (type == "LimitedProduct") {
			LimitedProduct product = new LimitedProduct(productName, category, image, maximumQuantity);
			productRepo.save(product);
		} else if (type == "PricedProduct") {
			PricedProduct product = new PricedProduct(productName, category, image, maximumQuantity, price);
			productRepo.save(product);
		}
		entityManager.flush();
		entityManager.clear();
		category = categoryRepo.findOne(categoryId);
		model.addAttribute("category", category);
		return "redirect:/admin/categories/" + categoryId;
	}
}
