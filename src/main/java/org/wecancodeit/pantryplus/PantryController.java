package org.wecancodeit.pantryplus;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PantryController {
	
	@Resource
	private CartRepository cartRepo;

	@Resource
	private CategoryRepository categoryRepo;
	
	@RequestMapping("/")
	public String displayMainPage(Model model) {
		model.addAttribute("categories", categoryRepo.findAll());
		model.addAttribute("cart", cartRepo.findOne(1L));
		return "index";
	}
	
}
