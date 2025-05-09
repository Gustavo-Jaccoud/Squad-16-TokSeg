CREATE TABLE "delivery_package" (
    id UUID PRIMARY KEY,
    delivery_person_id UUID,
    compartment_id UUID NOT NULL,
    apartment_id UUID NOT NULL,
    delivery_datetime TIMESTAMP NOT NULL,
    max_pickup_datetime TIMESTAMP NOT NULL,
    status VARCHAR(20) NOT NULL,
    FOREIGN KEY (delivery_person_id) REFERENCES delivery_person(id) ON DELETE SET NULL,
    FOREIGN KEY (compartment_id) REFERENCES compartment(id),
    FOREIGN KEY (apartment_id) REFERENCES apartment(id)
);
