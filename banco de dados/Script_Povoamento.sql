USE LOJA_CELULAR;

INSERT INTO PRODUTO (NOME, MARCA, PRECO) VALUES
('iPhone 13','Apple', 1200),
('Galaxy S21','Samsung ',1000),
('Pixel 6','Google', 900),
('Xperia 1','Sony', 1100),
('Mi 11','Xiaomi', 800);

INSERT INTO FORNECEDOR (NOME, TELEFONE, RUA, NUMERO, BAIRRO, CIDADE, UF) VALUES
('TechGizmo','(11) 1234-5678', 'Rua A', '100', 'Centro', 'São Paulo', 'SP'),
('NovaWave','(21) 9876-5432', 'Avenida B', '200', 'Copacabana', 'Rio de Janeiro', 'RJ'),
('SmartTech','(31) 2468-1357', 'Rua C', '300', 'Savassi', 'Belo Horizonte', 'MG');

INSERT INTO DEPOSITO (NOME, TELEFONE, RUA, NUMERO, BAIRRO, CIDADE, UF) VALUES
('CellVault Depot','(81) 1111-2222', 'Rua 1', '100', 'Boa Viagem', 'Recife', 'PE'),
('Mobile Warehouse','(84) 3333-4444', 'Avenida 2', '200', 'Ponta Negra', 'Natal', 'RN'),
('TechHub Storage','(73) 5555-6666', 'Travessa 3', '300', 'Centro', 'Salvador', 'BA');

INSERT INTO FORNECEDORPRODUTO (FORNECEDORID, PRODUTOID) VALUES
(1, 1),
(1, 2),
(1, 3),
(2, 1),
(2, 2),
(2, 4),
(3, 2),
(3, 3),
(3, 5);

INSERT INTO DEPOSITOPRODUTO (DEPOSITOID, PRODUTOID, PONTOREPOSICAO, QUANTIDADEATUAL) VALUES
(1, 1, 10, 20),
(1, 2, 10, 20),
(1, 3, 7, 14),
(1, 4, 7, 14),
(2, 1, 15, 30),
(2, 2, 15, 30),
(2, 3, 10, 20),
(2, 5, 5, 10),
(3, 1, 7, 14),
(3, 2, 7, 14),
(3, 4, 3, 6),
(3, 5, 3, 6);

INSERT INTO MOVIMENTACAO (DEPOSITOID, PRODUTOID, QUANTIDADE, TIPO) VALUES
(1, 1, 20, 1),
(1, 2, 20, 1),
(1, 3, 14, 1),
(1, 4, 14, 1),
(2, 1, 20, 1),
(2, 2, 20, 1),
(2, 3, 14, 1),
(2, 5, 14, 1),
(3, 1, 14, 1),
(3, 2, 14, 1),
(3, 4, 6, 1),
(3, 5, 6, 1),
(1, 1, 2, 0),
(1, 3, 1, 0),
(2, 2, 5, 0),
(2, 5, 2, 0),
(3, 1, 8, 0),
(3, 2, 10, 0),
(1, 1, 5, 0),
(3, 1, 5, 1);