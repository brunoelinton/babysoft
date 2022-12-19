INSERT INTO tb_mae (cpf, nome, data_nascimento, telefone, endereco, numero, bairro, complemento, uf, cep) VALUES ('56789012345', 'Kaline Santana Santos Guimarães', '1992-08-18', '73981153915', 'Rua Peroba', 69, 'Cacique', 'Casa', 'ES', '29932140');
INSERT INTO tb_ficha_paciente (cpf_paciente, tipo_sanguineo, soro_positivo, hipertensao, diabetes, medicacao_controlada, peso, altura) VALUES ('56789012345', 'O+', 'NÃO', 'NÃO', 'NÃO', 'NÃO', 60.0, 1.58);
INSERT INTO tb_recem_nascido (nome, data_nascimento, sexo, peso, altura, condicao, cpf_pai, cpf_mae) VALUES ('Ágatah Santana Guimarães', '2023-03-20 10:20:30', 'F', 3.50, 40.0, 'SAUDÁVEL', '04226648552', '56789012345');
INSERT INTO tb_especialidade (nome) VALUES ('Obstetra');
INSERT INTO tb_especialidade (nome) VALUES ('Anestesista');
INSERT INTO tb_especialidade (nome) VALUES ('Pediatra');
INSERT INTO tb_funcionario (cpf, nome, email, senha, status) VALUES ('12345678901', 'Bruno Guimarães', 'bruno@gmail.com', '123456', 'ATIVO');
INSERT INTO tb_funcionario (cpf, nome, email, senha, status) VALUES ('23456789012', 'João Silva', 'joao@gmail.com', '123456', 'ATIVO');
INSERT INTO tb_funcionario (cpf, nome, email, senha, status) VALUES ('34567890123', 'Maria Souza', 'maria@gmail.com', '123456', 'ATIVO');
INSERT INTO tb_medico(cpf_funcionario, crm, especialidade_id) VALUES ('12345678901', 'BA123456', 1);
INSERT INTO tb_enfermeiro(cpf_funcionario, inscricao_coren) VALUES ('23456789012', 'BA123456T1');
INSERT INTO tb_parto(cpf_mae, data_parto, tipo_parto, parto_risco, multifetal, observacao) VALUES ('56789012345', '2023-03-20 10:20:30', 'NORMAL', 'NÃO', 'NÃO', 'Parto sem complicações');

