CREATE TABLE  "block"(
    id UUID PRIMARY KEY,
    condominium_id UUID NOT NULL,
    name VARCHAR(255) NOT NULL,
    FOREIGN KEY (condominium_id) REFERENCES condominium(id) ON DELETE CASCADE
)