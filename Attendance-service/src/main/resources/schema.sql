CREATE TABLE IF NOT EXISTS attendance ( id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                                                         student_id BIGINT NOT NULL, date DATE NOT NULL,
                                                                                                               present BOOLEAN NOT NULL,
                                       FOREIGN KEY (student_id) REFERENCES students(id));