CREATE DATABASE IF NOT EXISTS cis530_week4;

CREATE USER IF NOT EXISTS 'cis530_user'@'localhost' IDENTIFIED BY 'CHANGE_ME';
GRANT ALL PRIVILEGES ON cis530_week4.* TO 'cis530_user'@'localhost';
FLUSH PRIVILEGES;

USE cis530_week4;
-- After the app runs once, verify with:
-- SHOW TABLES;
-- DESCRIBE student;
-- SELECT * FROM student;
