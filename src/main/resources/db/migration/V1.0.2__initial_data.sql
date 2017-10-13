INSERT INTO users (username, password, role, enabled) VALUES
  ('peter@example.com', '$2a$10$D4OLKI6yy68crm.3imC9X.P2xqKHs5TloWUcr6z5XdOqnTrAK84ri', 'ADMIN', TRUE),
  ('john@example.com', '$2a$10$D4OLKI6yy68crm.3imC9X.P2xqKHs5TloWUcr6z5XdOqnTrAK84ri', 'VALIDATED_USER', TRUE),
  ('katie@example.com', '$2a$10$D4OLKI6yy68crm.3imC9X.P2xqKHs5TloWUcr6z5XdOqnTrAK84ri', 'VALIDATED_USER', TRUE);

INSERT INTO people (name, age, username) VALUES
  ('Peter', 25, 'peter@example.com'),
  ('John', 30, 'john@example.com'),
  ('Katie', 18, 'katie@example.com');

INSERT INTO skill (name) VALUES
  ('Juggling'),
  ('Dancing'),
  ('Story-telling'),
  ('Singing');

INSERT INTO level (name) VALUES
  ('GOOD'),
  ('AWESOME'),
  ('GODLIKE');

INSERT INTO party (location, party_date) VALUES
  ('Old Folks Club', '2016-09-20'),
  ('Luxury Yacht Party', '2016-12-05');

INSERT INTO people_parties (person_id, party_id) VALUES
  (1, 1),
  (1, 2),
  (2, 1),
  (3, 2);

INSERT INTO skill_level (skill_id, level_id) VALUES
  (1, 1),
  (2, 2),
  (1, 2),
  (3, 3),
  (4, 1);

INSERT INTO people_skill_level (person_id, skill_id, level_id) VALUES
  (1, 1, 1),
  (1, 2, 2),
  (2, 1, 2),
  (2, 3, 3),
  (3, 4, 1);