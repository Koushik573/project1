package com.niit.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.model.Supplier;

@Repository("supplierDAO")
@Transactional
public class SupplierDAOImplementation implements SupplierDAO 
{
	@Autowired
	SessionFactory sessionfactory;

	@Override
	public boolean addSupplier(Supplier supplier) {
		try{
			sessionfactory.getCurrentSession().save(supplier);
			return true;
		}
		catch(Exception e){
			return false;
		}
	}

	@Override
	public boolean deleteSupplier(Supplier supplier) {
		try{
			sessionfactory.getCurrentSession().delete(supplier);
			return true;
		}
		catch(Exception e){
			return false;
		}
	}

	@Override
	public boolean updateSupplier(Supplier supplier) {
		try{
			sessionfactory.getCurrentSession().update(supplier);
			return true;
		}
		catch(Exception e){
			return false;
		}
	}

	@Override
	public Supplier getSupplier(int SupplierID) {
		Session session =  sessionfactory.openSession();
		Supplier supplier = (Supplier)session.get(Supplier.class,SupplierID);
		session.close();
		return supplier;
	}

	@Override
	public List<Supplier> listSupplier() {
		Session session = sessionfactory.openSession();
		Query query = session.createQuery("from Supplier");
		List list = query.list();
		List<Supplier> listsupplier = list;
		session.close();
		return listsupplier;
	}
}
