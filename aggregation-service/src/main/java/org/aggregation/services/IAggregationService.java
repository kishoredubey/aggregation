package org.aggregation.services;

import java.util.List;

import org.aggregation.dto.AggregationDto;

public interface IAggregationService {

	AggregationDto getAggregatedProductDetail(List<String> shipmentOrderNumbers, List<String> trackOrderNumbers,
			List<String> pricingCountryCodes);

}
