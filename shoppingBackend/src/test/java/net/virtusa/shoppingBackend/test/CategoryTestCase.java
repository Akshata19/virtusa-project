package net.virtusa.shoppingBackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import net.virtusa.shoppingBackend.dao.CategoryDAO;
import net.virtusa.shoppingBackend.dto.Category;

public class CategoryTestCase {
	
	private static AnnotationConfigApplicationContext context;

	private static CategoryDAO categoryDAO;

	private Category category;

	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("net.virtusa.shoppingBackend");
		context.refresh();
		categoryDAO = (CategoryDAO)context.getBean("categoryDAO");
	}

/*	@Test
	public void testAddCategory() {
		
		category = new Category();
		
		category.setName("Kid's Apparels");
		category.setDescription("This includes all category of Kid's apparel");
	    category.setImageURL("kid's Apparel.jpg");
		
		assertEquals("Successfully added a category inside the table!",true,categoryDAO.add(category));
		
		
	} */
	
	/*@Test
	public void testGetCategory() {
		
		category = categoryDAO.get(3);
		
		
		assertEquals("Successfully fetched a single category from the table!","Kid's Apparels",category.getName());
		
		
	}
*/
	
/*	@Test
	public void testUpdateCategory() {
		
		category = categoryDAO.get(3);
		
		category.setName("kid's Apparels");
		
		assertEquals("Successfully updated a single category in the table!",true,categoryDAO.update(category));
		
		
	} */
	
/*	@Test
	public void testDeleteCategory() {
		
		category = categoryDAO.get(3);		
		assertEquals("Successfully deleted a single category in the table!",true,categoryDAO.delete(category));
		
		
	} */
	
/*	@Test
	public void testListCategory() {
					
		assertEquals("Successfully fetched the list of categories from the table!",2,categoryDAO.list().size());
		
		
	}

*/
	@Test
	public void testCRUDCategory() {
		
		 category = new Category();
		category.setName("Women's Apparels");
		category.setDescription("This includes all category of Women's apparel");
	    category.setImageURL("women's Apparel.jpg");
	    assertEquals("Successfully added a category inside the table!",true,categoryDAO.add(category));

	  
	    category = new Category();
		//category.setId(2);
		category.setName("Men's Apparels");
		category.setDescription("This includes all category of Men's apparel");
	    category.setImageURL("men's Apparel.jpeg");
	    assertEquals("Successfully added a category inside the table!",true,categoryDAO.add(category));

	    
	    
	    category = new Category();
	//	category.setId(3);
		category.setName("Kid's Apparels");
		category.setDescription("This includes all category of Kid's apparel");
	    category.setImageURL("kid's Apparel.jpg");
	    assertEquals("Successfully added a category inside the table!",true,categoryDAO.add(category));

	    
	    category = categoryDAO.get(3);
		category.setName("kids Apparels");
				assertEquals("Successfully updated a single category in the table!",true,categoryDAO.update(category));
				
		category = categoryDAO.get(3);		
		assertEquals("Successfully deleted a single category in the table!",true,categoryDAO.delete(category));
		
		assertEquals("Successfully fetched the list of categories from the table!",2,categoryDAO.list().size());
	   
		
	}
	
}
