package net.virtusa.onlineshopping.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import net.virtusa.onlineshopping.util.FileUploadUtility;
import net.virtusa.onlineshopping.validator.ProductValidator;
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
			else if(operation.equals("category"))
			{
				mv.addObject("message", "Category submitted successfully!");
			}
         }

		// Product nProduct = new Product();		
		//mv.addObject("product", productDAO.get(id));			
		return mv;
		
	}
	
	
	@RequestMapping(value = "/{id}/product", method=RequestMethod.GET)
	public ModelAndView showEditProducts(@PathVariable int id) 
	{		

		ModelAndView mv = new ModelAndView("page");	
		
		mv.addObject("userClickManageProducts",true);
		mv.addObject("title","Manage Products");		
						
        Product nProduct = productDAO.get(id);
               				
		

		mv.addObject("product", nProduct);
				
		return mv;
		
	}
	
	
	@RequestMapping(value = "/products", method=RequestMethod.POST)
	public String handleProductSubmission(@Valid @ModelAttribute("product") Product mProduct,BindingResult results,Model model,HttpServletRequest request ) 
	{	
		
		
		if(mProduct.getId() == 0)
		{
			new ProductValidator().validate(mProduct, results);

		}
		
		else
		{
			if(!mProduct.getFile().getOriginalFilename().equals(""))
			{
				new ProductValidator().validate(mProduct, results);
			}
		}
		if(results.hasErrors())
	{
			model.addAttribute("userClickManageProducts",true);
			model.addAttribute("title","Manage Products");
			model.addAttribute("message","Validation failed for product ");
			
			return "page";
	}
		logger.info(mProduct.toString());
		
		if(mProduct.getId() == 0)
		{
			productDAO.add(mProduct);	
		}
		else
			
		{
			productDAO.update(mProduct);	
		}
		
		if(!mProduct.getFile().getOriginalFilename().equals("") ){
			FileUploadUtility.uploadFile(request, mProduct.getFile(), mProduct.getCode()); 
		 }
		

		
		return "redirect:/manage/products?operation=product" ;
	}
	

	@RequestMapping(value = "/product/{id}/activation", method=RequestMethod.POST)
	@ResponseBody
	public String handleProductActivation(@PathVariable int id) 
	{		
		Product product = productDAO.get(id);
		boolean isActive = product.isActive();
		product.setActive(!product.isActive());
		productDAO.update(product);		
		return (isActive)? "You have successfully deactivated the product with id"+ product.getId()
		:"You have successfully activated the product with id"+ product.getId();
	}
		
	
	@RequestMapping(value = "/category", method=RequestMethod.POST)
	public String handleCategorySubmission(@ModelAttribute Category category) {					
		categoryDAO.add(category);		
		return "redirect:/manage/products?operation=category";
	}
	

	
	@ModelAttribute("categories") 
	public List<Category> getCategories() {
		return categoryDAO.list();
	}
	
	@ModelAttribute("category")
	public Category getCategory() {
		return new Category();
	}
	

	
	
	
	

	}
	


