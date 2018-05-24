package org.wecancodeit.pantryplus.controllers;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.wecancodeit.pantryplus.cart.Cart;
import org.wecancodeit.pantryplus.cart.CartRepository;
import org.wecancodeit.pantryplus.category.CategoryRepository;
import org.wecancodeit.pantryplus.lineitem.CountedLineItem;
import org.wecancodeit.pantryplus.lineitem.LineItem;
import org.wecancodeit.pantryplus.lineitem.LineItemRepository;
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

	@Resource
	private LineItemRepository lineItemRepo;

	@RequestMapping("/")
	public String displayUserForm(Model model) {
		return "user-form";
	}

	@RequestMapping("/user-form")
	public String userFormProcessing(@RequestParam String firstName, @RequestParam String lastName,
			@RequestParam int familySize, @RequestParam int schoolkidsCount,
			@RequestParam(defaultValue = "false") boolean infants, @RequestParam String pickUpDate,
			@RequestParam String zipCode, @RequestParam String birthdate, @RequestParam String address, @RequestParam String phoneNumber, @RequestParam String emailAddress) {
		if (zipCode.equals("Other")){
			return "redirect:/invalid-zipcode";
		}
		User user = new User(firstName, lastName, familySize, schoolkidsCount, infants, pickUpDate, zipCode, address,
				birthdate, phoneNumber, emailAddress);
		user = userRepo.save(user);
		Cart cart = cartRepo.save(new Cart(user));
		long cartId = cart.getId();
		return "redirect:/shopping?cartId=" + cartId;
	}
	
	@RequestMapping("/invalid-zipcode")
	public String displayIncorrectZipcode() {
		return "invalid-zipcode";
	}

	@RequestMapping("/shopping")
	public String displayShopping(Model model, @RequestParam long cartId) {
		model.addAttribute("categories", categoryRepo.findAll());
		model.addAttribute("cart", cartRepo.findOne(cartId));
		return "shopping";
	}

	@RequestMapping("/carts/{cartId}")
	public String displayCart(Model model, @PathVariable long cartId) {
		Cart cart = cartRepo.findOne(cartId);
		model.addAttribute("cart", cart);
		Iterable<LineItem> lineItems = cart.getLineItems();
		Set<LineItem> superLineItems = new HashSet<>();
		Set<CountedLineItem> countedLineItems = new HashSet<>();
		for (LineItem item : lineItems) {
			if (item instanceof CountedLineItem) {
				countedLineItems.add((CountedLineItem) item);
			} else {
				superLineItems.add(item);
			}
		}
		model.addAttribute("lineItems", superLineItems);
		model.addAttribute("countedLineItems", countedLineItems);
		return "cart";
	}
	
	@RequestMapping("/about-us")
	public String displayAboutUs() {
		return "about-us";
	}

}
