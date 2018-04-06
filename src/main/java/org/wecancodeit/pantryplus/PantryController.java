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
