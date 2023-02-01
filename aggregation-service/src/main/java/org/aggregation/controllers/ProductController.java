package org.aggregation.controllers;

import java.util.List;
import java.util.Map;

import org.aggregation.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/")
public class ProductController {
	
	@Autowired
	private IProductService productService;
	
	@GetMapping(value = "/getPrice/{countryCode}", produces = "application/json")
	public ResponseEntity<Map<String, String>> getProductPrice(@RequestParam List<String> countryCodes){
		Map<String, String> price = productService.getPrice(countryCodes);
		return new ResponseEntity<>(price, HttpStatus.OK);
	}
	
	@GetMapping(value = "/getShipment/{orderNumber}", produces = "application/json")
	public ResponseEntity<Map<String, List<String>>> getOrderShipment(@RequestParam List<String> orderNumbers){
		Map<String, List<String>>ordershipment= productService.getShipmentProducts(orderNumbers);
		return new ResponseEntity<>(ordershipment, HttpStatus.OK);
	}
	
	@GetMapping(value = "/getStatus/{orderNumber}", produces = "application/json")
	public ResponseEntity<Map<String, String>> getOrderStatus(@RequestParam List<String> orderNumbers){
		Map<String, String> status=productService.getStatus(orderNumbers);
		return new ResponseEntity<>(status, HttpStatus.OK);
	}

}
