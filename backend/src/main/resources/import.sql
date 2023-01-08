INSERT INTO tb_mae (cpf, nome, data_nascimento, telefone, endereco, numero, bairro, complemento, uf, cep) VALUES ('56789012345', 'Kaline Santana Santos Guimarães', '1992-08-18', '73981153915', 'Rua Peroba', 69, 'Cacique', 'Casa', 'ES', '29932140');

INSERT INTO tb_ficha_paciente (cpf_paciente, tipo_sanguineo, soro_positivo, hipertensao, diabetes, medicacao_controlada, peso, altura) VALUES ('56789012345', 'O+', 'NÃO', 'NÃO', 'NÃO', 'NÃO', 60.0, 1.58);

INSERT INTO tb_recem_nascido (nome, data_nascimento, sexo, peso, altura, condicao, cpf_pai, cpf_mae) VALUES ('Ágata Santana Guimarães', '2023-03-20 10:20:30', 'F', 3.50, 40.0, 'SAUDÁVEL', '04226648552', '56789012345');

INSERT INTO tb_especialidade (nome) VALUES ('Obstetra');
INSERT INTO tb_especialidade (nome) VALUES ('Anestesista');
INSERT INTO tb_especialidade (nome) VALUES ('Pediatra');

INSERT INTO tb_funcionario (cpf, nome, email, password, status) VALUES ('12345678901', 'Bruno Guimarães', 'bruno@gmail.com', '$2a$10$hwNOMuZtGLTdSIo9yimcvenZdPpei6wemQhS2hYt3eYjr1jo6BWPW', 'ATIVO');
INSERT INTO tb_funcionario (cpf, nome, email, password, status) VALUES ('23456789012', 'João Silva', 'joao@gmail.com', '$2a$10$hwNOMuZtGLTdSIo9yimcvenZdPpei6wemQhS2hYt3eYjr1jo6BWPW', 'ATIVO');
INSERT INTO tb_funcionario (cpf, nome, email, password, status) VALUES ('34567890123', 'Maria Souza', 'maria@gmail.com', '$2a$10$hwNOMuZtGLTdSIo9yimcvenZdPpei6wemQhS2hYt3eYjr1jo6BWPW', 'ATIVO');
INSERT INTO tb_funcionario (cpf, nome, email, password, status) VALUES ('45678901231', 'Fernanda Cruz', 'fernanda@gmail.com', '$2a$10$hwNOMuZtGLTdSIo9yimcvenZdPpei6wemQhS2hYt3eYjr1jo6BWPW', 'ATIVO');
INSERT INTO tb_funcionario (cpf, nome, email, password, status) VALUES ('78901234567', 'Roberta Santos', 'roberta@gmail.com', '$2a$10$hwNOMuZtGLTdSIo9yimcvenZdPpei6wemQhS2hYt3eYjr1jo6BWPW', 'ATIVO');
INSERT INTO tb_funcionario (cpf, nome, email, password, status) VALUES ('89012345678', 'Gabriel Fonseca', 'gabriel@gmail.com', '$2a$10$hwNOMuZtGLTdSIo9yimcvenZdPpei6wemQhS2hYt3eYjr1jo6BWPW', 'ATIVO');
INSERT INTO tb_funcionario (cpf, nome, email, password, status) VALUES ('11111111111', 'Pedro Barizon', 'pedro@gmail.com', '$2a$10$hwNOMuZtGLTdSIo9yimcvenZdPpei6wemQhS2hYt3eYjr1jo6BWPW', 'ATIVO');
INSERT INTO tb_funcionario (cpf, nome, email, password, status) VALUES ('00000000000', 'Admin Admin', 'admin@gmail.com', '$2a$10$hwNOMuZtGLTdSIo9yimcvenZdPpei6wemQhS2hYt3eYjr1jo6BWPW', 'ATIVO');

INSERT INTO tb_medico(cpf_funcionario, crm, especialidade_id) VALUES ('12345678901', 'BA123456', 1);
INSERT INTO tb_medico(cpf_funcionario, crm, especialidade_id) VALUES ('45678901231', 'BA234568', 3);

INSERT INTO tb_enfermeiro(cpf_funcionario, inscricao_coren) VALUES ('23456789012', 'BA123456T1');
INSERT INTO tb_enfermeiro(cpf_funcionario, inscricao_coren) VALUES ('34567890123', 'BA123456T3');

INSERT INTO tb_perfil(autoridade) VALUES ('ROLE_NIVEL_1');
INSERT INTO tb_perfil(autoridade) VALUES ('ROLE_NIVEL_2');
INSERT INTO tb_perfil(autoridade) VALUES ('ROLE_NIVEL_3');

INSERT INTO tb_funcionario_perfil(cpf_funcionario, perfil_id) VALUES ('11111111111', 1);
INSERT INTO tb_funcionario_perfil(cpf_funcionario, perfil_id) VALUES ('12345678901', 1);
INSERT INTO tb_funcionario_perfil(cpf_funcionario, perfil_id) VALUES ('12345678901', 2);
INSERT INTO tb_funcionario_perfil(cpf_funcionario, perfil_id) VALUES ('45678901231', 1);
INSERT INTO tb_funcionario_perfil(cpf_funcionario, perfil_id) VALUES ('45678901231', 2);
INSERT INTO tb_funcionario_perfil(cpf_funcionario, perfil_id) VALUES ('23456789012', 1);
INSERT INTO tb_funcionario_perfil(cpf_funcionario, perfil_id) VALUES ('23456789012', 2);
INSERT INTO tb_funcionario_perfil(cpf_funcionario, perfil_id) VALUES ('34567890123', 1);
INSERT INTO tb_funcionario_perfil(cpf_funcionario, perfil_id) VALUES ('34567890123', 2);
INSERT INTO tb_funcionario_perfil(cpf_funcionario, perfil_id) VALUES ('78901234567', 1);
INSERT INTO tb_funcionario_perfil(cpf_funcionario, perfil_id) VALUES ('89012345678', 2);
INSERT INTO tb_funcionario_perfil(cpf_funcionario, perfil_id) VALUES ('00000000000', 1);
INSERT INTO tb_funcionario_perfil(cpf_funcionario, perfil_id) VALUES ('00000000000', 2);
INSERT INTO tb_funcionario_perfil(cpf_funcionario, perfil_id) VALUES ('00000000000', 3);

INSERT INTO tb_equipe_parto(doula) VALUES ('SIM');

INSERT INTO tb_equipe_medica(crm, equipe_parto_id) VALUES ('BA123456', 1);
INSERT INTO tb_equipe_medica(crm, equipe_parto_id) VALUES ('BA234568', 1);

INSERT INTO tb_equipe_enfermagem(coren, equipe_parto_id) VALUES ('BA123456T1', 1);
INSERT INTO tb_equipe_enfermagem(coren, equipe_parto_id) VALUES ('BA123456T3', 1);

INSERT INTO tb_parto(cpf_mae, data_parto, equipe_parto_id, tipo_parto, parto_risco, multifetal, observacao) VALUES ('56789012345', '2023-03-20 10:20:30', 1, 'NORMAL', 'NÃO', 'NÃO', 'Parto sem complicações');


