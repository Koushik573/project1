package com.niit.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.model.Product;

@Repository("ProductDAO")
@Transactional
public class ProductDAOImplementation implements ProductDAO
{
	private static final Serializable ProductID = null;
	@Autowired
	SessionFactory sessionfactory;

	
	public boolean addProduct(Product product) {
		try{
			sessionfactory.getCurrentSession().save(product);
			return true;
		}
		catch(Exception e){
			return false;
		}
	}

	
	public boolean deleteProduct(Product product) {
		try{
			sessionfactory.getCurrentSession().delete(product);
			return true;
		}
		catch(Exception e){
			return false;
		}

	}

	public boolean updateProduct(Product product) {
		try{
			sessionfactory.getCurrentSession().update(product);
			return true;
		}
		catch(Exception e){
			return false;
		}
	}

	
	public List<Product> listProduct() {
		Session session = sessionfactory.openSession();
		Query query = session.createQuery("from Product");
		List list = query.list();
		List<Product> listproduct = list;
		session.close();
		return listproduct;
	}

	
	public Product getProduct(int product) {
		Session session =  sessionfactory.openSession();
		Product product1= (Product)session.get(Product.class,ProductID);
		session.close();
		return product1;	
		
	
	}
}
