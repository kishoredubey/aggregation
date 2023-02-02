package org.aggregation.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@Service
public class BackendClientImpl implements BackendClient {
    public static final String SHIPMENT_STATUS_ENDPOINT = "/shipment-products?orderNumber={orderNumber}";
    public static final String TRACK_STATUS_ENDPOINT = "/track-status?orderNumber={orderNumber}";
    public static final String PRICING_ENDPOINT = "/pricing?countryCode={countryCode}";

    private RestTemplate restTemplate;
    private String backendEndpoint;

    public BackendClientImpl(RestTemplate restTemplate, @Value("${service.base-url}") String backendEndpoint) {
        this.restTemplate = restTemplate;
        this.backendEndpoint = backendEndpoint;
    }

    @Override
    public List<String> getShipmentStatus(String orderNumber) {
        List<String> response = null;
        try {
            ResponseEntity<List> out = restTemplate.getForEntity(
                    backendEndpoint + SHIPMENT_STATUS_ENDPOINT, List.class, orderNumber
            );
            log.debug(String.valueOf(out.getBody()));
            response = out.getBody();
        } catch (Exception e) {
            // track timeout exception here and report appropriately
            log.error("request timeout {}", e.getCause());
        }
        return response;
    }

    @Override
    public String getTrackStatus(String orderNumber) {
        String response = null;
        try {
            ResponseEntity<String> out = restTemplate.getForEntity(
                    backendEndpoint + TRACK_STATUS_ENDPOINT, String.class, orderNumber
            );
            log.debug(out.getBody());
            response = out.getBody();
        } catch (Exception e) {
            // track timeout exception here and report appropriately
            log.error("request timeout {}", e.getCause());
        }
        String replace=null;
        if(response!=null){
            char first = response.charAt(0);
            String valueOf = String.valueOf(first);
            replace = response.replace(valueOf, "");
        }
        return replace;
    }

    @Override
    public String getPricing(String countryCode) {
        String response = null;
        try {
            ResponseEntity<String> out = restTemplate.getForEntity(
                    backendEndpoint + PRICING_ENDPOINT, String.class, countryCode
            );
            log.debug(out.getBody());
            response = out.getBody();
        } catch (Exception e) {
            // track timeout exception here and report appropriately
            log.error("request timeout {}", e.getCause());
        }
        return response;
    }
}
