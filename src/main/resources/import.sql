-- ==========================================================
-- HIERARQUIA: Paciente 1 (Ana Beatriz)
-- 1 - tb_pessoa (raiz da hierarquia)
INSERT INTO tb_pessoa (email, telefone) VALUES ('ana.beatriz@email.com', '(11) 98888-1111');
-- 2 - tb_pessoa_fisica (filho de tb_pessoa, mesmo id)
INSERT INTO tb_pessoa_fisica (id_pessoa, nome, cpf) VALUES (1, 'Ana Beatriz da Silva', '111.222.333-44');
-- 3 - tb_paciente (filho de tb_pessoa_fisica, mesmo id)
INSERT INTO tb_paciente (id_pessoa_fisica) VALUES (1);

-- ==========================================================
-- HIERARQUIA: Paciente 2 (Carlos Souza)
INSERT INTO tb_pessoa (email, telefone) VALUES ('carlos.souza@email.com', '(11) 97777-2222');
INSERT INTO tb_pessoa_fisica (id_pessoa, nome, cpf) VALUES (2, 'Carlos Souza', '555.666.777-88');
INSERT INTO tb_paciente (id_pessoa_fisica) VALUES (2);

-- ==========================================================
-- HIERARQUIA: Medico 1 (Dr. Joao Almeida)
INSERT INTO tb_pessoa (email, telefone) VALUES ('joao.almeida@clinica.com', '(21) 91111-3333');
INSERT INTO tb_pessoa_fisica (id_pessoa, nome, cpf) VALUES (3, 'Joao Almeida', '999.888.777-66');
INSERT INTO tb_medico (id_pessoa_fisica, crm) VALUES (3, '12345-SP');

-- ==========================================================
-- HIERARQUIA: Medico 2 (Dra. Fernanda Mendes)
INSERT INTO tb_pessoa (email, telefone) VALUES ('fernanda.mendes@clinica.com', '(21) 92222-4444');
INSERT INTO tb_pessoa_fisica (id_pessoa, nome, cpf) VALUES (4, 'Fernanda Mendes', '111.000.999-55');
INSERT INTO tb_medico (id_pessoa_fisica, crm) VALUES (4, '54321-RJ');

-- ==========================================================
-- CONSULTAS
INSERT INTO tb_consulta (data, valor, observacao, paciente_id, medico_id) VALUES ('2024-11-15T10:30:00', 350.00, 'Primeira avaliacao', 1, 3);
INSERT INTO tb_consulta (data, valor, observacao, paciente_id, medico_id) VALUES ('2024-11-16T14:00:00', 200.00, 'Retorno de exames', 2, 4);