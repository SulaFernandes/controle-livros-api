package br.edu.utfpr.controlelivros.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.utfpr.controlelivros.model.Livro;
import br.edu.utfpr.controlelivros.repository.LivroRepository;

@Service
public class LivroService {
	
	@Autowired
	private LivroRepository livroRepository;
	
	public Livro alterar(Livro livro) {
		return this.livroRepository.save(livro);
	}

}
