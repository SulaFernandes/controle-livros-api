package br.edu.utfpr.controlelivros.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.utfpr.controlelivros.model.Livro;
import br.edu.utfpr.controlelivros.repository.livro.LivroRepositoryQuery;

public interface LivroRepository extends JpaRepository<Livro, Long>, LivroRepositoryQuery {

}
