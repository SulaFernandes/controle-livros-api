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
	
	private String titulo;
	private String autor;
	private String editora;
	private String volume;
	private String edicao;
	
	@Column(name = "quant_paginas")
	private int quantidadePaginas;
	
	@Column(name = "quant_livros")
	private int quantidadeLivros;
	private String sinopse;
	
	@Lob
	private byte[] imagem;

}
