CREATE TABLE IF NOT EXISTS user_table (
                                          ldap_login VARCHAR(50) PRIMARY KEY,
                                          name VARCHAR(100),
                                          surname VARCHAR(100)
);

INSERT INTO user_table (ldap_login, name, surname) VALUES
('jdoe', 'John', 'Doe'),
('asmith', 'Alice', 'Smith'),
('bwayne', 'Bruce', 'Wayne');
