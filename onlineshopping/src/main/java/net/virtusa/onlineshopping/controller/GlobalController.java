package net.virtusa.onlineshopping.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import net.virtusa.onlineshopping.model.UserModel;
import net.virtusa.shoppingBackend.dao.UserDAO;
import net.virtusa.shoppingBackend.dto.User;

@ControllerAdvice
public class GlobalController {

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private HttpSession session;

	private UserModel userModel = null;
	private User user = null;

	@ModelAttribute("userModel")
	public UserModel getUserModel() {
		if (session.getAttribute("userModel") == null) {

			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			User user = userDAO.getByEmail(authentication.getName());

			if (user != null) {
				userModel = new UserModel();
				// set the name and the id
				userModel.setId(user.getId());
				userModel.setEmail(user.getEmail());
				userModel.setRole(user.getRole());
				userModel.setFullName(user.getFirstName() + " " + user.getLastName());

				if (user.getRole().equals("USER")) {
					userModel.setCart(user.getCart());
				}

				session.setAttribute("userModel", userModel);
				return userModel;

			}

		}

		return (UserModel) session.getAttribute("userModel");

	}

}
