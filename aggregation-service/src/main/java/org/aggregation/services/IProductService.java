package org.aggregation.services;

import java.util.List;
import java.util.Map;

public interface IProductService {
	Map<String, String> getPrice(List<String> countryCodes);
	Map<String, List<String>> getShipmentProducts(List<String> orderNumbers);
	Map<String, String> getStatus(List<String> orderNumbers);
}
