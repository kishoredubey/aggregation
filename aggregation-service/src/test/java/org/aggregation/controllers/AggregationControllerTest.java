package org.aggregation.controllers;

import org.aggregation.model.AggregationResponse;
import org.aggregation.services.AggregationService;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AggregationControllerTest {

    private AggregationService service = Mockito.mock(AggregationService.class);
    private AggregationController controller = new AggregationController(service);
    protected MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

    @Test
    public void test_getProductPrice_get() throws Exception {
        String[] shipmentOrderNumbers = new String[]{"123456789"};
        String[] trackOrderNumbers = new String[]{"123456789"};
        String[] pricingCountryCodes = new String[]{"123456789"};

        AggregationResponse aggregationResponse = new AggregationResponse();
        Map<String, List<String>> shipments = new HashMap<>();
        shipments.put("123456789", Arrays.asList("BOX"));
        Map<String, String> track = new HashMap<>();
        track.put("123456789", "DELIVERED");
        Map<String, String> prices = new HashMap<>();
        prices.put("NL", "14.2");

        aggregationResponse.setShipments(shipments);
        aggregationResponse.setTrack(track);
        aggregationResponse.setPrices(prices);

        doReturn(aggregationResponse).when(
                service).getAggregatedDetails(anyList(), anyList(), anyList()
        );

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/aggregation")
                .param("shipmentOrderNumbers", shipmentOrderNumbers)
                .param("trackOrderNumbers", trackOrderNumbers)
                .param("pricingCountryCodes", pricingCountryCodes);
        mockMvc.perform(requestBuilder).andExpect(status().isOk());
    }

}
