package org.aggregation.controllers;

import java.util.List;

import org.aggregation.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/")
public class ProductController {
	
	@Autowired
	private IProductService productService;
	
	@GetMapping(value = "/getPrice/{countryCode}", produces = "application/json")
	public ResponseEntity<Double> getProductPrice(@PathVariable String countryCode){
		Double price = productService.getPrice(countryCode);
		return new ResponseEntity<>(price, HttpStatus.OK);
	}
	
	@GetMapping(value = "/getShipment/{orderNumber}", produces = "application/json")
	public ResponseEntity<List<String>> getOrderShipment(@PathVariable String orderNumber){
		List<String> ordershipment= productService.getShipmentProducts(orderNumber);
		return new ResponseEntity<>(ordershipment, HttpStatus.OK);
	}
	
	@GetMapping(value = "/getStatus/{orderNumber}", produces = "application/json")
	public ResponseEntity<String> getOrderStatus(@PathVariable String orderNumber){
		String status=productService.getStatus(orderNumber);
		return new ResponseEntity<>(status, HttpStatus.OK);
	}

}
