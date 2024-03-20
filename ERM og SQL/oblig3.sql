-- SQL for DAT107 Oblig 3

-- Skript for å opprette databasen og legge inn testdata
    -- Skjema = dat107_oblig3
        -- Tabeller = ansatt, avdeling, prosjekt, prosjektdeltakelse

-- MERK!!! DROP SCHEMA ... CASCADE sletter alt !!!
DROP SCHEMA IF EXISTS dat107_oblig3 CASCADE;
CREATE SCHEMA dat107_oblig3;
SET search_path TO dat107_oblig3;
    
    
    
    
-- Ikke nødvendig å slette tabellen(e) siden vi har tomt skjema, men ...
-- DROP TABLE avdeling;

CREATE TABLE avdeling
(
	avdeling_id SERIAL PRIMARY KEY,
	avdelingsnavn VARCHAR(255)
	CONSTRAINT avdelingsnavn UNIQUE
);


    
-- Ikke nødvendig å slette tabellen(e) siden vi har tomt skjema, men ...
-- DROP TABLE ansatt;

CREATE TABLE ansatt
(
    ansatt_id SERIAL PRIMARY KEY,
    brukernavn VARCHAR(60) NOT NULL UNIQUE,
    fornavn VARCHAR(60) NOT NULL,
    etternavn VARCHAR(60) NOT NULL,
    ansettelsesdato DATE NOT NULL,
    stilling VARCHAR(255) NOT NULL,
    maanedsloonn DECIMAL(10,2) NOT NULL,
    avdeling_id INTEGER,
    CONSTRAINT avdelingFK FOREIGN KEY (avdeling_id) REFERENCES avdeling(avdeling_id)
);

INSERT INTO
  avdeling(avdelingsnavn)
VALUES
	('Oslo'),
	('Stavanger'),
	('Bergen');

INSERT INTO
  ansatt(brukernavn, fornavn, etternavn, ansettelsesdato, stilling, maanedsloonn, avdeling_id)
VALUES
    ('ASBO', 'Asgeir', 'Borgermoen', '2022-06-11', 'Sjefen over alle sjefer', 1000000/12, 1),
    ('OLNO', 'Ola', 'Nordmann', '2005-12-10', 'Database Administrator', 550000/12, 3),
    ('KANO', 'Kari', 'Nordmann', '2024-03-14', 'Junior Konsulent', 640000/12, 1),
    ('PEVI', 'Petter', 'Viskeler', '2022-04-11', 'Grafisk designer', 440000/12, 2),
    ('SOSA', 'Solveig', 'Salte', '2015-09-01', 'Junior Konsulent', 670000/12, 2),
    ('KAHA', 'Karl', 'Haugen', '2021-01-19', 'Senior Konsulent', 850000/12, 2),
    ('SIOL', 'Sigurd', 'Olavsen', '2019-02-18', 'Trainee', 400000/12, 3),
    ('PEBR', 'Peder', 'Brattset', '2020-08-23', 'UX-designer', 400000/12, 1),
    ('SINO', 'Silje', 'Noresen', '2014-01-30', 'Frontend utvikler', 650000/12, 1),
    ('NOSI', 'Nora', 'Sigurdsdottir', '2024-02-17', 'Backend utvikler', 740000/12, 2);


ALTER TABLE avdeling
ADD sjef_id INTEGER,
ADD CONSTRAINT sjef_FK FOREIGN KEY (sjef_id) REFERENCES ansatt(ansatt_id);


UPDATE avdeling
SET sjef_id = 1
WHERE avdelingsnavn = 'Oslo';

UPDATE avdeling
SET sjef_id = 6
WHERE avdelingsnavn = 'Stavanger';

UPDATE avdeling
SET sjef_id = 2
WHERE avdelingsnavn = 'Bergen';

CREATE TABLE prosjekt
(
	prosjekt_id SERIAL PRIMARY KEY,
	navn VARCHAR(255) NOT NULL UNIQUE,
	beskrivelse VARCHAR(255)
);

INSERT INTO
   prosjekt(navn, beskrivelse)
VALUES
   ('Ny nettside','Utvikling av ny nettside for bedriften'),
   ('Gammel nettside','Forsøker å gå tilbake til den gamle nettsiden etter en foreskrekket implementering av den nye'),
   ('Kostnadsanalyse','Analysere kostnadene av implementering av ny nettside, samt kostnader for psykologisk hjelp til ansatte i prosjektet "Gammel nettside'),
   ('Avvikling','Prosjekt for å avvikle driften til bedriften');
   
 
CREATE TABLE prosjektdeltagelse
(
	deltagelse_id SERIAL PRIMARY KEY,
	ansatt_id INTEGER NOT NULL,
	prosjekt_id INTEGER NOT NULL,
	timer INTEGER,
	rolle VARCHAR(255) NOT NULL,
	CONSTRAINT deltegalsePK UNIQUE (ansatt_id, prosjekt_id),
	CONSTRAINT ansattFK FOREIGN KEY (ansatt_id) REFERENCES ansatt(ansatt_id),
	CONSTRAINT prosjektFK FOREIGN KEY (prosjekt_id) REFERENCES prosjekt(prosjekt_id)
);

INSERT INTO
   prosjektdeltagelse(ansatt_id, prosjekt_id, timer, rolle)
VALUES
	(1, 1, 20, 'Prosjektleder'),
	(4, 1, 10, 'Prosjektkoordinator'),
	(7, 2, 20, 'Prosjektleder'),
	(3, 3, 20, 'Prosjektleder'),
	(8, 4, 20, 'Prosjektleder'),
	(1, 3, 50, 'Analytiker');


SELECT * FROM prosjektdeltagelse




