package net.virtusa.shoppingBackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import net.virtusa.shoppingBackend.dao.CartLineDAO;
import net.virtusa.shoppingBackend.dao.ProductDAO;
import net.virtusa.shoppingBackend.dao.UserDAO;
import net.virtusa.shoppingBackend.dto.Cart;
import net.virtusa.shoppingBackend.dto.CartLine;
import net.virtusa.shoppingBackend.dto.Product;
import net.virtusa.shoppingBackend.dto.User;

public class CartLineTestCase {

	private static AnnotationConfigApplicationContext context;
	
	
	private static CartLineDAO cartLineDAO = null;
	private static ProductDAO productDAO = null;
	private static UserDAO userDAO = null;
		
	private CartLine cartLine = null;
	
	private User user = null;
	private Product product = null;
	private Cart cart = null;
	
	
	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("net.virtusa.shoppingBackend");
		context.refresh();
		cartLineDAO = (CartLineDAO)context.getBean("cartLineDAO");
		productDAO = (ProductDAO)context.getBean("productDAO");
		userDAO = (UserDAO)context.getBean("userDAO");
	}

	
	@Test
	public void testAddNewCartLine() { 
		
		// fetch the user and then cart of that user
				 user = userDAO.getByEmail("akshatamithari@gmail.com");		
				 cart = user.getCart();
				
				// fetch the product 
			 product = productDAO.get(1);
				
				// Create a new CartLine
				cartLine = new CartLine();
				
				cartLine.setBuyingPrice(product.getUnitPrice());
				cartLine.setProductCount(cartLine.getProductCount()+1);
				cartLine.setTotal(product.getUnitPrice() * cartLine.getProductCount());
				cartLine.setAvailable(true);
				cartLine.setCartId(cart.getId());
				cartLine.setProduct(product);
				
				assertEquals("Failed to add the CartLine!",true, cartLineDAO.add(cartLine));
				
				cart.setGrandTotal(cart.getGrandTotal() + cartLine.getTotal());
				
				cart.setCartLines(cart.getCartLines() + 1);
				
				assertEquals("Failed to update the cartLine!",true, cartLineDAO.updateCart(cart));

				
			//	double oldTotal = cartLine.getTotal();		
				
				
									
	}

	
}
