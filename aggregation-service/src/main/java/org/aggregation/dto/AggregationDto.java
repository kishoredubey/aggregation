package org.aggregation.dto;

import java.util.List;
import java.util.Map;

public class AggregationDto {
	private Map<String,List<String>> shipments;
	private Map<String, String> track;
	private Map<String, String> prices;
	public Map<String, List<String>> getShipments() {
		return shipments;
	}
	public void setShipments(Map<String, List<String>> shipments) {
		this.shipments = shipments;
	}
	public Map<String, String> getTrack() {
		return track;
	}
	public void setTrack(Map<String, String> track) {
		this.track = track;
	}
	public Map<String, String> getPrices() {
		return prices;
	}
	public void setPrices(Map<String, String> prices) {
		this.prices = prices;
	}
	
	
	

	
	
}
