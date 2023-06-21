package com.sk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sk.entity.Order;
import com.sk.service.ShoppingService;

@RestController
@RequestMapping("/order")
public class TestController {

	@Autowired
	private ShoppingService service;

	@PostMapping("/order")
	public ResponseEntity<String> insertOrderRecord(@RequestBody Order order) {
		try {
			String msg = service.insert(order);
			return new ResponseEntity<String>(msg, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Order Record is not Inserted", HttpStatus.OK);
		}
	}

	@GetMapping("/questions")
	public ResponseEntity<String> questionsExecution() {
//		service.questions();
		return new ResponseEntity<String>("Executed", HttpStatus.OK);
	}
}
