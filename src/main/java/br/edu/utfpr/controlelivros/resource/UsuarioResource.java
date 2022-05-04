package br.edu.utfpr.controlelivros.resource;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.utfpr.controlelivros.event.RecursoCriadoEvent;
import br.edu.utfpr.controlelivros.model.Usuario;
import br.edu.utfpr.controlelivros.repository.UsuarioRepository;

@RestController
@RequestMapping("/usuarios")
public class UsuarioResource {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	public List<Usuario> listar() {
		
		return usuarioRepository.findAll();
	}
	
	@PostMapping
	public ResponseEntity<Usuario> novoUsuario(@RequestBody Usuario novoUsuario, HttpServletResponse response) {
		Usuario usuario = usuarioRepository.save(novoUsuario);
		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, usuario.getId()));
		 
		return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
		
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<?> buscarPorId(@PathVariable Long codigo) {
		
		Optional<Usuario> usuario = this.usuarioRepository.findById(codigo);
		return usuario.isPresent() ? ResponseEntity.ok(usuario) : ResponseEntity.notFound().build();
		
	}
	
	@DeleteMapping("/{codigo}")
	public void deletarUsuario(@PathVariable Long codigo) {
		usuarioRepository.deleteById(codigo);
	}

}