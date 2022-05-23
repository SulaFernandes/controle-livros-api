package br.edu.utfpr.controlelivros.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.utfpr.controlelivros.model.Categoria;
import br.edu.utfpr.controlelivros.repository.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Categoria alterar(Categoria categoria) {
		return this.categoriaRepository.save(categoria);
		
	}
	

}
