CREATE TABLE tb_usuario (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(60) NOT NULL,
	login VARCHAR(255) NOT NULL,
    senha VARCHAR(8) NOT NULL
)ENGINE=InnoDB DEFAULT CHARSET=utf8;