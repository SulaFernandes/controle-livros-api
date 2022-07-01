package br.edu.utfpr.controlelivros.security.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GeradorSenha {
	
	public static void main(String[] args) {
		
		BCryptPasswordEncoder bPasswordEncoder = new BCryptPasswordEncoder();
		System.out.println(bPasswordEncoder.encode("m@bil3"));
		
	}

}
