package org.wecancodeit.pantryplus;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartController {

	@Resource
	CartRepository cartRepo;

	@RequestMapping(path = "/something", method = RequestMethod.GET)
	public Cart findCart(long id) {
		return cartRepo.findOne(id);
	}

}
