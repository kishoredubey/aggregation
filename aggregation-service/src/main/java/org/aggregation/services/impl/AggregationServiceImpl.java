package org.aggregation.services.impl;

import java.util.List;
import java.util.Map;

import org.aggregation.dto.AggregationDto;
import org.aggregation.services.IAggregationService;
import org.aggregation.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AggregationServiceImpl implements IAggregationService{

	@Autowired
	IProductService productService;
	
	@Override
	public AggregationDto getAggregatedProductDetail(List<String> shipmentOrderNumbers, List<String> trackOrderNumbers,
			List<String> pricingCountryCodes) {
		Map<String, List<String>> shipments = productService.getShipmentProducts(shipmentOrderNumbers);
		Map<String, String> track = productService.getStatus(trackOrderNumbers);
		Map<String, String> prices = productService.getPrice(pricingCountryCodes);
		AggregationDto aggregatedData = new AggregationDto();
		aggregatedData.setShipments(shipments);
		aggregatedData.setTrack(track);
		aggregatedData.setPrices(prices);
		return aggregatedData;
	}

}
