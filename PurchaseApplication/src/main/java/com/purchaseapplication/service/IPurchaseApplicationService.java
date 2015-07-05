package com.purchaseapplication.service;

import java.util.List;

import com.purchaseapplication.model.PurchaseApplication;

public interface IPurchaseApplicationService {

	public boolean addProduct(PurchaseApplication object);

	public List<PurchaseApplication> getList();
}
