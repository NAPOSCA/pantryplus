package org.wecancodeit.pantryplus;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PantryController {

	@Resource
	private CategoryRepository categoryRepo;
	
	@RequestMapping("/")
	public String displayMainPage(Model model) {
		model.addAttribute("categories", categoryRepo.findAll());
		return "index";
	}
	
}
