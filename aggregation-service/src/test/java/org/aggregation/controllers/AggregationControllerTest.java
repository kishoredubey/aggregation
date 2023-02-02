package org.aggregation.controllers;

import org.aggregation.Aggregation;
import org.aggregation.dto.AggregationResponse;
import org.aggregation.services.AggregationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Aggregation.class,loader = AnnotationConfigContextLoader.class)
@WebMvcTest
public class AggregationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    AggregationService aggregationService;

    @Test
    public void test_getProductPrice_get() throws Exception {
        /*String[] shipmentOrderNumbers = new String[]{"123456789"};
        String[] trackOrderNumbers = new String[]{"123456789"};
        String[] pricingCountryCodes = new String[]{"123456789"};

        AggregationResponse aggregationResponse = new AggregationResponse();
        Map<String, List<String>> shipments= new HashMap<>();
        shipments.put("123456789", Arrays.asList("BOX"));
        Map<String, String> track= new HashMap<>();
        track.put("123456789", "DELIVERED");
        Map<String, String> prices= new HashMap<>();
        prices.put("NL", "14.2");

        aggregationResponse.setShipments(shipments);
        aggregationResponse.setTrack(track);
        aggregationResponse.setPrices(prices);

        doReturn(aggregationResponse).when(aggregationService).getAggregatedProductDetail(anyList(), anyList(), anyList());

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/aggregation")
                .param("shipmentOrderNumbers",shipmentOrderNumbers)
                .param("trackOrderNumbers",trackOrderNumbers)
                .param("pricingCountryCodes",pricingCountryCodes);
        mockMvc.perform(requestBuilder).andExpect(status().isOk());
        */
    }

}
