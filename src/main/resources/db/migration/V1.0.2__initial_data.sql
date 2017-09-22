INSERT INTO users (user_id, username, password, role, enabled) VALUES
  (1, 'peter@example.com', '$2a$10$D4OLKI6yy68crm.3imC9X.P2xqKHs5TloWUcr6z5XdOqnTrAK84ri', 'ADMIN', TRUE),
  (2, 'john@example.com', '$2a$10$D4OLKI6yy68crm.3imC9X.P2xqKHs5TloWUcr6z5XdOqnTrAK84ri', 'VALIDATED_USER', TRUE),
  (3, 'katie@example.com', '$2a$10$D4OLKI6yy68crm.3imC9X.P2xqKHs5TloWUcr6z5XdOqnTrAK84ri', 'VALIDATED_USER', TRUE);

INSERT INTO people (person_id, name, age, username) VALUES
  (1, 'Peter', 25, 'peter@example.com'),
  (2, 'John', 30, 'john@example.com'),
  (3, 'Katie', 18, 'katie@example.com');

INSERT INTO skill (skill_id, name) VALUES
  (1, 'Juggling'),
  (2, 'Dancing'),
  (3, 'Story-telling'),
  (4, 'Singing');

INSERT INTO level (level_id, name) VALUES
  (1, 'GOOD'),
  (2, 'AWESOME'),
  (3, 'GODLIKE');

INSERT INTO party (party_id, location, party_date) VALUES
  (1, 'Old Folks Club', '2016-09-20'),
  (2, 'Luxury Yacht Party', '2016-12-05');

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

INSERT INTO people_skill_level (psl_id, person_id, skill_id, level_id) VALUES
  (1, 1, 1, 1),
  (2, 1, 2, 2),
  (3, 2, 1, 2),
  (4, 2, 3, 3),
  (5, 3, 4, 1);