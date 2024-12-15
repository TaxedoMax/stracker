-- Создание таблицы users
CREATE TABLE users (
                       id BIGSERIAL PRIMARY KEY,
                       login VARCHAR(255) NOT NULL UNIQUE,
                       photo TEXT
);

-- Создание таблицы teams
CREATE TABLE teams (
                       id BIGSERIAL PRIMARY KEY,
                       name VARCHAR(255) NOT NULL,
                       status VARCHAR(50) NOT NULL,
                       lead_id BIGINT REFERENCES users(id) ON DELETE SET NULL,
                       description TEXT,
                       photo TEXT
);

-- Создание таблицы tasks
CREATE TABLE tasks (
                       id BIGSERIAL PRIMARY KEY,
                       name VARCHAR(255) NOT NULL,
                       status VARCHAR(50),
                       type VARCHAR(50),
                       description TEXT,
                       author_id BIGINT REFERENCES users(id) ON DELETE SET NULL,
                       executor_id BIGINT REFERENCES users(id) ON DELETE SET NULL,
                       team_id BIGINT REFERENCES teams(id) ON DELETE SET NULL
);

-- Создание промежуточной таблицы user_team
CREATE TABLE user_team (
                           user_id BIGINT REFERENCES users(id) ON DELETE CASCADE,
                           team_id BIGINT REFERENCES teams(id) ON DELETE CASCADE,
                           PRIMARY KEY (user_id, team_id)
);