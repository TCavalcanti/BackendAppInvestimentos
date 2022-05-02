
INSERT INTO stocks (created_at, name, updated_at)  VALUES 	('2022-04-19 08:46:46.159923',	'ITSA4',	'2022-04-19 08:46:46.159923');
INSERT INTO stocks (created_at, name, updated_at)  VALUES 	('2022-04-19 08:46:46.159923',	'VALE3',	'2022-04-19 08:46:46.159923');
INSERT INTO stocks (created_at, name, updated_at)  VALUES 	('2022-04-19 08:46:46.159923',	'USIM5',	'2022-04-19 08:46:46.159923');
INSERT INTO stocks (created_at, name, updated_at)  VALUES 	('2022-04-19 08:46:46.159923',	'CIEL3',	'2022-04-19 08:46:46.159923');
INSERT INTO stocks (created_at, name, updated_at)  VALUES 	('2022-04-19 08:46:46.159923',	'BBAS3',	'2022-04-19 08:46:46.159923');
INSERT INTO stocks (created_at, name, updated_at)  VALUES 	('2022-04-19 08:46:46.159923',	'BBDC4',	'2022-04-19 08:46:46.159923');
INSERT INTO stocks (created_at, name, updated_at)  VALUES 	('2022-04-19 08:46:46.159923',	'BBAS3',	'2022-04-19 08:46:46.159923');
INSERT INTO stocks (created_at, name, updated_at)  VALUES 	('2022-04-19 08:46:46.159923',	'PETZ3',	'2022-04-19 08:46:46.159923');
INSERT INTO stocks (created_at, name, updated_at)  VALUES 	('2022-04-19 08:46:46.159923',	'RAIL3',	'2022-04-19 08:46:46.159923');
INSERT INTO stocks (created_at, name, updated_at)  VALUES 	('2022-04-19 08:46:46.159923',	'ITUB4',	'2022-04-19 08:46:46.159923');



INSERT INTO clients (created_at, email, name, updated_at) VALUES 	('2022-04-19 08:46:46.159923',	'teste1@teste.com',	'Srta. Stella Cardoso',	'2022-04-19 08:46:46.159923');
INSERT INTO clients (created_at, email, name, updated_at) VALUES 	('2022-04-19 08:46:46.159923',	'teste2@teste.com',	'Fernanda Duarte',	'2022-04-19 08:46:46.159923');
INSERT INTO clients (created_at, email, name, updated_at) VALUES 	('2022-04-19 08:46:46.159923',	'teste3@teste.com',	'Davi Lucas Fogaça',	'2022-04-19 08:46:46.159923');
INSERT INTO clients (created_at, email, name, updated_at) VALUES 	('2022-04-19 08:46:46.159923',	'teste4@teste.com',	'Dr. Lucas Gabriel da Mota',	'2022-04-19 08:46:46.159923');
INSERT INTO clients (created_at, email, name, updated_at) VALUES 	('2022-04-19 08:46:46.159923',	'teste5@teste.com',	'Natália Rezende',	'2022-04-19 08:46:46.159923');
INSERT INTO clients (created_at, email, name, updated_at) VALUES 	('2022-04-19 08:46:46.159923',	'teste6@teste.com',	'Alana Caldeira',	'2022-04-19 08:46:46.159923');
INSERT INTO clients (created_at, email, name, updated_at) VALUES 	('2022-04-19 08:46:46.159923',	'teste7@teste.com',	'Sra. Evelyn Gonçalves',	'2022-04-19 08:46:46.159923');
INSERT INTO clients (created_at, email, name, updated_at) VALUES 	('2022-04-19 08:46:46.159923',	'teste8@teste.com',	'Dr. Thales Pereira',	'2022-04-19 08:46:46.159923');
INSERT INTO clients (created_at, email, name, updated_at) VALUES 	('2022-04-19 08:46:46.159923',	'teste9@teste.com',	'Pedro Henrique Rocha',	'2022-04-19 08:46:46.159923');
INSERT INTO clients (created_at, email, name, updated_at) VALUES 	('2022-04-19 08:46:46.159923',	'teste10@teste.com','Rafaela Nunes',	'2022-04-19 08:46:46.159923');


INSERT INTO users (created_at, email, name, password, updated_at, username) VALUES ('2022-04-19 10:39:17.509393',	'user1@teste.com',	'User 1',	'$2a$10$2b4LqxTQJl36sabOljnox.dmSY6hERgYYX.Vkyvg9xmtysgrhy7ni',	'2022-04-19 10:39:17.509393',	'administrator');
INSERT INTO users (created_at, email, name, password, updated_at, username) VALUES ('2022-04-19 10:39:26.532987',	'user2@teste.com',	'User 2',	'$2a$10$L.IN6IzYf7.gr0zBwM7otu29iox7J862up035zo8CsFIr4KT2k65y',	'2022-04-19 10:39:26.532987',	'administrator2');




INSERT INTO investments (amount, created_at, indicador_carencia, name, updated_at, client_id) VALUES	(500000,	'2022-04-19 08:46:46.159923',	true,	'Iate dos Sonhos',	'2022-04-19 08:46:46.159923', 1);
INSERT INTO investments (amount, created_at, indicador_carencia, name, updated_at, client_id) VALUES	(100000,	'2022-04-19 08:46:46.159923',	false,	'Reforma casa',		'2022-04-19 08:46:46.159923', 2);
INSERT INTO investments (amount, created_at, indicador_carencia, name, updated_at, client_id) VALUES	(50000,		'2022-04-19 08:46:46.159923',	false,	'Viagem férias',	'2022-04-19 08:46:46.159923', 3);
INSERT INTO investments (amount, created_at, indicador_carencia, name, updated_at, client_id) VALUES	(50000,		'2022-04-19 08:46:46.159923',	false,	'Viagem férias',	'2022-04-19 08:46:46.159923', 4);


INSERT INTO investment_stock (created_at, percent, updated_at, investment_id, stock_id) VALUES	('2022-04-19 08:46:46.159923',	35,	'2022-04-19 08:46:46.159923',	1,	2);
INSERT INTO investment_stock (created_at, percent, updated_at, investment_id, stock_id) VALUES	('2022-04-19 08:46:46.159923',	35,	'2022-04-19 08:46:46.159923',	1,	7);
INSERT INTO investment_stock (created_at, percent, updated_at, investment_id, stock_id) VALUES	('2022-04-19 08:46:46.159923',	30,	'2022-04-19 08:46:46.159923',	1,	3);
INSERT INTO investment_stock (created_at, percent, updated_at, investment_id, stock_id) VALUES	('2022-04-19 08:46:46.159923',	20,	'2022-04-19 08:46:46.159923',	2,	4);
INSERT INTO investment_stock (created_at, percent, updated_at, investment_id, stock_id) VALUES	('2022-04-19 08:46:46.159923',	20,	'2022-04-19 08:46:46.159923',	3,	5);
INSERT INTO investment_stock (created_at, percent, updated_at, investment_id, stock_id) VALUES	('2022-04-19 08:46:46.159923',	30,	'2022-04-19 08:46:46.159923',	3,	6);
INSERT INTO investment_stock (created_at, percent, updated_at, investment_id, stock_id) VALUES	('2022-04-19 08:46:46.159923',	30,	'2022-04-19 08:46:46.159923',	4,	7);