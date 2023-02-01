package org.aggregation.task;

import org.aggregation.services.BackendClient;
import org.springframework.lang.NonNull;
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

    public @NonNull Map<String, String> submit(final List<String> countryCodes) {
        Map<String, String> response = new HashMap<>();
        if (countryCodes == null || countryCodes.isEmpty()) return response;

        CountDownLatch latch = new CountDownLatch(countryCodes.size());
        ExecutorService executor = Executors.newFixedThreadPool(countryCodes.size());
        for (String code : countryCodes) {
            executor.execute(() -> {
                // code to be executed asynchronously
                String price = backendClient.getPricing(code);
                if (price != null) {
                    response.put(code, price);
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
