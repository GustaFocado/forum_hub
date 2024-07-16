CREATE TABLE topico (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    mensagem TEXT NOT NULL,
    data_criacao TIMESTAMP,
    estado VARCHAR(50),
    autor VARCHAR(255),
    curso VARCHAR(255)
);
