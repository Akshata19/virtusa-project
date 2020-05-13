package net.virtusa.onlineshopping.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/cart")
public class CartController {

	@RequestMapping("/show")
	public ModelAndView showCart() {
		
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Shopping Cart");
		mv.addObject("userClickShowCart", true);
		
		
		mv.addObject("cartLines", null);
		return mv;
		
		
	/*	if(result!=null) {
			switch(result) {
				case "added":
					mv.addObject("message", "Product has been successfully added inside cart!");					
					cartService.validateCartLine();
					break;
				case "unavailable":
					mv.addObject("message", "Product quantity is not available!");					
					break;
				case "updated":
					mv.addObject("message", "Cart has been updated successfully!");					
					cartService.validateCartLine();
					break;
				case "modified":
					mv.addObject("message", "One or more items inside cart has been modified!");
					break;
				case "maximum":
					mv.addObject("message", "Maximum limit for the item has been reached!");
					break;
				case "deleted":
					mv.addObject("message", "CartLine has been successfully removed!");
					break;

			}
		}
		else {
			String response = cartService.validateCartLine();
			if(response.equals("result=modified")) {
				mv.addObject("message", "One or more items inside cart has been modified!");
			}
		} */

		
	}
	


	
	
}
