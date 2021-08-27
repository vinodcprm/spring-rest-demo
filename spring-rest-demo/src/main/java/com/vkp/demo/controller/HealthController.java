package com.vkp.demo.controller;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vkp.demo.model.HealthResponse;

@RestController
public class HealthController {
	
	@GetMapping("/echo")
	public String echo(@RequestParam String message) {
		return "Hello "+message;
	}
	
	@GetMapping("/health")
	public ResponseEntity<HealthResponse> health(@RequestParam String format){
		if("short".equals(format)) {
			HealthResponse healthResponse = new HealthResponse("OK");
			return new ResponseEntity<>(healthResponse, HttpStatus.OK);
		} else if("long".equals(format)) {
			HealthResponse healthResponse = new HealthResponse("OK", new Date());
			return new ResponseEntity<>(healthResponse, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}	
	}

}
