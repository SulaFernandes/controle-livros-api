package br.edu.utfpr.controlelivros.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.utfpr.controlelivros.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
