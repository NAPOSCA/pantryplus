package org.wecancodeit.pantryplus;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PantryController {

	@Resource
	private CategoryRepository categoryRepo;

	@Resource
	private CartRepository cartRepo;

	@RequestMapping("/index")
	public String displayMainPage(Model model) {
		model.addAttribute("categories", categoryRepo.findAll());
		return "index";
	}

	@RequestMapping("/cart")
	public String displayCart(Model model) {
		model.addAttribute("cart", cartRepo.findOne(1L));
		return "cart";
	}
}
