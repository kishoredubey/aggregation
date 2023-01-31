package org.aggregation.services.impl;

import java.util.List;

import org.aggregation.error.ApiError;
import org.aggregation.error.CommonException;
import org.aggregation.services.IBackendService;
import org.aggregation.util.IsoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
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
	public List<String> getShipmentProducts(String orderNumber) {
		String uri=buidURi("orderNumber",orderNumber,"shipment-products");
		ResponseEntity<List<String>> productForShipment= restTemplate.exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<List<String>>() {
		});
		return productForShipment.getBody();
	}

	@Override
	public String getTrackProductStatus(String orderNumber) {
		String uri=buidURi("orderNumber",orderNumber, "track-status");
		ResponseEntity<String> response;
		try {
			response= restTemplate.exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<String>() {});
		} catch (HttpStatusCodeException e) {
			ApiError apiError = new ApiError();
			apiError.setHttpStatus(e.getStatusCode());
			throw new CommonException("ERROR IN FETCHING DETAIL FROM BACKE END SERVICE");
		}
		return response.getBody();
	}

	@Override
	public Double getPriceOfProduct(String countryCode) {
		 if(StringUtils.isEmpty(countryCode) && !IsoUtil.isValidISOCountry(countryCode)) { 
			 throw new  CommonException("ERROR IN FETCHING DETAIL FROM BACKE END SERVICE"); 
		 }
		 String uri=buidURi("countryCode",countryCode, "pricing");
		 
		 ResponseEntity<Double> price= restTemplate.exchange(uri, HttpMethod.GET,
		 null, new ParameterizedTypeReference<Double>() { });
		 return price.getBody();
		
	}
	
 	private String buidURi(String queryParam, String value, String path) {
 		return UriComponentsBuilder.newInstance().scheme("http").host("127.0.0.1:4000").path("/"+path).queryParam(queryParam,value).build().toString();
 	}

}
