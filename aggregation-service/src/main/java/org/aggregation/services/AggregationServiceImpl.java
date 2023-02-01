package org.aggregation.services;

import org.aggregation.model.AggregationResponse;
import org.aggregation.task.PricingTask;
import org.aggregation.task.ShipmentTask;
import org.aggregation.task.TrackingTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Service
public class AggregationServiceImpl implements AggregationService {
    private final ShipmentTask shipmentTask;
    private final TrackingTask trackingTask;
    private final PricingTask pricingTask;

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

    /**
     * @param shipmentOrderNumbers
     * @param trackOrderNumbers
     * @param pricingCountryCodes
     * @return AggregationResponse
     */
    @Override
    public AggregationResponse getAggregatedDetails(
            List<String> shipmentOrderNumbers,
            List<String> trackOrderNumbers,
            List<String> pricingCountryCodes
    ) {
        /**
         * Submit all three tasks [shipmentTask, trackingTask, pricingTask] asynchronously
         **/
        CompletableFuture<Map<String, List<String>>> shipmentResponse = CompletableFuture.supplyAsync(() ->
                shipmentTask.submit(shipmentOrderNumbers)
        );

        CompletableFuture<Map<String, String>> trackingResponse = CompletableFuture.supplyAsync(() ->
                trackingTask.submit(trackOrderNumbers)
        );

        CompletableFuture<Map<String, String>> pricingResponse = CompletableFuture.supplyAsync(() ->
                pricingTask.submit(pricingCountryCodes)
        );

        /**
         * Aggregate the response from all three asynchronous submissions.
         */
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
