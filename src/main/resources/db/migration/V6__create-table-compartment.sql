CREATE TABLE "compartment"(
      id UUID PRIMARY KEY,
      name VARCHAR(10) NOT NULL,
      cabinet_id UUID NOT NULL,
      size VARCHAR(10) NOT NULL,
      is_occupied BOOLEAN NOT NULL,
      FOREIGN KEY (cabinet_id) REFERENCES cabinet(id) ON DELETE CASCADE
)