CREATE TABLE IF NOT EXISTS users (
                                     user_id SERIAL PRIMARY KEY,
                                     login VARCHAR(50) NOT NULL,
                                     first_name VARCHAR(100),
                                     last_name VARCHAR(100)
);

INSERT INTO users (login, first_name, last_name) VALUES
('jdoe', 'John', 'Doe'),
('asmith', 'Alice', 'Smith'),
('bwayne', 'Bruce', 'Wayne');
