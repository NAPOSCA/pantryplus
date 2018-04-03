package org.wecancodeit.pantryplus;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController {

	@Resource
	private CategoryRepository categoryRepo;

	@RequestMapping("/index")
	public Iterable<Category> findAllCategories() {

		return categoryRepo.findAll();
	}

}
