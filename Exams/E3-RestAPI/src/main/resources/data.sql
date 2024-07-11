INSERT INTO albums(title, genre, release_date, sales, has_vinyl)
VALUES ('ASTROWORLD', 'HIPHOP', '20180803', 4907811.67, true),
       ('Her Loss', 'POP', '20221104', 2457811.79, false);

INSERT INTO artists(name, birth_date, city, monthly_listeners, label)
VALUES ('Travis Scott', '19910430', 'Texas', 45372340, 'Cactus Jack Records'),
       ('Drake', '19861024', 'Toronto', 67903340, 'OVO Sound'),
       ('21 Savage', '19921022', 'Atlanta', 52567074, 'Slaughter Gang');

INSERT INTO album_artists(album_id, artist_id)
VALUES (1, 1),
       (2, 2),
       (2, 3);

INSERT INTO songs(name, time, album_id)
VALUES ('STARGAZING', '000430', 1),
       ('CAROUSEL', '000300', 1),
       ('SICKO MODE', '000512', 1),
       ('R.I.P. SCREW', '000305', 1),
       ('STOP TRYING TO BE GOD', '000538', 1),
       ('NO BYSTANDERS', '000338', 1),
       ('SKELETONS', '000225', 1),
       ('WAKE UP', '000351', 1),
       ('5% TINT', '000316', 1),
       ('NC-17', '000236', 1),
       ('ASTROTHUNDER', '000222', 1),
       ('YOSEMITE', '000230', 1),
       ('CAN''T SAY', '000318', 1),
       ('WHO? WHAT!', '000256', 1),
       ('BUTTERFLY EFFECT', '000310', 1),
       ('HOUSTONFORNICATION', '000337', 1),
       ('COFFEE BEAN', '000329', 1),
       ('Rich Flex', '000359', 2),
       ('Major Distribution', '000250', 2),
       ('On BS', '000421', 2),
       ('BackOutsideBoyz', '000232', 2),
       ('Privileged Rappers', '000240', 2),
       ('Spin Bout U', '000334', 2),
       ('Hours In Silence', '000639', 2),
       ('Treacherous Twins', '000300', 2),
       ('Circo Loco', '000356', 2),
       ('Pussy & Millions (feat. Travis Scott)', '000402', 2),
       ('Broke Boys', '000345', 2),
       ('Middle of the Ocean', '000556', 2),
       ('Jumbotron Shit Poppin', '000217', 2),
       ('More M''s', '000341', 2),
       ('3AM on Glenwood', '000258', 2),
       ('I Guess It''s Fuck Me', '000423', 2);