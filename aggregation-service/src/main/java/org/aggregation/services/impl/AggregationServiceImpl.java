package org.aggregation.services.impl;

import org.aggregation.dto.AggregationResponse;
import org.aggregation.services.AggregationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AggregationServiceImpl implements AggregationService {
    private ShipmentTask shipmentTask;
    private TrackingTask trackingTask;
    private PricingTask pricingTask;

    @Autowired
    public AggregationServiceImpl(
            ShipmentTask shipmentTask,
            TrackingTask trackingTask,
            PricingTask pricingTask
    ) {
        this.shipmentTask = shipmentTask;
        this.trackingTask = trackingTask;
        this.pricingTask = pricingTask;
    }

    @Override
    public AggregationResponse getAggregatedProductDetail(
            List<String> shipmentOrderNumbers,
            List<String> trackOrderNumbers,
            List<String> pricingCountryCodes
    ) {
        AggregationResponse aggregatedData = new AggregationResponse();
        Map<String, List<String>> shipments = shipmentTask.submit(shipmentOrderNumbers);
        Map<String, String> track = trackingTask.submit(trackOrderNumbers);
        Map<String, String> prices = pricingTask.submit(pricingCountryCodes);

        aggregatedData.setShipments(shipments);
        aggregatedData.setTrack(track);
        aggregatedData.setPrices(prices);
        return aggregatedData;
    }

}
