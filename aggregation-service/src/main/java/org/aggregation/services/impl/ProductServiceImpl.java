package org.aggregation.services.impl;

import java.util.List;

import org.aggregation.services.IBackendService;
import org.aggregation.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements IProductService{

	@Autowired
	IBackendService backendService;
	
	@Override
	public Double getPrice(String countryCode) {
		// TODO Auto-generated method stub
		return backendService.getPriceOfProduct(countryCode);
	}

	@Override
	public List<String> getShipmentProducts(String orderNumber) {
		// TODO Auto-generated method stub
		return backendService.getShipmentProducts(orderNumber);
	}

	@Override
	public String getStatus(String orderNumber) {
		// TODO Auto-generated method stub
		return backendService.getTrackProductStatus(orderNumber);
	}
	


}
