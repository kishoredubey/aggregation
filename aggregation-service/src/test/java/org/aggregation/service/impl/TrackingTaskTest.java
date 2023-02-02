package org.aggregation.service.impl;

import org.aggregation.services.BackendClient;
import org.aggregation.services.impl.TrackingTask;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class TrackingTaskTest {

    @InjectMocks
    TrackingTask trackingTask;

    @Mock
    private BackendClient backendClient;

    @Test
    public void test_submit(){
        List<String> orders = new ArrayList<>();
        orders.add("123456789");
        ExecutorService executor = Mockito.mock(ExecutorService.class);
        doReturn("DELIVERED").when(backendClient).getTrackStatus(Mockito.anyString());
        Map<String, String> submit = trackingTask.submit(orders);
        assertEquals("DELIVERED",submit.get("123456789"));

    }
}
