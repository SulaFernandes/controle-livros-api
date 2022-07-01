package br.edu.utfpr.controlelivros.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@Entity
@Table(name = "tb_usuario")
public class Permissao {
	
	@EqualsAndHashCode.Include
	@Id
	private Long id;
	private String descricao;

}
