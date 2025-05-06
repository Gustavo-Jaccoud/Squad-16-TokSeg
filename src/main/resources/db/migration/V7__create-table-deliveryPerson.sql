CREATE TABLE "delivery_person"(
        id UUID PRIMARY KEY,
        user_id UUID NOT NULL,
        cpf VARCHAR(14) NOT NULL,
        FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
)