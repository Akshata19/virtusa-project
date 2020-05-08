package net.virtusa.shoppingBackend.dao;

import java.util.List;

import net.virtusa.shoppingBackend.dto.Category;

public interface CategoryDAO {
List<Category> list();
Category get(int id);

}
