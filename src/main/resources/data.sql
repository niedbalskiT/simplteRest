DROP TABLE IF EXISTS test_user;

CREATE TABLE test_user (
                              id INT AUTO_INCREMENT  PRIMARY KEY,
                              first_name VARCHAR(250) NOT NULL,
                              last_name VARCHAR(250) NOT NULL
);

INSERT INTO test_user (first_name, last_name) VALUES
('Tomasz', 'Niedbalski');