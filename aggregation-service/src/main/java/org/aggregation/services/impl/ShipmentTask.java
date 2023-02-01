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
public class ShipmentTask {
    private BackendClient backendClient;

    public ShipmentTask(BackendClient backendClient) {
        this.backendClient = backendClient;
    }

    public Map<String, List<String>> submit(List<String> orders) {
        Map<String, List<String>> response = new HashMap<>();
        if (orders == null || orders.isEmpty()) return response;

        CountDownLatch latch = new CountDownLatch(orders.size());
        ExecutorService executor = Executors.newFixedThreadPool(orders.size());
        for (String order : orders) {
            executor.execute(() -> {
                // code to be executed asynchronously
                List<String> status = backendClient.getShipmentStatus(order);
                if (status != null) {
                    response.put(order, status);
                }
                System.out.println("For order ="+ order + " the response is = " +response);
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
