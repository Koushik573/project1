package com.niit.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.model.Category;

@Repository("categoryDAO")
@Transactional
public class CategoryDAOImplementation implements CategoryDAO
{
	@Autowired
	SessionFactory sessionfactory;
	
	public boolean addCategory(Category category) {
		try{
			sessionfactory.getCurrentSession().save(category);
			return true;
		}
		catch(Exception e){
			return false;
		}
	}

	
	public boolean deleteCategory(Category category) {
		try{
			sessionfactory.getCurrentSession().delete(category);
			return true;
		}
		catch(Exception e){
			return false;
		}
	}

	
	public boolean updateCategory(Category category) {
		try{
			sessionfactory.getCurrentSession().update(category);
			return true;
		}
		catch(Exception e){
			return false;
		}
	}

	
	public List<Category> listCategory() {
		Session session = sessionfactory.openSession();
		Query query = session.createQuery("from Category");
		List list = query.list();
		List<Category> listcategory = list;
		session.close();
		return listcategory;
	}

	
	public Category getCategory(int CategoryID) {
		Session session =  sessionfactory.openSession();
		Category category = (Category)session.get(Category.class,CategoryID);
		session.close();
		return category;
	}
	
}
