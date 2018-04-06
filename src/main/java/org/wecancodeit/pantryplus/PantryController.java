package org.wecancodeit.pantryplus;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class PantryController {

	@Resource
	private CartRepository cartRepo;

	@Resource
	private CategoryRepository categoryRepo;

	@Resource
	private UserRepository userRepo;

	public String displayUserForm(Model model) {
		return "user-form";
	}

	public String displayShopping(Model model) {
		model.addAttribute("categories", categoryRepo.findAll());
		model.addAttribute("cart", cartRepo.findOne(1L));
		model.addAttribute("user", userRepo.findOne(1L));
		return "shopping";
	}

	public String displayCart(Model model) {
		return "cart";
	}

}
