package net.virtusa.shoppingBackend.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.virtusa.shoppingBackend.dao.CategoryDAO;
import net.virtusa.shoppingBackend.dto.Category;
@Repository("categoryDAO")
@Transactional
public class CategoryDAOImpl implements CategoryDAO {
	@Autowired
	private SessionFactory sessionFactory;


	//private static List<Category> categories= new ArrayList<>();
	/*static 
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
	} */
	@Override
	public List<Category> list() {
		String selectActiveCategory = "FROM Category WHERE active = :active";

		Query query = sessionFactory.getCurrentSession().createQuery(selectActiveCategory);

		query.setParameter("active", true);
		
		return query.getResultList();

	}
	
	@Override
	public Category get(int id) {
		/*for(Category category:categories)
		{
			if(category.getId()==id)
				return category;
		} */
		return sessionFactory.getCurrentSession().get(Category.class,Integer.valueOf(id));
	}
	

	@Override
	public boolean add(Category category) {
		try {
			// add the category to the database table
			sessionFactory.getCurrentSession().persist(category);
			return true;
	     	} 
		catch (Exception ex)
		{
			ex.printStackTrace();
			return false;
		}

		
	}

@Override
public boolean update(Category category) {

	try {
		// add the category to the database table
		sessionFactory.getCurrentSession().update(category);
		return true;
	}
	catch (Exception ex) 
	{
		ex.printStackTrace();
		return false;
	}


}


@Override
public boolean delete(Category category) {
	
	category.setActive(false);
	
	try {
		// add the category to the database table
		sessionFactory.getCurrentSession().update(category);
		return true;
		} 
	catch (Exception ex)
	{
		ex.printStackTrace();
		return false;
	}
}


}
