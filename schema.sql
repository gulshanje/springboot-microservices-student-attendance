CREATE DATABASE studentdb;

USE studentdb;


CREATE TABLE students ( id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                                         name VARCHAR(255) NOT NULL);


CREATE DATABASE attendancedb;

USE attendancedb;


CREATE TABLE attendance ( id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                                           student_id BIGINT NOT NULL, date DATE NOT NULL,
                                                                                                 present BOOLEAN NOT NULL,
                         FOREIGN KEY (student_id) REFERENCES students(id));

