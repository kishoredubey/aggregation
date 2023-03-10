package org.aggregation.task;

import lombok.extern.slf4j.Slf4j;
import org.aggregation.services.BackendClient;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@Component
public class ShipmentTask {
    private BackendClient backendClient;

    public ShipmentTask(BackendClient backendClient) {
        this.backendClient = backendClient;
    }

    public @NonNull Map<String, List<String>> submit(final List<String> orders) {
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
                latch.countDown();
            });
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            // handle the error appropriately
            log.error("error during shipment task {}", e.getCause());
        } finally {
            executor.shutdown();
        }
        return response;
    }
}
