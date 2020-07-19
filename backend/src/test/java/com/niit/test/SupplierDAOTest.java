package com.niit.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.dao.SupplierDAO;
import com.niit.model.Supplier;

public class SupplierDAOTest
{
	static SupplierDAO supplierdao; 
	@BeforeClass
	public static void intialize()
	{
         AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		
		context.scan("com.niit");
		
		context.refresh();
		
		supplierdao = (SupplierDAO)context.getBean("supplierDAO");

	}
	
	@Test
	public void addSupplierTest()
	{
		Supplier supplier = new Supplier();
		supplier.setSupplierName("ravinath");
		supplier.setSupplierAddress("china");
		assertTrue("problem in adding supplier:",supplierdao.addSupplier(supplier));	
	}
	
	
	@Ignore
	@Test
	public void updateSupplierTest()
	{
		Supplier supplier = supplierdao.getSupplier(14);
		supplier.setSupplierName("mani");
		supplier.setSupplierAddress("Mumbai");
		assertTrue("problem in updating supplier:",supplierdao.updateSupplier(supplier));
	
	}
	@Ignore
	@Test
	public void deleteSupplierTest()
	{
		Supplier supplier = supplierdao.getSupplier(21);
		
		assertTrue("problem in deleting supplier:",supplierdao.deleteSupplier(supplier));
	
	}
	
	@Test
	public void listSupplierTest()
	{
		List<Supplier> listsupplier =supplierdao.listSupplier();
		assertNotNull("problem in listing:",listsupplier);
		
		for(Supplier supplier:listsupplier){
			System.out.print(supplier.getSupplierID()+":::");
			System.out.print(supplier.getSupplierName()+":::");
			System.out.println(supplier.getSupplierAddress());

	}
}
}
