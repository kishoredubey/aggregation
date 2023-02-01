package org.aggregation.services.impl;

import org.aggregation.dto.AggregationResponse;
import org.aggregation.services.AggregationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

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
    public AggregationResponse getAggregatedDetails(
            List<String> shipmentOrderNumbers,
            List<String> trackOrderNumbers,
            List<String> pricingCountryCodes
    ) {
        CompletableFuture<Map<String, List<String>>> shipmentResponse = CompletableFuture.supplyAsync(() -> {
            return shipmentTask.submit(shipmentOrderNumbers);
        });

        CompletableFuture<Map<String, String>> trackingResponse = CompletableFuture.supplyAsync(() -> {
            return trackingTask.submit(trackOrderNumbers);
        });

        CompletableFuture<Map<String, String>> pricingResponse = CompletableFuture.supplyAsync(() -> {
            return pricingTask.submit(pricingCountryCodes);
        });

        AggregationResponse aggregatedData = new AggregationResponse();
        return CompletableFuture.allOf(shipmentResponse, trackingResponse, pricingResponse)
                .thenApply(v -> {
                    aggregatedData.setShipments(shipmentResponse.join());
                    aggregatedData.setTrack(trackingResponse.join());
                    aggregatedData.setPrices(pricingResponse.join());
                    return aggregatedData;
                }).join();
    }

}
