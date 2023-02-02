package org.aggregation.service.impl;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.doReturn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.aggregation.model.AggregationResponse;
import org.aggregation.services.AggregationServiceImpl;
import org.aggregation.task.PricingTask;
import org.aggregation.task.ShipmentTask;
import org.aggregation.task.TrackingTask;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AggregationServiceImplTest {
    @InjectMocks
    private AggregationServiceImpl aggregationServiceImpl;

    @Mock
    private ShipmentTask shipmentTask;
    @Mock
    private TrackingTask trackingTask;
    @Mock
    private PricingTask pricingTask;



    @Test
    public void test_getAggregatedProductDetail(){
        List<String> shipmentOrderNumbers = new ArrayList<>();
        shipmentOrderNumbers.add("987654321");
        List<String> trackOrderNumbers = new ArrayList<>();
        trackOrderNumbers.add("987654321");
        List<String> pricingCountryCodes = new ArrayList<>();
        pricingCountryCodes.add("NL");

        Map<String, List<String>> shipments = new HashMap<>();
        shipments.put("987654321", Arrays.asList("BOX","BOX","PALLET"));
        Map<String, String> track = new HashMap<>();
        track.put("123456789","COLLECTING");
        Map<String, String> prices = new HashMap<>();
        prices.put("NL", "14.84");

        doReturn(shipments).when(shipmentTask).submit(any());
        doReturn(track).when(trackingTask).submit(anyList());
        doReturn(prices).when(pricingTask).submit(anyList());

        AggregationResponse aggregatedProductDetail = aggregationServiceImpl.getAggregatedProductDetail(shipmentOrderNumbers,
                trackOrderNumbers,
                pricingCountryCodes);

        assertEquals(shipments.get("987654321"), aggregatedProductDetail.getShipments().get("987654321"));
        assertEquals(track.get("987654321"), aggregatedProductDetail.getTrack().get("987654321"));
        assertEquals(prices.get("NL"), aggregatedProductDetail.getPrices().get("NL"));
    }
}
