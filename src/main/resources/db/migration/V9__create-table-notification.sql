CREATE TABLE "notification" (
    id UUID PRIMARY KEY,
    delivery_package_id UUID NOT NULL,
    sent_datetime TIMESTAMP NOT NULL,
    status VARCHAR(20) NOT NULL,
    FOREIGN KEY (delivery_package_id) REFERENCES delivery_package(id) ON DELETE CASCADE
);
