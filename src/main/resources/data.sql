-- Sample Artifacts
INSERT INTO artifact
(dtype, id, author, genre, language, library_location, publisher, release_year,
 subject, title, isbn, pages, total_stock, stock_on_loan)
VALUES ('Book', 1, 'Jane Austin', 'Classic Regency Novel', 'English', 'A1000', 'Penguin', 1813, 'Book',
        'Pride and Prejudice', '10602345', 431, 5, 4),
       ('Book', 2, 'J.K. Rowling', 'Fantasy Fiction', 'English', 'A1001', 'Harper', 2012, 'Book',
        'Harry Potter and the Half-Blood Prince', '10002345', 534, 5, 2),
       ('Book', 3, 'J.K. Rowling', 'Fantasy Fiction', 'English', 'A1001', 'Harper', 2012, 'Book',
        'Harry Potter and Deathly Hallows', '10002345', 534, 5, 2),
       ('Book', 4, 'Stephen King', 'Horror', 'English', 'A1001', 'Bloomberg', 2012, 'Book', 'Pet Sematary',
        '10002345', 534, 5, 2),
       ('Book', 5, 'Stephen King', 'Horror', 'English', 'A1001', 'Bloomberg', 2012, 'Book', 'It', '10002345',
        534, 5, 2),
       ('Book', 6, 'Stephen King', 'Horror', 'English', 'A1001', 'Harper', 2012, 'Book', 'The Gunslinger',
        '10002345', 534, 5, 2),
       ('Book', 7, 'Stephen King', 'Horror', 'English', 'A1001', 'Harper', 2012, 'Book', 'The Stand',
        '10002345', 534, 5, 2),
       ('Book', 8, 'Stephen King', 'Horror', 'English', 'A1001', 'Harper', 2012, 'Book', 'The Shining',
        '10002345', 534, 5, 2),
       ('Book', 9, 'Margaret Atwood', 'Dystopian Fiction', 'English', 'A1001', 'Penguin', 2012, 'Book',
        'The Handmaids Tale', '10002345', 534, 5, 2),
       ('Book', 10, 'Cecelia Aherne', 'Romance', 'English', 'A1001', 'Harper', 2012, 'Book', 'P.S. I Love You',
        '10002345', 534, 5, 2),
       ('Book', 11, 'Elizabeth Gilbert', 'Romance', 'English', 'A1001', 'Harper', 2012, 'Book',
        'Eat, Pray, Love', '10002345', 534, 5, 2);

INSERT INTO artifact(dtype, id, title,author, genre, language, library_location, publisher, release_year,
                     subject, runtime_minutes,total_stock, stock_on_loan)
                     VALUES ( 'CD',12,'The Planets, Op. 32','Gustav Holtz', 'Classical', 'N/A','A1001', 'Berliner Philharmoniker',
                             '1918','CD', 52, 1,0),( 'CD',13,'Shostakovich 5th Symphony','Shostakovich',
                            'Classical', 'N/A','A1001', 'Berliner Philharmoniker','1937','CD', 52, 1,0),
                            ( 'Video',16,'Spirited Away','Hayao Miyazaki', 'Coming of age', 'japanese','A1001', 'Studio Ghibli',
                            '2001','Video', 125, 1,0);
INSERT INTO artifact(dtype,id,title,author,genre,language,library_location,publisher,release_year, subject, frequency, total_stock,
stock_on_loan, issue) VALUES ('Magazine',14,'threads','multiple authors','crafts','english','A1001','Tauton Press','1972',
                       'Magazine','monthly',3,2,223),('Comic',15,'Journey into Mystery','multiple authors','fantasy','english','A1001','Marvel','2015',
                      'Comic','monthly',1,0,12);

INSERT INTO user(id, full_name, username, password, email, phone_number, role, created)
VALUES (1, 'my name', 'user1', 'password', 'user1@gmail.com', '+353 87 123 4567', 'member', '2020-05-06'),
       (2, 'saoirse Ronan', 'saoirseRon94', 'password', 'saoirseRonan@gmail.com', '+353 87 123 4567', 'admin',
        '2020-05-06'),
       (3, 'hozier', 'NinaCriedPower', 'password', 'hozier@gmail.com', '+353 87 123 4567', 'admin', '2020-05-06'),
       (4, 'Barak Obama', 'admin', 'password', 'barak@gmail.com', '+353 87 123 4567', 'admin', '2020-05-06');

INSERT INTO reservation(id, created, artifact_id, user_id)
VALUES (1, '2020-05-06', 2, 1),
       (2, '2020-07-06', 4, 1),
       (3, '2020-01-12', 5, 1);

INSERT INTO loan(id, created, due, returned, renewable, artifact_id, user_id)
VALUES (8, '2020-05-06', '2020-06-01', '2020-08-06', 0, 2, 1),
       (1, '2020-07-06', '2020-06-02', null, 1, 8, 1),
       (2, '2020-05-06', '2020-06-03', '2020-08-06', 0, 7, 1),
       (3, '2020-05-06', '2020-06-04', '2020-08-09',0,  10, 1),
       (4, '2020-05-06', '2020-06-05', '2020-08-06',0,  11, 1),
       (5, '2020-05-06', '2020-06-06', '2020-10-06',0,  6, 1),
       (6, '2020-05-06', '2020-06-07', '2020-08-06', 0, 2, 2),
       (7, '2020-02-02', '2020-02-08', null,0 ,  3, 1);

INSERT INTO motm(id, body_of_text )VALUES (1, 'Our pick this month is The Planets, Op. 32 by Gustav Holtz. Each movement of the suite is named after a
            planet in the solar system, however the work is based more on astrology than astromony. Our own UCD Symphony
            orchestra is preforming two of movements "Mars the bringer of War" and "Jupiter  the Bringer of Jollity" in
            the National Concert Hall on the 01/04/2020' );



INSERT INTO opening_hours (day, opening, closing, open_today)
VALUES (0, '09:00:00', '17:00:00', 1),
       (1, '09:00:00', '17:00:00', 1),
       (2, '09:00:00', '17:00:00', 1),
       (3, '09:00:00', '17:00:00', 1),
       (4, '09:00:00', '17:00:00', 1),
       (5, '10:00:00', '15:00:00', 1),
       (6, '00:00:00', '00:00:00', 0)
;

