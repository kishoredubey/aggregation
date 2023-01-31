package org.aggregation.services;

import java.util.List;

public interface IBackendService {
	 List<String> getShipmentProducts(String orderNumber);
	 String getTrackProductStatus(String orderNumber);
	 Double getPriceOfProduct(String countryCode);
}
