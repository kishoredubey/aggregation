package org.aggregation.services;

import java.util.List;

public interface IProductService {
	Double getPrice(String countryCode);
	List<String> getShipmentProducts(String orderNumber);
	String getStatus(String orderNumber);
}
