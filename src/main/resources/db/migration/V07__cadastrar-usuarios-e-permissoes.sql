INSERT INTO tb_usuario (id, nome, login, senha) values (1, 'Sula Fernandes', 'sula.fer@gmail.com', '12sm34#');
INSERT INTO tb_usuario (id, nome, login, senha) values (2, 'Pedro Terra', 'pedroterr@gmail.com', '4a5ff23');

INSERT INTO tb_permissao (id, descricao) values (1, 'ROLE_CADASTRAR_CATEGORIA');
INSERT INTO tb_permissao (id, descricao) values (2, 'ROLE_REMOVER_CATEGORIA');
INSERT INTO tb_permissao (id, descricao) values (3, 'ROLE_PESQUISAR_CATEGORIA');

INSERT INTO tb_permissao (id, descricao) values (4, 'ROLE_CADASTRAR_LIVRO');
INSERT INTO tb_permissao (id, descricao) values (5, 'ROLE_PESQUISAR_LIVRO');
INSERT INTO tb_permissao (id, descricao) values (6, 'ROLE_ATUALIZAR_LIVRO');
INSERT INTO tb_permissao (id, descricao) values (7, 'ROLE_REMOVER_LIVRO');

INSERT INTO tb_permissao (id, descricao) values (8, 'ROLE_CADASTRAR_SITUACAO_COMPRA');
INSERT INTO tb_permissao (id, descricao) values (9, 'ROLE_PESQUISAR_SITUACAO_COMPRA');
INSERT INTO tb_permissao (id, descricao) values (10, 'ROLE_ATUALIZAR_SITUACAO_COMPRA');
INSERT INTO tb_permissao (id, descricao) values (11, 'ROLE_REMOVER_SITUACAO_COMPRA');

INSERT INTO tb_usuario_permissao (id_usuario, id_permissao) values (1, 1);
INSERT INTO tb_usuario_permissao (id_usuario, id_permissao) values (1, 3);
INSERT INTO tb_usuario_permissao (id_usuario, id_permissao) values (1, 4);
INSERT INTO tb_usuario_permissao (id_usuario, id_permissao) values (1, 5);
INSERT INTO tb_usuario_permissao (id_usuario, id_permissao) values (1, 6);
INSERT INTO tb_usuario_permissao (id_usuario, id_permissao) values (1, 7);
INSERT INTO tb_usuario_permissao (id_usuario, id_permissao) values (1, 8);
INSERT INTO tb_usuario_permissao (id_usuario, id_permissao) values (1, 9);
INSERT INTO tb_usuario_permissao (id_usuario, id_permissao) values (1, 10);
INSERT INTO tb_usuario_permissao (id_usuario, id_permissao) values (1, 11);

INSERT INTO tb_usuario_permissao (id_usuario, id_permissao) values (2, 1);
INSERT INTO tb_usuario_permissao (id_usuario, id_permissao) values (2, 3);
INSERT INTO tb_usuario_permissao (id_usuario, id_permissao) values (2, 4);
INSERT INTO tb_usuario_permissao (id_usuario, id_permissao) values (2, 5);
INSERT INTO tb_usuario_permissao (id_usuario, id_permissao) values (2, 6);
INSERT INTO tb_usuario_permissao (id_usuario, id_permissao) values (2, 7);
INSERT INTO tb_usuario_permissao (id_usuario, id_permissao) values (2, 8);
INSERT INTO tb_usuario_permissao (id_usuario, id_permissao) values (2, 9);
INSERT INTO tb_usuario_permissao (id_usuario, id_permissao) values (2, 10);
INSERT INTO tb_usuario_permissao (id_usuario, id_permissao) values (2, 11);





