package net.virtusa.onlineshopping.model;

import java.io.Serializable;

import net.virtusa.shoppingBackend.dto.Address;
import net.virtusa.shoppingBackend.dto.User;

public class RegisterModel implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User user;
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Address getBilling() {
		return billing;
	}
	public void setBilling(Address billing) {
		this.billing = billing;
	}
	private Address billing;

}
