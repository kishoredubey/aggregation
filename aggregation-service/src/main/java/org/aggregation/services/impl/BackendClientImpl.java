package org.aggregation.services.impl;

import org.aggregation.services.BackendClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class BackendClientImpl implements BackendClient {
    public static final String SHIPMENT_STATUS_ENDPOINT = "/shipment-products?orderNumber={orderNumber}";
    public static final String TRACK_STATUS_ENDPOINT = "/track-status?orderNumber={orderNumber}";
    public static final String PRICING_ENDPOINT = "/pricing?countryCode={countryCode}";

    private RestTemplate restTemplate;
    @Value("${service.base-url}")
    private String BASE_URI;

    public BackendClientImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<String> getShipmentStatus(String orderNumber) {
        List<String> response = null;
        try {
            ResponseEntity<List> out = restTemplate.getForEntity(
                    BASE_URI + SHIPMENT_STATUS_ENDPOINT, List.class, orderNumber
            );
            response = out.getBody();
        } catch (Exception e) {
            // track timeout exception here and report appropriately
        }
        return response;
    }

    @Override
    public String getTrackStatus(String orderNumber) {
        String response = null;
        try {
            ResponseEntity<String> out = restTemplate.getForEntity(
                    BASE_URI + TRACK_STATUS_ENDPOINT, String.class, orderNumber
            );
            response = out.getBody();
        } catch (Exception e) {
            // track timeout exception here and report appropriately
        }
        return response;
    }

    @Override
    public String getPricing(String countryCode) {
        String response = null;
        try {
            ResponseEntity<String> out = restTemplate.getForEntity(
                    BASE_URI + PRICING_ENDPOINT, String.class, countryCode
            );
            response = out.getBody();
        } catch (Exception e) {
            // track timeout exception here and report appropriately
        }
        return response;
    }
}
