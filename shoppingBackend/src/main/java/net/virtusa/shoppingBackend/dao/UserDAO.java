package net.virtusa.shoppingBackend.dao;

import java.util.List;

import net.virtusa.shoppingBackend.dto.Address;
import net.virtusa.shoppingBackend.dto.Cart;
import net.virtusa.shoppingBackend.dto.User;

public interface UserDAO {
	boolean addUser(User user);
	boolean addAddress(Address address);
	
//	Address getBillingAddress(int userId);
	//List<Address> listShippingAddresses(int userId);
	
	Address getBillingAddress(User user);
	List<Address> listShippingAddresses(User user);
	
	boolean updateCart(Cart cart);
  User getByEmail(String email);

}
