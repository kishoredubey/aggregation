package org.aggregation.services;

import java.util.List;

public interface BackendClient {
    public List<String> getShipmentStatus(String orderNumber);

    public String getTrackStatus(String orderNumber);

    public String getPricing(String countryCode);
}
