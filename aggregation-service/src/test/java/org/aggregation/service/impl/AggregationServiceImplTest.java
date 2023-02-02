package org.aggregation.service.impl;


import org.aggregation.model.AggregationResponse;
import org.aggregation.services.AggregationServiceImpl;
import org.aggregation.task.PricingTask;
import org.aggregation.task.ShipmentTask;
import org.aggregation.task.TrackingTask;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.doReturn;

public class AggregationServiceImplTest {

    private ShipmentTask mockShipmentTask = Mockito.mock(ShipmentTask.class);
    private TrackingTask mockTrackingTask = Mockito.mock(TrackingTask.class);
    private PricingTask mockPricingTask = Mockito.mock(PricingTask.class);
    private AggregationServiceImpl subject =
            new AggregationServiceImpl(mockShipmentTask, mockTrackingTask, mockPricingTask);

    @Test
    public void test_getAggregatedProductDetail() {
        List<String> shipmentOrderNumbers = new ArrayList<>();
        shipmentOrderNumbers.add("987654321");
        List<String> trackOrderNumbers = new ArrayList<>();
        trackOrderNumbers.add("987654321");
        List<String> pricingCountryCodes = new ArrayList<>();
        pricingCountryCodes.add("NL");

        Map<String, List<String>> shipments = new HashMap<>();
        shipments.put("987654321", Arrays.asList("BOX", "BOX", "PALLET"));
        Map<String, String> track = new HashMap<>();
        track.put("123456789", "COLLECTING");
        Map<String, String> prices = new HashMap<>();
        prices.put("NL", "14.84");

        doReturn(shipments).when(mockShipmentTask).submit(any());
        doReturn(track).when(mockTrackingTask).submit(anyList());
        doReturn(prices).when(mockPricingTask).submit(anyList());

        AggregationResponse aggregatedProductDetail = subject.getAggregatedDetails(
                shipmentOrderNumbers,
                trackOrderNumbers,
                pricingCountryCodes
        );

        assertEquals(shipments.get("987654321"), aggregatedProductDetail.getShipments().get("987654321"));
        assertEquals(track.get("987654321"), aggregatedProductDetail.getTrack().get("987654321"));
        assertEquals(prices.get("NL"), aggregatedProductDetail.getPrices().get("NL"));
    }
}
