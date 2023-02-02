package org.aggregation.service.impl;

import org.aggregation.services.BackendClient;
import org.aggregation.services.impl.PricingTask;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class PricingTaskTest {
    @InjectMocks
    PricingTask pricingTask;
    @Mock
    private BackendClient backendClient;
    @Test
    public void test_submit(){
        List<String> countryCodes = new ArrayList<>();
        countryCodes.add("NL");
        ExecutorService executor = Mockito.mock(ExecutorService.class);
        doReturn("14.2").when(backendClient).getPricing(Mockito.anyString());
        Map<String, String> submit = pricingTask.submit(countryCodes);
        assertEquals("14.2",submit.get("NL"));
    }
}
