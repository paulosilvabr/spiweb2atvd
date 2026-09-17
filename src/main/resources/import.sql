-- Inserindo Pacientes
INSERT INTO paciente (nome, telefone) VALUES ('Ana Beatriz da Silva', '(11) 98888-1111');
INSERT INTO paciente (nome, telefone) VALUES ('Carlos Souza', '(11) 97777-2222');

-- Inserindo Médicos
INSERT INTO medico (nome, crm) VALUES ('Dr. João Almeida', '12345-SP');
INSERT INTO medico (nome, crm) VALUES ('Dra. Fernanda Mendes', '54321-RJ');

-- Inserindo Consultas
INSERT INTO consulta (data, valor, observacao, paciente_id, medico_id) VALUES ('2023-11-15 10:30:00', 350.00, 'Primeira avaliação', 1, 1);
INSERT INTO consulta (data, valor, observacao, paciente_id, medico_id) VALUES ('2023-11-16 14:00:00', 200.00, 'Retorno de exames', 2, 2);
