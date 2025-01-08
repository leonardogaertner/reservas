CREATE TABLE resource (
    id SERIAL PRIMARY KEY,            -- Identificador único do recurso
    name VARCHAR(255) NOT NULL,       -- Nome do recurso
    type VARCHAR(100) NOT NULL        -- Tipo do recurso (ex.: Sala, Equipamento)
);

CREATE TABLE reservation (
    id SERIAL PRIMARY KEY,            -- Identificador único da reserva
    resource_id INT NOT NULL,         -- Relacionamento com a tabela resource
    start_time TIMESTAMP NOT NULL,    -- Horário de início da reserva
    end_time TIMESTAMP NOT NULL,      -- Horário de término da reserva
    FOREIGN KEY (resource_id) REFERENCES resource (id) ON DELETE CASCADE
);

-- Índice para melhorar as consultas de conflitos de horário
CREATE INDEX idx_reservation_resource_time 
ON reservation (resource_id, start_time, end_time);