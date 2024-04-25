CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    username VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS cards (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    color_enum VARCHAR(10)
);

CREATE TABLE IF NOT EXISTS positions (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    line INT,
    column INT,
    card_id BIGINT,
    FOREIGN KEY (card_id) REFERENCES cards(id)
);

-- Primeiro, crie a tabela battle para evitar o erro de referência não encontrada
CREATE TABLE IF NOT EXISTS battle (
    id VARCHAR(255) PRIMARY KEY,
    player1_id BIGINT,
    player2_id BIGINT,
    table_card_id BIGINT,
    current_player ENUM('RED', 'BLUE')
    -- Remova as chaves estrangeiras aqui para evitar erros de referência circular
);

CREATE TABLE IF NOT EXISTS player (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT,
    color ENUM('RED', 'BLUE') NOT NULL,
    card1_id BIGINT,
    card2_id BIGINT,
    battle_id VARCHAR(255),
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (card1_id) REFERENCES cards(id),
    FOREIGN KEY (card2_id) REFERENCES cards(id),
    FOREIGN KEY (battle_id) REFERENCES battle(id)
);

CREATE TABLE IF NOT EXISTS part (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    player_id BIGINT,
    line INT,
    column INT,
    part_type_enum ENUM('MASTER', 'DISCIPLE'),
    FOREIGN KEY (player_id) REFERENCES player(id)
);

-- Agora, adicione as chaves estrangeiras à tabela battle
ALTER TABLE battle ADD FOREIGN KEY (player1_id) REFERENCES player(id);
ALTER TABLE battle ADD FOREIGN KEY (player2_id) REFERENCES player(id);
ALTER TABLE battle ADD FOREIGN KEY (table_card_id) REFERENCES cards(id);

