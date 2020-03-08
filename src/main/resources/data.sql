
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

