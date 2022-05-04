package br.edu.utfpr.controlelivros.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

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
import br.edu.utfpr.controlelivros.model.Livro;
import br.edu.utfpr.controlelivros.repository.LivroRepository;

@RestController
@RequestMapping("/livros")
public class LivroResource {
	
	@Autowired
	private LivroRepository livroRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	public List<Livro> listar() {
		return livroRepository.findAll();
	}
	
	@PostMapping
	public ResponseEntity<Livro> novoLivro(@Valid @RequestBody Livro novoLivro, HttpServletResponse response) {
		Livro livro = livroRepository.save(novoLivro);
		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, livro.getId()));
		 
		return ResponseEntity.status(HttpStatus.CREATED).body(livro);
		
	}
	
	@DeleteMapping("/{codigo}")
	public ResponseEntity<Void> excluirLivro(@PathVariable Long codigo) {
		if(!livroRepository.existsById(codigo)) {
			return ResponseEntity.notFound().build(); //404
		}
		livroRepository.deleteById(codigo);
		return ResponseEntity.noContent().build(); //204
	}

}
