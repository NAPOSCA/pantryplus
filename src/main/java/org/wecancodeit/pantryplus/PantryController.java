package org.wecancodeit.pantryplus;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class PantryController {

	public String displayUserForm(Model model) {
		return "user-form";
	}

	// @Resource
	// private CartRepository cartRepo;
	//
	// @Resource
	// private CategoryRepository categoryRepo;

	// @RequestMapping("/shopping")
	// public String displayMainPage(Model model) {
	// model.addAttribute("categories", categoryRepo.findAll());
	// model.addAttribute("cart", cartRepo.findOne(1L));
	// return "shopping";
	// }
	//
	// @RequestMapping("/cart")
	// public String displayCart(Model model) {
	// model.addAttribute("cart", cartRepo.findOne(1L));
	// return "cart";
	// }
	//
	// @RequestMapping("/")
	// public String displayUserForm(Model model) {
	// return "user-form";
	// }

}
