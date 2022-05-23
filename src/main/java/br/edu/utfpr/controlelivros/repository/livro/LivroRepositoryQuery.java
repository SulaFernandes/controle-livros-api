package br.edu.utfpr.controlelivros.repository.livro;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.edu.utfpr.controlelivros.model.Livro;
import br.edu.utfpr.controlelivros.repository.filter.LivroFilter;

public interface LivroRepositoryQuery {
	
	public Page<Livro> filtrar(LivroFilter livroFilter, Pageable pageable);

}
