package org.wecancodeit.pantryplus.controllers;

import javax.annotation.Resource;

import org.springframework.ui.Model;
import org.wecancodeit.pantryplus.category.Category;
import org.wecancodeit.pantryplus.category.CategoryRepository;

public class AdministrationController {
	
	@Resource
	private CategoryRepository categoryRepo;

	public String displayAdminView(Model model) {
		Iterable<Category> categories = categoryRepo.findAll();
		model.addAttribute("categories", categories);
		return "admin";
	}
}
