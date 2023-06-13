package com.email.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.email.api.service.EmailService;

@RestController
@RequestMapping(value = "/email")
public class EmailController {
	
	@Autowired
	private EmailService service;
	
	@PostMapping
	public ResponseEntity<String> sendEmail(@RequestBody String json){
		service.sendEmail(json);
		return ResponseEntity.ok().body("e-mail sended");
	}
}
