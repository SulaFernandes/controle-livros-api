package br.edu.utfpr.controlelivros.token;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.catalina.util.ParameterMap;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RefreshTokenCookiePreProcessorFilter implements Filter {
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest hRequest = (HttpServletRequest) request;
		
		if("/oauth/token".equalsIgnoreCase(hRequest.getRequestURI())
				&& "refresh_token".equals(hRequest.getParameter("grant_type")) && hRequest.getCookies() != null) {
			
			for(Cookie cookie : hRequest.getCookies()) {
				if(cookie.getName().equals("refreshToken")) {
					String refreshToken = cookie.getValue();
					hRequest = new MyServletRequestWrapper(hRequest, refreshToken);
				}
			}
		}
		
		chain.doFilter(hRequest, response);
		
	}
	
	static class MyServletRequestWrapper extends HttpServletRequestWrapper {
		
		private String refreshToken;
		
		public MyServletRequestWrapper(HttpServletRequest request, String refreshToken) {
			super(request);
			this.refreshToken = refreshToken;
		}
		
		@Override
		public Map<String, String[]> getParameterMap() {
			
			ParameterMap<String, String[]> pMap = new ParameterMap<>(getRequest().getParameterMap());
			pMap.put("refresh_token", new String[] { refreshToken });		
			pMap.setLocked(true);
			
			return pMap;
		}
		
		
		
	}

}
