CREATE TABLE tb_situacao_compra (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    loja_compra VARCHAR(20),
    valor_livro DECIMAL(5,2),
	descricao VARCHAR(20) 
)ENGINE=InnoDB DEFAULT CHARSET=utf8;