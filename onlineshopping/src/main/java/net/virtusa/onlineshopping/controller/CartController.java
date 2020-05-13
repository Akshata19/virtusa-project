package net.virtusa.onlineshopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import net.virtusa.onlineshopping.service.CartService;

@Controller
@RequestMapping("/cart")
public class CartController {


	@Autowired
	private CartService cartService;
	
	
	
	@RequestMapping("/show")
	public ModelAndView showCart(@RequestParam(name = "result", required = false) String result) {
		
		ModelAndView mv = new ModelAndView("page");
		
								
		if(result!=null) 
		{
			switch(result) {
				case "added":
					mv.addObject("message", "Product has been successfully added inside cart!");					
					cartService.validateCartLine();
					break; 
				case "error":
					mv.addObject("message", "something went wrong");					
					break;
				case "updated":
					mv.addObject("message", "Cartline has been updated successfully!");					
					cartService.validateCartLine();
					break;
				case "delete":
					mv.addObject("message", "cartLine has been removed successfully");					
					break;
					
				case "unavailable":
					mv.addObject("message", "product quantity is not available");					
					break;
					
				case "maximum":
					mv.addObject("message", "cartLine has reached to maximum count");					
					break;
			

			}
		}
		mv.addObject("title", "User Cart");
		mv.addObject("userClickShowCart", true);
		mv.addObject("cartLines", cartService.getCartLines());
		return mv;
	} 
		
	
	
	@RequestMapping("/{cartLineId}/update")
	public String udpateCart(@PathVariable int cartLineId, @RequestParam int count) {
		String response = cartService.manageCartLine(cartLineId, count);		
		return "redirect:/cart/show?"+response;		
	}
	
	@RequestMapping("/{cartLineId}/delete")
	public String updateCart(@PathVariable int cartLineId) {
		String response = cartService.deleteCartLine(cartLineId);
		return "redirect:/cart/show?"+response;
	}
	
	

	@RequestMapping("/add/{productId}/product")
	public String addCart(@PathVariable int productId) {
		String response = cartService.addCartLine(productId);
		return "redirect:/cart/show?"+response;
	}

	@RequestMapping("/validate")
	public String validateCart() {	
		String response = cartService.validateCartLine();
		if(!response.equals("result=success")) {
			return "redirect:/cart/show?"+response;
		}
		else {
			return "redirect:/cart/checkout";
		}
	}	

	
}
