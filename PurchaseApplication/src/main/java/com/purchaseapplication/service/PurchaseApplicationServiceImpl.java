package com.purchaseapplication.service;

import java.util.List;

import com.purchaseapplication.dao.IPurchaseApplicationDao;
import com.purchaseapplication.dao.PurchaseApplicationDaoImpl;
import com.purchaseapplication.model.PurchaseApplication;

public class PurchaseApplicationServiceImpl implements
		IPurchaseApplicationService {
	IPurchaseApplicationDao applicationDaoObject = new PurchaseApplicationDaoImpl();

	public boolean addProduct(PurchaseApplication object) {

		boolean status = false;
		System.out.println("xcvbnm");
		if (object.getProductName() != null && object.getProductType() != null
				&& object.getProductColor() != null
				&& object.getProductPrice() != null
				&& object.getItemsInStock() != null
				&& object.getImage() != null) {
			System.out.println("123456u");
			status = applicationDaoObject.addProduct(object);
		} else {
			System.out.println("Wrong input!!");
		}
		return status;
	}

	public List<PurchaseApplication> getList() {
		return applicationDaoObject.getList();

	}
}
