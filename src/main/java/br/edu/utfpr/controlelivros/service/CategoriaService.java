package br.edu.utfpr.controlelivros.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.edu.utfpr.controlelivros.model.Categoria;
import br.edu.utfpr.controlelivros.repository.CategoriaRepository;

@Service
public class CategoriaService {
	//REGRA DE NEGOCIO
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Categoria alterar(@PathVariable Long codigo, @RequestBody Categoria novaCategoria) {
		
		Categoria categoria = this.categoriaRepository.findById(codigo)
				.orElseThrow(() -> new EmptyResultDataAccessException(1)); //lança excessao caso não encontre
		BeanUtils.copyProperties(novaCategoria, categoria, "codigo"); 
		
		categoria.setId(codigo);
		
		return this.categoriaRepository.save(categoria);
	}
	

}
