package com.platform.util;

public class RandomUtil {
	
	/**
	 * 产生6位为随机数
	 * @return
	 */
	public static String random(){
		String str = "";
		for(int i = 0; i < 6; i++){
			int num = (int)(Math.random()*10);
			str += num;
			
		}
		return str;
	}
	
	
}
