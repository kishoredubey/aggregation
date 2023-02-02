package org.aggregation.services;

import java.util.List;

public interface BackendClient {
    List<String> getShipmentStatus(String orderNumber);

    String getTrackStatus(String orderNumber);

    String getPricing(String countryCode);
}
