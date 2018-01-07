package com.paltform.test;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.platform.entity.Member;
import com.platform.util.httpclient.HttpClientHelper;

public class MemberTest {
	
	
	@Test
	public void test(){
		
		Member m = new Member();
		m.setPhone("15082037343");
		m.setPassword("123456");
		Map<String, String> map = new HashMap<String, String>();
		
		map.put("phone", "15082037343");
		map.put("password", "123456");
		
		String content = HttpClientHelper.post("http://localhost:8888/mem/loginValide", map);
		
		System.out.println(content);
	}
	
}
