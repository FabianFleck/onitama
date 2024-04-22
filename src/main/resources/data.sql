-- Inserir cartas com nomes e cores específicas
INSERT INTO cards (name, color_enum) VALUES
('Elephant', 'RED'),
('Tiger', 'BLUE'),
('Crab', 'RED'),
('Dragon', 'BLUE'),
('Monkey', 'RED'),
('Mantis', 'BLUE'),
('Crane', 'RED'),
('Boar', 'BLUE'),
('Frog', 'RED'),
('Goose', 'BLUE'),
('Horse', 'RED'),
('Rabbit', 'BLUE'),
('Rooster', 'RED'),
('Eel', 'BLUE'),
('Cobra', 'RED'),
('Ox', 'BLUE');

-- Assumindo que os IDs são atribuídos sequencialmente a partir de 1
-- Inserir movimentos para cada carta
INSERT INTO positions (line, column, card_id) VALUES
(-1, 1, 1), (-1, -1, 1), (0, 1, 1), (0, -1, 1),  -- Elephant
(-2, 0, 2), (2, 0, 2), (0, 2, 2), (0, -2, 2),   -- Tiger
(1, 2, 3), (1, -2, 3), (-1, 2, 3), (-1, -2, 3), -- Crab
(1, 1, 4), (1, -1, 4), (-1, 1, 4), (-1, -1, 4), -- Dragon
(1, 0, 5), (-1, 0, 5), (0, 2, 5), (0, -2, 5),   -- Monkey
(2, 1, 6), (2, -1, 6), (-2, 1, 6), (-2, -1, 6), -- Mantis
(0, 1, 7), (0, -1, 7), (1, 0, 7), (-1, 0, 7),   -- Crane
(1, 1, 8), (1, -1, 8), (-1, 1, 8), (-1, -1, 8), -- Boar
(2, 0, 9), (-2, 0, 9), (0, 2, 9), (0, -2, 9),   -- Frog
(1, 2, 10), (1, -2, 10), (-1, 2, 10), (-1, -2, 10), -- Goose
(2, 1, 11), (2, -1, 11), (-2, 1, 11), (-2, -1, 11), -- Horse
(1, 0, 12), (-1, 0, 12), (0, 1, 12), (0, -1, 12), -- Rabbit
(2, 2, 13), (2, -2, 13), (-2, 2, 13), (-2, -2, 13), -- Rooster
(1, 1, 14), (1, -1, 14), (-1, 1, 14), (-1, -1, 14), -- Eel
(2, 0, 15), (-2, 0, 15), (0, 1, 15), (0, -1, 15), -- Cobra
(1, 2, 16), (1, -2, 16), (-1, 2, 16), (-1, -2, 16); -- Ox