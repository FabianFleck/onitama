-- Inserir cartas com nomes e cores específicas alternando entre 'RED' e 'BLUE'
INSERT INTO cards (name, color_enum) VALUES
('Elephant', 'BLUE'),
('Tiger', 'RED'),
('Crab', 'BLUE'),
('Dragon', 'RED'),
('Monkey', 'BLUE'),
('Mantis', 'RED'),
('Crane', 'BLUE'),
('Boar', 'RED'),
('Frog', 'BLUE'),
('Goose', 'RED'),
('Horse', 'BLUE'),
('Rabbit', 'RED'),
('Rooster', 'BLUE'),
('Eel', 'RED'),
('Cobra', 'BLUE'),
('Ox', 'RED');

-- Elephant
INSERT INTO positions (line, column, card_id) VALUES
(-1, 1, 1),
(-1, -1, 1),
(0, 1, 1),
(0, -1, 1);

-- Tiger
INSERT INTO positions (line, column, card_id) VALUES
(-2, 0, 2),
(1, 0, 2);

-- Crab
INSERT INTO positions (line, column, card_id) VALUES
(0, -2, 3),
(0, 2, 3),
(-1, 0, 3);

-- Dragon
INSERT INTO positions (line, column, card_id) VALUES
(-1, -2, 4),
(-1, 2, 4),
(1, -1, 4),
(1, 1, 4);

-- Monkey
INSERT INTO positions (line, column, card_id) VALUES
(1, -1, 5),
(1, 1, 5),
(-1, -1, 5),
(-1, 1, 5);

-- Mantis
INSERT INTO positions (line, column, card_id) VALUES
(-1, -1, 6),
(-1, 1, 6),
(1, 0, 6);

-- Crane
INSERT INTO positions (line, column, card_id) VALUES
(-1, 0, 7),
(1, -1, 7),
(1, 1, 7);

-- Boar
INSERT INTO positions (line, column, card_id) VALUES
(-1, 0, 8),
(0, -1, 8),
(0, 1, 8);

-- Frog
INSERT INTO positions (line, column, card_id) VALUES
(-1, -1, 9),
(0, -2, 9),
(1, 1, 9);

-- Goose
INSERT INTO positions (line, column, card_id) VALUES
(-1, -1, 10),
(0, -1, 10),
(1, 1, 10),
(0, 1, 10);

-- Horse
INSERT INTO positions (line, column, card_id) VALUES
(-1, 0, 11),
(0, -1, 11),
(1, 0, 11);

-- Rabbit
INSERT INTO positions (line, column, card_id) VALUES
(-1, 1, 12),
(1, -1, 12),
(0, 2, 12);

-- Rooster
INSERT INTO positions (line, column, card_id) VALUES
(-1, 1, 13),
(1, -1, 13),
(0, 1, 13),
(0, -1, 13);

-- Eel
INSERT INTO positions (line, column, card_id) VALUES
(-1, -1, 14),
(1, -1, 14),
(0, 1, 14);

-- Cobra
INSERT INTO positions (line, column, card_id) VALUES
(-1, 1, 15),
(1, 1, 15),
(0, -1, 15);

-- Ox
INSERT INTO positions (line, column, card_id) VALUES
(-1, 0, 16),
(1, 0, 16),
(0, 1, 16);