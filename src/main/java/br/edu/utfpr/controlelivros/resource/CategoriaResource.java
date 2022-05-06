package br.edu.utfpr.controlelivros.resource;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import br.edu.utfpr.controlelivros.repository.CategoriaRepository;
import br.edu.utfpr.controlelivros.service.CategoriaService;
import br.edu.utfpr.controlelivros.event.RecursoCriadoEvent;
import br.edu.utfpr.controlelivros.model.Categoria;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private CategoriaService categoriaService;
	
	@GetMapping
	public List<Categoria> listar() {
		return categoriaRepository.findAll();
	}
	
	@PostMapping
	public ResponseEntity<Categoria> novaCategoria(@Valid @RequestBody Categoria novaCategoria, HttpServletResponse response) {
		Categoria categoria = categoriaRepository.save(novaCategoria);
		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, categoria.getId()));
		 
		return ResponseEntity.status(HttpStatus.CREATED).body(categoria);
		
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<?> buscarPorId(@PathVariable Long codigo) {
		
		Optional<Categoria> categoria = this.categoriaRepository.findById(codigo);
		return categoria.isPresent() ? ResponseEntity.ok(categoria) : ResponseEntity.notFound().build();
		
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletarCategoria(@PathVariable Long codigo) {
		
		categoriaRepository.deleteById(codigo);
		 
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<Categoria> atualizar(@PathVariable Long codigo, @Valid @RequestBody Categoria categoria) {
		try {
			
			Categoria categoriaSalva = categoriaService.alterar(codigo, categoria);
			return ResponseEntity.ok(categoriaSalva);
		}
		catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
		
	}

}
