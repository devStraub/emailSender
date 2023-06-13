package com.email.api.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class EmailService {
	
    @Autowired
    private JavaMailSender emailSender;	
	
	@SuppressWarnings("unchecked")
	public void sendEmail(String json) {
		
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, String> parametersMap = new HashMap<>();
		try {
			parametersMap = objectMapper.readValue(json, Map.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} 	
		
		if (parametersMap.get("TO") == null) {
			throw new RuntimeException("TO parameter empty");
		}	
		
		if (parametersMap.get("SUBJECT") == null) {
			throw new RuntimeException("SUBJECT parameter empty");
		}	
		
        SimpleMailMessage message = new SimpleMailMessage(); 
        message.setTo(parametersMap.get("TO")); 
        message.setSubject(parametersMap.get("SUBJECT")); 
        message.setText(parametersMap.get("TEXT"));
        emailSender.send(message);	
        
        System.out.println("e-mail sended");
	}
}
