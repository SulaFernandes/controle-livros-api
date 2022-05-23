package br.edu.utfpr.controlelivros.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.edu.utfpr.controlelivros.model.Usuario;
import br.edu.utfpr.controlelivros.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public Usuario alterar(Usuario usuario) {		
		return this.usuarioRepository.save(usuario);
		
	}

}
