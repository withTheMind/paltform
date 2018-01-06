package com.platform.util.regex;

import java.util.regex.Pattern;

/**
 * 正则表达式验证
 * @author Administrator
 *
 */
public class RegexUtil {
	
	/**
	 * 手机号
	 */
	public static final String MOBILE_PATTREN = "^1[34578]\\d{9}$";
	
	/**
	 * 邮箱
	 */
	public static final String EMAIL_PATTERN = "^(\\w-*\\.*)+@(\\w-?)+(\\.\\w{2,})+$";
	
	/**
	 * qq
	 */
	public static final String QQ_PATTERN = "^\\d{6,10}$";
	
	/**
	 * qq正则
	 * @param qq qq
	 * @return
	 */
	public static boolean qq(String qq){
		return regex(QQ_PATTERN, qq);
	}
	
	/**
	 * 邮箱正则
	 * @param email 邮箱
	 * @return
	 */
	public static boolean email(String email){
		return regex(EMAIL_PATTERN, email);
	}
	
	/**
	 * 验证手机号
	 * @param mobile 手机号
	 * @return
	 */
	public static boolean mobile(String mobile){
		return regex(MOBILE_PATTREN, mobile);
	}
	
	public static boolean regex(String pattern, String value){
		Pattern patten = Pattern.compile(pattern);
		return patten.matcher(value).find();
	}
	
}
