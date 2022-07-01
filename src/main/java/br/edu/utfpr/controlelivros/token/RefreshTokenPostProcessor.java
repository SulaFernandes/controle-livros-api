package br.edu.utfpr.controlelivros.token;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import br.edu.utfpr.controlelivros.config.property.ControleLivrosApiProperty;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
 
@ControllerAdvice
public class RefreshTokenPostProcessor implements ResponseBodyAdvice<OAuth2AccessToken> {
	
	//@Autowired
	private ControleLivrosApiProperty cLivrosApiProperty = new ControleLivrosApiProperty();
	
	@Override
	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
		
		return returnType.getMethod().getName().equals("postAccessToken");
	}
	
	@Override
	public OAuth2AccessToken beforeBodyWrite(OAuth2AccessToken body, MethodParameter returnType,
			MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType,
			ServerHttpRequest request, ServerHttpResponse response) {
		
		HttpServletRequest hServletRequest = ((ServletServerHttpRequest) request).getServletRequest();
		HttpServletResponse hServletResponse = ((ServletServerHttpResponse) response).getServletResponse();
		
		DefaultOAuth2AccessToken dAccessToken = (DefaultOAuth2AccessToken)body;
		
		String refreshToken = body.getRefreshToken().getValue();
		adicionarRefreshTokenNoCookie(refreshToken, hServletRequest, hServletResponse);
		removerRefreshTokenDoBody(dAccessToken);
		
		return body;
	}

	private void removerRefreshTokenDoBody(DefaultOAuth2AccessToken dAccessToken) {
		dAccessToken.setRefreshToken(null);
		
	}

	private void adicionarRefreshTokenNoCookie(String refreshToken, HttpServletRequest hServletRequest,
			HttpServletResponse hServletResponse) {
		
		Cookie refreshTokenCookie = new Cookie("refreshToken", refreshToken);
		refreshTokenCookie.setHttpOnly(true);
		refreshTokenCookie.setSecure(cLivrosApiProperty.getSeguranca().isEnableHttps());
		refreshTokenCookie.setPath(hServletRequest.getContextPath() + "/oauth/token");
		refreshTokenCookie.setMaxAge(2592000);
		hServletResponse.addCookie(refreshTokenCookie);
		
	}
	

}
