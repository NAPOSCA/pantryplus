package org.wecancodeit.pantryplus.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.wecancodeit.pantryplus.category.Category;
import org.wecancodeit.pantryplus.category.CategoryRepository;

@Controller
public class AdministrationController {
	
	@Resource
	private CategoryRepository categoryRepo;

	@RequestMapping("/admin/categories")
	public String displayAdminCategoriesView(Model model) {
		Iterable<Category> categories = categoryRepo.findAll();
		model.addAttribute("categories", categories);
		return "administration/categories";
	}

	@RequestMapping(value = "/admin/categories", method = POST)
	public String receiveAPostRequestOnCategories(String categoryName) {
		categoryRepo.save(new Category(categoryName));
		return "administration/categories";
	}
}
