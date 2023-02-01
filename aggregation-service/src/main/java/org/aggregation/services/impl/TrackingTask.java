package org.aggregation.services.impl;

import org.aggregation.services.BackendClient;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class TrackingTask {
    private BackendClient backendClient;

    public TrackingTask(BackendClient backendClient) {
        this.backendClient = backendClient;
    }

    public Map<String, String> submit(List<String> orders) {
        Map<String, String> response = new HashMap<>();
        if (orders == null || orders.isEmpty()) return response;

        CountDownLatch latch = new CountDownLatch(orders.size());
        ExecutorService executor = Executors.newFixedThreadPool(orders.size());
        for (String order : orders) {
            executor.execute(() -> {
                // code to be executed asynchronously
                String status = backendClient.getTrackStatus(order);
                if (status != null) {
                    response.put(order, status);
                }
                latch.countDown();
            });
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            // handle the error appropriately
        } finally {
            executor.shutdown();
        }
        return response;
    }
}
