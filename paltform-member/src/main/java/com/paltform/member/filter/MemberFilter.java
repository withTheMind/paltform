package com.paltform.member.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.platform.entity.Member;
import com.platform.util.ajax.Result;

@WebFilter(filterName = "memberFilter", urlPatterns = {"/member/*"})
public class MemberFilter implements Filter{
	
	private static Logger logger = LoggerFactory.getLogger(MemberFilter.class);

	@Override
	public void destroy() {
		logger.info("destroy");
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		//转换
		HttpServletRequest request = (HttpServletRequest)arg0;
		HttpServletResponse response = (HttpServletResponse)arg1;
		//session获取会员信息
		Member member = (Member) request.getSession().getAttribute("member");
		if(member != null){
			//不拦截
			arg2.doFilter(arg0, arg1);
		}
		else{
			logger.info("未登录或登录过期");
			//请求头
			String header = request.getHeader("X-Requested-With");
			/*//请求路径
			String url = request.getRequestURL().toString();
			Map<String, String> paramsMap = new HashMap<String, String>();
			//获取所有的请求参数
			Enumeration<String> em = request.getParameterNames();
			while(em.hasMoreElements()){
				String name = em.nextElement();
				paramsMap.put(name, request.getParameter(name));
			}
			
			//设置过期时间
			//将请求信息存入redis
			RedisUtil.set(sessionId+":url", url);
			if(!paramsMap.isEmpty() && paramsMap.size() > 0){
				RedisUtil.hmset(sessionId+":data", paramsMap);
			}*/
			
			//异步请求
			if("XMLHttpRequest".equalsIgnoreCase(header)){
				//响应
				response.setContentType("application/json;charset=UTF-8");
				PrintWriter pw = response.getWriter();
				Result result = new Result();
				result.setStatus(403);//未登录
				result.setMessage("登录过期，请重新登录");
				String jsonStr = JSON.toJSONString(result);
				pw.println(jsonStr);
			}
			//同步请求
			else{
				response.sendRedirect("/notLogin");
			}
		}
		
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		logger.info("init");
	}
	
	
	
}

