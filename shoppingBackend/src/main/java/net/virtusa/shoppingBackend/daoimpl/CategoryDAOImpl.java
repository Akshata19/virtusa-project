package net.virtusa.shoppingBackend.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import net.virtusa.shoppingBackend.dao.CategoryDAO;
import net.virtusa.shoppingBackend.dto.Category;
@Repository("categoryDAO")
public class CategoryDAOImpl implements CategoryDAO {

	private static List<Category> categories= new ArrayList<>();
	static 
	{
		Category category = new Category();
		category.setId(1);
		category.setName("Women's Apparels");
		category.setDescription("This includes all category of Women's apparel");
	    category.setImageURL("women's Apparel.jpg");
	    categories.add(category);
	    
	    category = new Category();
		category.setId(2);
		category.setName("Men's Apparels");
		category.setDescription("This includes all category of Men's apparel");
	    category.setImageURL("men's Apparel.jpeg");
	    categories.add(category);
	    
	    category = new Category();
		category.setId(3);
		category.setName("Kid's Apparels");
		category.setDescription("This includes all category of Kid's apparel");
	    category.setImageURL("kid's Apparel.jpg");
	    categories.add(category);
	}
	@Override
	public List<Category> list() {
		
		return categories;
	}
	
	@Override
	public Category get(int id) {
		for(Category category:categories)
		{
			if(category.getId()==id)
				return category;
		}
		return null;
	}

}
