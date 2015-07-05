package com.purchaseapplication.dao;

import java.util.List;

import com.purchaseapplication.model.PurchaseApplication;

public interface IPurchaseApplicationDao {

	public boolean addProduct(PurchaseApplication object);
	
	public List<PurchaseApplication> getList();
}
