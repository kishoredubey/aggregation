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
public class PricingTask {
    private BackendClient backendClient;

    public PricingTask(BackendClient backendClient) {
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
                String price = backendClient.getPricing(order);
                if (price != null) {
                    response.put(order, price);
                }
                latch.countDown();
            });
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            // handle the error appropriately
        }
        executor.shutdown();
        return response;
    }
}
