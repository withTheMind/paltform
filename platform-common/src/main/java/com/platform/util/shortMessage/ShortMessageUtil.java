package com.platform.util.shortMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * 短信发送
 * 网建  url:http://sms.webchinese.com.cn/default.shtml
 * @author Administrator
 *
 */
public class ShortMessageUtil {
	
		//用户名
		private final static String Uid = "yhi1993";
		
		//接口安全秘钥
		private final static String Key = "5802bf8c5e3c5bc46c48";
		
//		//手机号码，多个号码如13800000000,13800000001,13800000002
//		private static String smsMob = "15082037343";
//		
//		//短信内容
//		private static String smsText = "验证码：8888";
		
		
		/**
		 * 短信单个发送
		 * @param smsMob
		 * @param smsText
		 * @return
		 */
		public static int sendMessage(String smsMob, String smsText){
			HttpClientUtil client = HttpClientUtil.getInstance();
			//UTF发送
			int result = client.sendMsgUtf8(Uid, Key, smsText, smsMob);
			return result;
		}
		
		/**
		 * 短信群发
		 * @param smsMobs
		 * @param smsText
		 * @return
		 */
		public static int sendMessage(List<String> smsMobs, String smsText){
			String phones = "";
			for(String smsMob : smsMobs){
				phones += smsMob + ",";
			}
			phones = phones.substring(0, phones.length()-1);
			HttpClientUtil client = HttpClientUtil.getInstance();
			//UTF发送
			int result = client.sendMsgUtf8(Uid, Key, smsText, phones);
			return result;
		}
		
		
		/**
		 * 群发测试
		 * @param args
		 */
		public static void main(String[] args) {
			List<String> list = new ArrayList<String>();
			list.add("15082037343");
			list.add("15023682232");
			list.add("17088361730");
			String content = "短信验证码为：888888，好吉利的数字";
			int count = sendMessage(list, content);
			System.out.println("条数:" + count);
			
		}
	
}
