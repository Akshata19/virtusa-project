package net.virtusa.onlineshopping.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import net.virtusa.shoppingBackend.dao.CategoryDAO;
import net.virtusa.shoppingBackend.dao.ProductDAO;
import net.virtusa.shoppingBackend.dto.Category;
import net.virtusa.shoppingBackend.dto.Product;

@Controller
@RequestMapping("/manage")

public class ManagementController {
	
	
	
	@Autowired
	private ProductDAO productDAO;

	@Autowired
	private CategoryDAO categoryDAO;	
	
	private static final Logger logger = LoggerFactory.getLogger(ManagementController.class);
	
	@RequestMapping(value = "/products", method=RequestMethod.GET)

	public ModelAndView showManageProducts(@RequestParam(name="operation",required=false)String operation) 
	{		

		ModelAndView mv = new ModelAndView("page");	
		
		mv.addObject("userClickManageProducts",true);
		mv.addObject("title","Manage Products");		
						
        Product nProduct = new Product();
               				
		nProduct.setSupplierId(1);
		nProduct.setActive(true);

		mv.addObject("product", nProduct);
		 if(operation != null)
         {
			if(operation.equals("product")){
				mv.addObject("message", "Product submitted successfully!");
			}	
         }

		// Product nProduct = new Product();		
		//mv.addObject("product", productDAO.get(id));			
		return mv;
		
	}
	
	@RequestMapping(value = "/products", method=RequestMethod.POST)
	public String handleProductSubmission(@ModelAttribute("product") Product mProduct ) 
	{	
		logger.info(mProduct.toString());
		productDAO.add(mProduct);	
		return "redirect:/manage/products?operation=product" ;
	}

	
	@ModelAttribute("categories") 
	public List<Category> getCategories() {
		return categoryDAO.list();
	}

	}
	


