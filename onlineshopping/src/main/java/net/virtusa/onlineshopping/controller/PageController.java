package net.virtusa.onlineshopping.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PageController {
	@RequestMapping(value = { "/", "/home", "/index" })
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("greeting","welcome to spring");
		mv.addObject("userClickHome",true);
		return mv;
	}

	
	@RequestMapping(value = "/test" )
	public ModelAndView test(@RequestParam(value= "greeting" ,required=false)String greeting ) {
		if(greeting==null)
		{
			greeting="hi";
		}
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("greeting", greeting);
		return mv;
	}

}
