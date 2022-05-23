package br.edu.utfpr.controlelivros.resource;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.edu.utfpr.controlelivros.event.RecursoCriadoEvent;
import br.edu.utfpr.controlelivros.model.Livro;
import br.edu.utfpr.controlelivros.repository.LivroRepository;
import br.edu.utfpr.controlelivros.repository.filter.LivroFilter;
import br.edu.utfpr.controlelivros.service.LivroService;

@RestController
@RequestMapping("/livros")
public class LivroResource {
	
	@Autowired
	private LivroRepository livroRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private LivroService livroService;
	
	@GetMapping
	public Page<Livro> listar(LivroFilter livroFilter, Pageable pageable) {
		return livroRepository.filtrar(livroFilter, pageable);
	}
	
	@PostMapping
	public ResponseEntity<Livro> novoLivro(@Valid @RequestBody Livro novoLivro, HttpServletResponse response) {
		Livro livro = livroRepository.save(novoLivro);
		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, livro.getId()));
		 
		return ResponseEntity.status(HttpStatus.CREATED).body(livro);
		
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluirLivro(@PathVariable Long codigo) {
		livroRepository.deleteById(codigo);
		
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<Livro> atualizar(@PathVariable Long codigo, @Valid @RequestBody Livro livro) {
		if (!livroRepository.existsById(codigo)) {
			return ResponseEntity.notFound().build();
		}
		
		livro.setId(codigo);
		livro = livroService.alterar(livro);
		
		return ResponseEntity.ok(livro);
	}

}
