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

-- Inserir movimentos considerando que avançar é mover-se para cima
INSERT INTO positions (line, column, card_id) VALUES
-- Elephant: Move uma casa para cima e para os lados; move para os lados na mesma linha
(-1, 1, 1),  -- Avança uma casa para cima e uma para a direita
(-1, -1, 1), -- Avança uma casa para cima e uma para a esquerda
(0, 1, 1),   -- Move uma casa para a direita na mesma linha
(0, -1, 1),  -- Move uma casa para a esquerda na mesma linha

-- Tiger: Avança duas casas para cima; recua uma casa para baixo
(-2, 0, 2),  -- Avança duas casas para cima
(1, 0, 2),   -- Recua uma casa para baixo

-- Crab: Move lateralmente na mesma linha; avança uma casa para cima
(0, -2, 3),  -- Move duas casas para a esquerda na mesma linha
(0, 2, 3),   -- Move duas casas para a direita na mesma linha
(1, 0, 3),   -- Avança uma casa para cima

-- Dragon: Avança uma casa para cima e move-se duas casas lateralmente; recua uma casa para baixo e move-se uma lateralmente
(-1, -2, 4), -- Avança uma casa para cima e move-se duas para a esquerda
(-1, 2, 4),  -- Avança uma casa para cima e move-se duas para a direita
(1, -1, 4),  -- Recua uma casa para baixo e move-se uma para a esquerda
(1, 1, 4),   -- Recua uma casa para baixo e move-se uma para a direita

-- Monkey: Avança ou recua diagonalmente
(-1, -1, 5), -- Avança uma casa para cima e uma para a esquerda
(-1, 1, 5),  -- Avança uma casa para cima e uma para a direita
(1, -1, 5),  -- Recua uma casa para baixo e uma para a esquerda
(1, 1, 5),   -- Recua uma casa para baixo e uma para a direita

-- Mantis: Avança diagonalmente para os lados; recua diretamente para baixo
(-1, -1, 6), -- Avança uma casa para cima e uma para a esquerda
(-1, 1, 6),  -- Avança uma casa para cima e uma para a direita
(1, 0, 6),   -- Recua uma casa para baixo

-- Crane: Avança diretamente para cima; recua diagonalmente para os lados
(-1, 0, 7),  -- Avança uma casa para cima
(1, -1, 7),  -- Recua uma casa para baixo e uma para a esquerda
(1, 1, 7),   -- Recua uma casa para baixo e uma para a direita

-- Boar: Avança diretamente para cima; move para os lados na mesma linha
(-1, 0, 8),  -- Avança uma casa para cima
(0, -1, 8),  -- Move uma casa para a esquerda na mesma linha
(0, 1, 8),   -- Move uma casa para a direita na mesma linha

-- Frog: Recua diretamente para baixo; avança diagonalmente para um lado e recua para o outro
(-1, -1, 9), -- Recua uma casa para baixo e uma para a esquerda
(1, 1, 9),   -- Avança uma casa para cima e uma para a direita
(-1, 1, 9),  -- Recua uma casa para baixo e uma para a direita

-- Goose: Avança ou recua diagonalmente para um lado; move para o outro lado na mesma linha
(1, -1, 10), -- Avança uma casa para cima e uma para a esquerda
(0, -1, 10), -- Move uma casa para a esquerda na mesma linha
(-1, 1, 10), -- Recua uma casa para baixo e uma para a direita
(0, 1, 10),  -- Move uma casa para a direita na mesma linha

-- Horse: Avança diretamente para cima; move lateralmente para a esquerda
(1, 0, 11),  -- Avança uma casa para cima
(0, -1, 11), -- Move uma casa para a esquerda na mesma linha

-- Rabbit: Avança diagonalmente para um lado; recua diagonalmente para o outro lado; move lateralmente para a direita
(1, 1, 12),  -- Avança uma casa para cima e uma para a direita
(-1, -1, 12),-- Recua uma casa para baixo e uma para a esquerda
(0, 2, 12),  -- Move duas casas para a direita na mesma linha

-- Rooster: Avança ou recua para um lado; move para o outro lado na mesma linha
(1, -1, 13), -- Avança uma casa para cima e uma para a esquerda
(-1, -1, 13),-- Recua uma casa para baixo e uma para a esquerda
(1, 1, 13),  -- Avança uma casa para cima e uma para a direita
(-1, 1, 13), -- Recua uma casa para baixo e uma para a direita

-- Eel: Avança ou recua diagonalmente para um lado; move lateralmente para a direita
(1, -1, 14), -- Avança uma casa para cima e uma para a esquerda
(-1, -1, 14),-- Recua uma casa para baixo e uma para a esquerda
(0, 1, 14),  -- Move uma casa para a direita na mesma linha

-- Cobra: Avança ou recua diagonalmente para um lado; move lateralmente para a esquerda
(1, 1, 15),  -- Avança uma casa para cima e uma para a direita
(-1, 1, 15), -- Recua uma casa para baixo e uma para a direita
(0, -1, 15), -- Move uma casa para a esquerda na mesma linha

-- Ox: Avança diretamente para cima; move lateralmente para a direita
(1, 0, 16),  -- Avança uma casa para cima
(0, 1, 16),  -- Move uma casa para a direita na mesma linha
(0, 1, 16);  -- Move uma casa para a direita na mesma linha