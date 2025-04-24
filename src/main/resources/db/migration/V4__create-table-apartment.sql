CREATE TABLE  "apartment"(
    id UUID PRIMARY KEY,
    block_id UUID NOT NULL,
    user_id UUID NOT NULL,
    apartment_number VARCHAR(10) NOT NULL,
    FOREIGN KEY (block_id) REFERENCES block(id),
    FOREIGN KEY (user_id) REFERENCES users(id)
)