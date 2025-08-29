CREATE DATABASE IF NOT EXISTS db3;
USE db3;

CREATE TABLE IF NOT EXISTS ldap_users (
                                          uid INT AUTO_INCREMENT PRIMARY KEY,
                                          login VARCHAR(50) NOT NULL,
                                          first_name VARCHAR(100),
                                          last_name VARCHAR(100)
);

CREATE USER IF NOT EXISTS 'testuser'@'%' IDENTIFIED BY 'testpass';
GRANT ALL PRIVILEGES ON db3.* TO 'testuser'@'%';
FLUSH PRIVILEGES;

INSERT INTO ldap_users (login, first_name, last_name) VALUES
('jdoe', 'John', 'Doe'),
('asmith', 'Alice', 'Smith'),
('bwayne', 'Bruce', 'Wayne');