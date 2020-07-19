package com.niit.dao;

import java.util.List;

import com.niit.model.Supplier;

public interface SupplierDAO 
{
	public boolean addSupplier(Supplier supplier);
	public boolean deleteSupplier(Supplier supplier);
	public boolean updateSupplier(Supplier supplier);
	public Supplier getSupplier(int SupplierID);
	public List<Supplier> listSupplier();
}
