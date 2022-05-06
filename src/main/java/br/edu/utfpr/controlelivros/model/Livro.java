package br.edu.utfpr.controlelivros.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@Entity
@Table(name = "tb_livro")
public class Livro {
	
	@EqualsAndHashCode.Include
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "categoria_id")
	private Categoria categoria;
	
	@Enumerated(EnumType.STRING)
	private SituacaoLeitura stleitura;
	
	@ManyToOne
	@JoinColumn(name = "stcompra_id")
	private SituacaoCompra situacaoCompra;	
	
	@NotBlank
	@Size(min = 3, max = 50)
	private String titulo;
	
	@NotBlank
	@Size(min = 3, max = 30)
	private String autor;
	
	@Size(min = 3, max = 30)
	private String editora;
	
	@Size(min = 3, max = 20)
	private String volume;
	
	@Size(min = 3, max = 50)
	private String edicao;
	
	@NotBlank
	@Column(name = "quant_paginas")
	private int quantidadePaginas;
	
	@NotBlank
	@Column(name = "quant_livros")
	private int quantidadeLivros;
	
	@Size(min = 3, max = 350)
	private String sinopse;
	
	@Lob
	private byte[] imagem;

}
