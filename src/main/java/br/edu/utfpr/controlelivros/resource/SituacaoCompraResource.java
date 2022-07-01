package br.edu.utfpr.controlelivros.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.edu.utfpr.controlelivros.event.RecursoCriadoEvent;
import br.edu.utfpr.controlelivros.model.SituacaoCompra;
import br.edu.utfpr.controlelivros.repository.SituacaoCompraRepository;

@RestController
@RequestMapping("/stcompras")
public class SituacaoCompraResource {
	
	@Autowired
	private SituacaoCompraRepository sCompraRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_SITUACAO_COMPRA') and #oauth2.hasScope('read')")
	public List<SituacaoCompra> listar() {
		return sCompraRepository.findAll();
	}
	
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_SITUACAO_COMPRA') and #oauth2.hasScope('write')")
	public ResponseEntity<SituacaoCompra> novaSitCompra(@Valid @RequestBody SituacaoCompra novaSitCompra, HttpServletResponse response) {
		SituacaoCompra sCompra = sCompraRepository.save(novaSitCompra);
		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, sCompra.getId()));
		 
		return ResponseEntity.status(HttpStatus.CREATED).body(sCompra);
		
	}
	
	@DeleteMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_REMOVER_SITUACAO_COMPRA') and #oauth2.hasScope('write')")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletarSitCompra(@PathVariable Long codigo) {
		sCompraRepository.deleteById(codigo);
	}


}
