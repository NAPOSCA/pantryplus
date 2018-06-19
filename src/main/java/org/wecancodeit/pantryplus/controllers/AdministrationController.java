package org.wecancodeit.pantryplus.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.wecancodeit.pantryplus.category.Category;
import org.wecancodeit.pantryplus.category.CategoryRepository;

@Controller
public class AdministrationController {
	
	@Resource
	private CategoryRepository categoryRepo;

	@RequestMapping(value = "/admin/categories", method = GET)
	public String displayAdminCategoriesView(Model model) {
		Iterable<Category> categories = categoryRepo.findAll();
		model.addAttribute("categories", categories);
		return "administration/categories";
	}

	@RequestMapping(value = "/admin/categories", method = POST)
	public String receiveAPostRequestOnCategories(@RequestParam String categoryName) {
		categoryRepo.save(new Category(categoryName));
		return "redirect:/admin/categories";
	}
}
