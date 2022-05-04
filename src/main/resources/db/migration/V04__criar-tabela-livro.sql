CREATE TABLE tb_livro (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	categoria_id BIGINT(20) NOT NULL,
	stcompra_id BIGINT(20) NOT NULL,
	titulo VARCHAR(50) NOT NULL,
	autor VARCHAR(30) NOT NULL,
	editora VARCHAR(30),
	volume VARCHAR(20),
	edicao VARCHAR(50),
	quant_paginas INT NOT NULL,
	quant_livros INT NOT NULL,
	stleitura VARCHAR(30) NOT NULL,
	sinopse VARCHAR(350),
	imagem BLOB  
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE tb_livro ADD CONSTRAINT fk_livro_categoria
FOREIGN KEY (categoria_id) REFERENCES tb_categoria(id);

ALTER TABLE tb_livro ADD CONSTRAINT fk_livro_situacao_compra
FOREIGN KEY (stcompra_id) REFERENCES tb_situacao_compra(id);