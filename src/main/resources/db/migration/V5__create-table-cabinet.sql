  CREATE TABLE "cabinet"(
  id UUID PRIMARY KEY,
  name VARCHAR(10) NOT NULL,
  condominium_id UUID NOT NULL,
  location TEXT NOT NULL,
  status BOOL NOT NULL,
  FOREIGN KEY (condominium_id) REFERENCES condominium(id) ON DELETE CASCADE
  )