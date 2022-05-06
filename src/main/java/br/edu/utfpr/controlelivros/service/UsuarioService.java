package br.edu.utfpr.controlelivros.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.edu.utfpr.controlelivros.model.Usuario;
import br.edu.utfpr.controlelivros.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public Usuario alterar(@PathVariable Long codigo, @RequestBody Usuario novoUsario) {
		
		Usuario usuario = this.usuarioRepository.findById(codigo)
				.orElseThrow(() -> new EmptyResultDataAccessException(1));
		BeanUtils.copyProperties(novoUsario, usuario, "codigo");
		
		return this.usuarioRepository.save(usuario);
		
	}

}
