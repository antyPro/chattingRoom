package com.aidic.springboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Hello world!
 *
 */

@RestController
public class TestController 
{
    @GetMapping("/hello")
    public String helloworld() {
    	
    	return "hello aidic111~";
    }
}
