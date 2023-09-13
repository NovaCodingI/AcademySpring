package com.ikik.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class TestController {
	
	@PostMapping(value = "/android", consumes = "application/json")
    public void receiveLocation(@RequestBody String locationData) {
        System.out.println("Received Location Data: " + locationData);
    }
}
