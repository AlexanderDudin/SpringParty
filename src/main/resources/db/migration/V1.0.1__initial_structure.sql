CREATE TABLE IF NOT EXISTS users (
  user_id  SERIAL PRIMARY KEY,
  username VARCHAR(128) UNIQUE,
  password VARCHAR(256),
  role     CHARACTER VARYING(50),
  enabled  BOOL
);

CREATE TABLE IF NOT EXISTS people (
  person_id SERIAL PRIMARY KEY,
  name      VARCHAR(32),
  username  VARCHAR(128) UNIQUE REFERENCES users (username),
  age       INT
);

CREATE TABLE IF NOT EXISTS skill (
  skill_id SERIAL PRIMARY KEY,
  name     VARCHAR(16)
);

CREATE TABLE IF NOT EXISTS party (
  party_id   SERIAL PRIMARY KEY,
  location   VARCHAR(64),
  party_date TIMESTAMP
);

CREATE TABLE IF NOT EXISTS level (
  level_id SERIAL PRIMARY KEY,
  name     VARCHAR(16)
);

CREATE TABLE IF NOT EXISTS people_parties (
  id        SERIAL PRIMARY KEY,
  person_id BIGINT NOT NULL REFERENCES people (person_id),
  party_id  BIGINT NOT NULL REFERENCES party (party_id),

  UNIQUE (person_id, party_id),
  CONSTRAINT people_parties__people_FK FOREIGN KEY (person_id) REFERENCES people (person_id),
  CONSTRAINT people_parties__party_FK FOREIGN KEY (party_id) REFERENCES party (party_id)
);

CREATE TABLE IF NOT EXISTS skill_level (
  id       SERIAL PRIMARY KEY,
  skill_id BIGINT NOT NULL REFERENCES skill (skill_id),
  level_id BIGINT NOT NULL REFERENCES level (level_id),

  UNIQUE (skill_id, level_id),
  CONSTRAINT skill_level__skill_FK FOREIGN KEY (skill_id) REFERENCES skill (skill_id),
  CONSTRAINT skill_level__level_FK FOREIGN KEY (level_id) REFERENCES level (level_id)

);

CREATE TABLE IF NOT EXISTS people_skill_level (
  psl_id    SERIAL PRIMARY KEY,
  person_id BIGINT NOT NULL REFERENCES people (person_id),
  skill_id  BIGINT NOT NULL REFERENCES skill (skill_id),
  level_id  BIGINT NOT NULL REFERENCES level (level_id),

  UNIQUE (person_id, skill_id, level_id),
  CONSTRAINT people_skill_level__people_FK FOREIGN KEY (person_id) REFERENCES people (person_id),
  CONSTRAINT people_skill_level__skill_FK FOREIGN KEY (skill_id) REFERENCES skill (skill_id),
  CONSTRAINT people_skill_level__level_FK FOREIGN KEY (level_id) REFERENCES level (level_id)

);
