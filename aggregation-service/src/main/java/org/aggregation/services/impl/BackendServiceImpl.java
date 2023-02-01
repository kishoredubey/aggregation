package org.aggregation.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.aggregation.error.ApiError;
import org.aggregation.error.CommonException;
import org.aggregation.services.IBackendService;
import org.aggregation.util.IsoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class BackendServiceImpl implements IBackendService{
	
	public static final String GET_SHIPMENT_PRODUCT_BY_ORDER_NUMBER_URI="shipment-product";
	public static final String GET_PRODUCT_PRICE_BY_COUNTRY_CD_URI="pricing";
	public static final String GET_TRACK_STATUS_BY_ORDER_NUMBER_URI="track-status";

	
	private RestTemplate restTemplate;
	
	@Value("${service.base-url}")
	private String baseUri;
	
	@Autowired
	public BackendServiceImpl(RestTemplate restTemplate) {
		super();
		this.restTemplate=restTemplate;
	}
	@Override
	public Map<String, List<String>> getShipmentProducts(List<String> orderNumbers) {
//		Map<String, List<String>> collect = orderNumbers.stream().map(orderNumber->{
//			ResponseEntity<String> response;
//			try {
//				String uri=buidURi("orderNumber",orderNumber,"shipment-products");
//				response= restTemplate.exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<String>() {
//				});
//			} catch (HttpStatusCodeException e) {
//				ApiError apiError = new ApiError();
//				apiError.setHttpStatus(HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS);
//				throw new CommonException("SERVICE UNAVAILABE");
//			}
//			return response.getBody();
//		}).collect(Collectors.groupingBy(orderNumber->orderNumber));
//		return collect;
		Map<String, List<String>> map = new HashMap<>();
		for(String orderNumber:orderNumbers) {
			ResponseEntity<List<String>> response;
			try {
				String uri=buidURi("orderNumber",orderNumber,"shipment-products");
				response= restTemplate.exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<List<String>>() {
				});
			} catch (HttpStatusCodeException e) {
				ApiError apiError = new ApiError();
				apiError.setHttpStatus(HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS);
				throw new CommonException("SERVICE UNAVAILABE");
			}
			map.put(orderNumber, response.getBody());
		}
		return map;
	}

	@Override
	public Map<String, String> getTrackProductStatus(List<String> orderNumbers) {
//		List<String> collect = orderNumbers.stream().map(orderNumber->{
//			String uri=buidURi("orderNumber",orderNumber, "track-status");
//			ResponseEntity<String> response;
//			try {
//				response= restTemplate.exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<String>() {});
//			} catch (HttpStatusCodeException e) {
//				ApiError apiError = new ApiError();
//				apiError.setHttpStatus(HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS);
//				throw new CommonException("SERVICE UNAVAILABE");
//			}
//			return response.getBody();
//		}).collect(Collectors.toList());
//		return collect;
		
		Map<String, String> map = new HashMap<>();
		for(String orderNumber:orderNumbers) {
			ResponseEntity<String> response;
			try {
				String uri=buidURi("orderNumber",orderNumber, "track-status");
				response= restTemplate.exchange(uri, HttpMethod.GET,
						null, new ParameterizedTypeReference<String>() { });
			} catch (HttpStatusCodeException e) {
				ApiError apiError = new ApiError();
				apiError.setHttpStatus(HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS);
				throw new CommonException("SERVICE UNAVAILABE");
			}
			map.put(orderNumber, response.getBody());
		}
		return map;
	}

	@Override
	public Map<String, String> getPriceOfProduct(List<String> countryCodes) {
//		List<String> collect = countryCodes.stream().map(countryCode->{
//			String uri=buidURi("countryCode",countryCode, "pricing");
//			ResponseEntity<String> response;
//			try {
//				response= restTemplate.exchange(uri, HttpMethod.GET,
//						null, new ParameterizedTypeReference<String>() { });
//			} catch (Exception e) {
//				ApiError apiError = new ApiError();
//				apiError.setHttpStatus(HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS);
//				throw new CommonException("SERVICE UNAVAILABE");
//			}
//			return response.getBody();
//		}).collect(Collectors.toList());
//		 return collect;
		 
		Map<String, String> map = new HashMap<>();
		for(String countryCode:countryCodes) {
			String uri=buidURi("countryCode",countryCode, "pricing");
			ResponseEntity<String> response;
			try {
				response= restTemplate.exchange(uri, HttpMethod.GET,
						null, new ParameterizedTypeReference<String>() { });
			} catch (Exception e) {
				ApiError apiError = new ApiError();
				apiError.setHttpStatus(HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS);
				throw new CommonException("SERVICE UNAVAILABE");
			}
			map.put(countryCode, response.getBody());
		}
		return map;
	}
	
 	private String buidURi(String queryParam, String value, String path) {
 		
 		return UriComponentsBuilder.newInstance().scheme("http").host("127.0.0.1:4000").path("/"+path).queryParam(queryParam,value).build().toString();
 	}

}
