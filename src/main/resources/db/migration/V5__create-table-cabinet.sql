  CREATE TABLE "cabinet"(
  id UUID PRIMARY KEY,
  condominium_id UUID NOT NULL,
  location TEXT NOT NULL,
  FOREIGN KEY (condominium_id) REFERENCES condominium(id) ON DELETE CASCADE
  )