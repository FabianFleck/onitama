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

CREATE TABLE IF NOT EXISTS player (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    color ENUM('RED', 'BLUE') NOT NULL
);

CREATE TABLE IF NOT EXISTS battle (
    id VARCHAR(255) PRIMARY KEY,
    player1_id BIGINT,
    player2_id BIGINT,
    FOREIGN KEY (player1_id) REFERENCES player(id),
    FOREIGN KEY (player2_id) REFERENCES player(id)
);

CREATE TABLE IF NOT EXISTS part (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    player_id BIGINT,
    line INT,
    column INT,
    part_type_enum ENUM('MASTER', 'DISCIPLE'),
    FOREIGN KEY (player_id) REFERENCES player(id)
);