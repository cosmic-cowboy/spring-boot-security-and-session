CREATE TABLE users (username VARCHAR(100) NOT NULL PRIMARY KEY, encoded_password VARCHAR(255));
INSERT INTO users (username, encoded_password) VALUES ('user1', '$2a$10$tFfSjQksxXWSr9aQKInAieagRA0KpCxcLs5QyuY8VSiJ.6e93C3ey');
INSERT INTO users (username, encoded_password) VALUES ('user2', '$2a$10$tFfSjQksxXWSr9aQKInAieagRA0KpCxcLs5QyuY8VSiJ.6e93C3ey');