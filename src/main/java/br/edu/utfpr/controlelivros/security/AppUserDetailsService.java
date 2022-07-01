package br.edu.utfpr.controlelivros.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.edu.utfpr.controlelivros.model.Usuario;
import br.edu.utfpr.controlelivros.repository.UsuarioRepository;

@Service
public class AppUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<Usuario> optional =  usuarioRepository.findByLogin(username);
		Usuario usuario = optional.orElseThrow(() -> new UsernameNotFoundException("Usuário ou senha incorretos"));
		
		return new User(username, usuario.getSenha(), getPermissoes(usuario));
	}

	private Collection<? extends GrantedAuthority> getPermissoes(Usuario usuario) {
		
		Set<SimpleGrantedAuthority> sAuthorities = new HashSet<>();
		usuario.getListaPermissao().forEach(p -> sAuthorities.add(new SimpleGrantedAuthority(p.getDescricao().toUpperCase())));
		return sAuthorities;
	}

}
