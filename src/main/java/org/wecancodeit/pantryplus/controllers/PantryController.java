package org.wecancodeit.pantryplus.controllers;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.wecancodeit.pantryplus.UserRepository;
import org.wecancodeit.pantryplus.cart.CartRepository;
import org.wecancodeit.pantryplus.category.CategoryRepository;

@Controller
public class PantryController {

	@Resource
	private CartRepository cartRepo;

	@Resource
	private CategoryRepository categoryRepo;

	@Resource
	private UserRepository userRepo;

	@RequestMapping("/")
	public String displayUserForm(Model model) {
		return "user-form";
	}

	@RequestMapping("/user-form")
	public String userFormProcessing(Model model) {
		return "redirect:/shopping";
	}

	@RequestMapping("/shopping")
	public String displayShopping(Model model) {
		model.addAttribute("categories", categoryRepo.findAll());
		model.addAttribute("cart", cartRepo.findOne(1L));
		model.addAttribute("user", userRepo.findOne(1L));
		return "shopping";
	}

	@RequestMapping("/cart")
	public String displayCart(Model model) {
		return "cart";
	}

}
