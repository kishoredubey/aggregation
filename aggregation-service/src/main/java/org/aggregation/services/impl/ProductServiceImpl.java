package org.aggregation.services.impl;

import java.util.List;
import java.util.Map;

import org.aggregation.services.IBackendService;
import org.aggregation.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements IProductService{

	@Autowired
	IBackendService backendService;
	
	@Override
	public Map<String, String> getPrice(List<String> countryCodes) {
		// TODO Auto-generated method stub
		return backendService.getPriceOfProduct(countryCodes);
	}

	@Override
	public Map<String, List<String>> getShipmentProducts(List<String> orderNumbers) {
		// TODO Auto-generated method stub
		return backendService.getShipmentProducts(orderNumbers);
	}

	@Override
	public Map<String, String> getStatus(List<String> orderNumbers) {
		// TODO Auto-generated method stub
		return backendService.getTrackProductStatus(orderNumbers);
	}
	


}
