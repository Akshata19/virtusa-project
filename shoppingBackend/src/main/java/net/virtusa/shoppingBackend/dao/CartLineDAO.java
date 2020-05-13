package net.virtusa.shoppingBackend.dao;

import java.util.List;

import net.virtusa.shoppingBackend.dto.Cart;
import net.virtusa.shoppingBackend.dto.CartLine;

public interface CartLineDAO {

	public List<CartLine> list(int cartId);
	public CartLine get(int id);	
	public boolean add(CartLine cartLine);
	public boolean update(CartLine cartLine);
	public boolean delete(CartLine cartLine);

	// list of available cartLine
		public List<CartLine> listAvailable(int cartId);

		// fetch the CartLine based on cartId and productId
		public CartLine getByCartAndProduct(int cartId, int productId);	
		
		boolean updateCart(Cart cart);
	
}
