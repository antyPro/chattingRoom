package com.aidic.springboot.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {
	
	@RequestMapping("freemarker")
	public String freemarker(Map<Object, Object> temp) {
		temp.put("msg", "hello freemarker!");
		//返回到hello.ftl模板
		return "hello";
	}
}
