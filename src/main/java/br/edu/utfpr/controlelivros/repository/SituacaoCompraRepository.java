package br.edu.utfpr.controlelivros.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.utfpr.controlelivros.model.SituacaoCompra;

public interface SituacaoCompraRepository extends JpaRepository<SituacaoCompra, Long> {

}
