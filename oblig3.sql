-- SQL for DAT107 Oblig 3

-- Skript for å opprette databasen og legge inn litt data
    -- Skjema = dat107_oblig3
        -- Tabell(er) = ansatt

-- MERK!!! DROP SCHEMA ... CASCADE sletter alt !!!
DROP SCHEMA IF EXISTS dat107_oblig3 CASCADE;
CREATE SCHEMA dat107_oblig3;
SET search_path TO dat107_oblig3;
    
-- Ikke nødvendig å slette tabellen(e) siden vi har tomt skjema, men ...
-- DROP TABLE ansatt

CREATE TABLE ansatt
(
    ansatt_id SERIAL,
    brukernavn VARCHAR(60),
    fornavn VARCHAR(60),
    etternavn VARCHAR(60),
    ansettelsesdato DATE,
    stilling VARCHAR(255),
    maanedsloonn DECIMAL(10,2),
    CONSTRAINT ansatt_pk PRIMARY KEY (ansatt_id)
);

INSERT INTO
  ansatt(brukernavn, fornavn, etternavn, ansettelsesdato, stilling, maanedsloonn)
VALUES
    ('sjefen', 'Asgeir', 'Borgermoen', '2022-06-11', 'Sjefen over alle sjefer', 1000000/12 ),
    ('DBA', 'Ola', 'Nordmann', '2005-12-10', 'Database Administrator', 550000/12),
    ('tempBruker', 'Kari', 'Nordmann', '2024-03-14', 'Junior Konsulent', 640000/12);

SELECT * FROM ansatt;

