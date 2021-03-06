package br.edu.utfpr.controlelivros.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@Entity
@Table(name = "tb_situacao_compra")
public class SituacaoCompra {
	
	@EqualsAndHashCode.Include
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Size(min = 3, max = 20)
	@Column(name = "loja_compra")
	private String lojaCompra;
	
	@Column(name = "valor_livro")
	private double valorLivro;
	
	@Size(min = 3, max = 20)
	private String descricao;

}
