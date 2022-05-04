package br.edu.utfpr.controlelivros.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SituacaoLeitura {
	
	NAO_LIDO("Não lido"),
	EM_PROGRESSO("Leitura em progresso"),
	LIDO("Leitura concluída");
	
	private String descricao;

}
