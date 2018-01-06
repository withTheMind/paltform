package com.paltform.admin.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Scope("prototype")
public class TestController {
	
	
	@RequestMapping("/test")
	public Map<String, Object> test(){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("test", "hello  world");
		
		return map;
	}
	
}
