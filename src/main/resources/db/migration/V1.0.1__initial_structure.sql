CREATE TABLE IF NOT EXISTS users (
  user_id  BIGINT PRIMARY KEY,
  username VARCHAR(128) UNIQUE,
  password VARCHAR(256),
  role     CHARACTER VARYING(50),
  enabled  BOOL
);

CREATE TABLE IF NOT EXISTS people (
  person_id BIGINT PRIMARY KEY,
  name      VARCHAR(32),
  username  VARCHAR(128) UNIQUE REFERENCES users (username),
  age       INT
);

CREATE TABLE IF NOT EXISTS skill (
  skill_id BIGINT PRIMARY KEY,
  name     VARCHAR(16)
);

CREATE TABLE IF NOT EXISTS party (
  party_id   BIGINT PRIMARY KEY,
  location   VARCHAR(64),
  party_date TIMESTAMP
);

CREATE TABLE IF NOT EXISTS people_parties (
  person_id BIGINT NOT NULL REFERENCES people (person_id),
  party_id  BIGINT NOT NULL REFERENCES party (party_id),
  PRIMARY KEY (person_id, party_id)
);

CREATE TABLE IF NOT EXISTS level (
  level_id BIGINT PRIMARY KEY,
  name     VARCHAR(16)
);

CREATE TABLE IF NOT EXISTS skill_level (
  skill_id BIGINT NOT NULL REFERENCES skill (skill_id),
  level_id BIGINT NOT NULL REFERENCES level (level_id),
  PRIMARY KEY (skill_id, level_id)

);

CREATE TABLE IF NOT EXISTS people_skill_level (
  psl_id    BIGINT PRIMARY KEY,
  person_id BIGINT NOT NULL REFERENCES people (person_id),
  skill_id  BIGINT NOT NULL REFERENCES skill (skill_id),
  level_id  BIGINT NOT NULL REFERENCES level (level_id),
  UNIQUE (person_id, skill_id, level_id)
);
