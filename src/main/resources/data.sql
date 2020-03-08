
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
        'Eat, Pray, Love', '10002345', 534, 5, 2)
;

INSERT INTO user(id, full_name, username, password, email, phone_number, role, created)
VALUES (0, 'my name' ,'user1', 'password', 'user1@gmail.com', '+353 87 123 4567', 'member', '2020-05-06'),
       (1, 'saoirse Ronan','saoirseRon94', 'password','saoirseRonan@gmail.com', '+353 87 123 4567', 'admin', '2020-05-06'),
       (2, 'hozier', 'NinaCriedPower','password', 'hozier@gmail.com', '+353 87 123 4567', 'admin', '2020-05-06');

INSERT INTO reservation(id, created, artifact_id, user_id)
VALUES (0, '2020-05-06', 2, 0),
    (1, '2020-07-06', 4, 0),
    (2, '2020-01-12', 5, 0);

INSERT INTO loan(id, created, due, returned, artifact_id, user_id)
VALUES (0, '2020-05-06', '2020-06-06', '2020-08-06', 2, 0),
       (1, '2020-07-06', '2020-06-06', null, 8, 0),
       (2, '2020-05-06', '2020-06-06', '2020-08-06', 7, 0),
       (3, '2020-05-06', '2020-06-06', '2020-08-09', 10, 0),
       (4, '2020-05-06', '2020-06-06', '2020-08-06', 11, 0),
       (5, '2020-05-06', '2020-06-06', '2020-10-06', 6, 0),
       (6, '2020-05-06', '2020-06-06', '2020-08-06', 2, 1),
       (7, '2020-02-02', '2020-02-03', null, 3, 0);

