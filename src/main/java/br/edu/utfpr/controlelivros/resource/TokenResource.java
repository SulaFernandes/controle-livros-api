package br.edu.utfpr.controlelivros.resource;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tokens")
public class TokenResource {
	
	@DeleteMapping("/revoke")
	private void revoke(HttpServletRequest hRequest, HttpServletResponse hResponse) {
		Cookie cookie = new Cookie("refreshToken", null);
		cookie.setHttpOnly(true);
		cookie.setSecure(false);
		cookie.setPath(hRequest.getContextPath() + "/oauth/token");
		cookie.setMaxAge(0);
		
		hResponse.addCookie(cookie);
		hResponse.setStatus(HttpStatus.NO_CONTENT.value());

	}
}
