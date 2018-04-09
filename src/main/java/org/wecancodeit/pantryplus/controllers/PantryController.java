package org.wecancodeit.pantryplus.controllers;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.wecancodeit.pantryplus.cart.Cart;
import org.wecancodeit.pantryplus.cart.CartRepository;
import org.wecancodeit.pantryplus.category.CategoryRepository;
import org.wecancodeit.pantryplus.user.User;
import org.wecancodeit.pantryplus.user.UserRepository;

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
	public String userFormProcessing(@RequestParam int familySize, @RequestParam int schoolkidsCount, @RequestParam boolean infants,
			@RequestParam String pickUpDate, @RequestParam String zipCode) {
		User user = new User(familySize, schoolkidsCount, infants, pickUpDate, zipCode);
		user = userRepo.save(user);
		Cart cart = cartRepo.save(new Cart(user));
		long cartId = cart.getId();
		return "redirect:/shopping?cartId=" + cartId;
	}

	@RequestMapping("/shopping")
	public String displayShopping(Model model, @RequestParam long cartId) {
		model.addAttribute("categories", categoryRepo.findAll());
		model.addAttribute("cart", cartRepo.findOne(cartId));
		return "shopping";
	}

	@RequestMapping("/cart")
	public String displayCart(Model model) {
		return "cart";
	}

}
