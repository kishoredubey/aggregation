package org.aggregation.services;

import java.util.List;
import java.util.Map;

public interface IBackendService {
	 Map<String, List<String>> getShipmentProducts(List<String> orderNumbers);
	 Map<String, String> getTrackProductStatus(List<String> orderNumbers);
	 Map<String, String> getPriceOfProduct(List<String> countryCodes);
}
