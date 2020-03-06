
-- Sample Artifacts
INSERT INTO artifact
(dtype, id, author, genre, language, library_location, publisher, release_year,
 subject, title, isbn, pages, frequency, total_stock, stock_on_loan)
VALUES ('Book', 2, 'J.K. Rowling', 'Fantasy Fiction', 'English', 'A1001', 'Harper', 2012, 'Book',
        'Harry Potter and the Half-Blood Prince', '10002345', 534, '2 Weeks', 5, 2),
       ('Book', 3, 'J.K. Rowling', 'Fantasy Fiction', 'English', 'A1001', 'Harper', 2012, 'Book',
        'Harry Potter and Deathly Hallows', '10002345', 534, '2 Weeks', 5, 2),
       ('Book', 4, 'Stephen King', 'Horror', 'English', 'A1001', 'Bloomberg', 2012, 'Book', 'Pet Sematary',
        '10002345', 534, '2 Weeks', 5, 2),
       ('Book', 5, 'Stephen King', 'Horror', 'English', 'A1001', 'Bloomberg', 2012, 'Book', 'It', '10002345',
        534, '2 Weeks', 5, 2),
       ('Book', 6, 'Stephen King', 'Horror', 'English', 'A1001', 'Harper', 2012, 'Book', 'The Gunslinger',
        '10002345', 534, '2 Weeks', 5, 2),
       ('Book', 7, 'Stephen King', 'Horror', 'English', 'A1001', 'Harper', 2012, 'Book', 'The Stand',
        '10002345', 534, '2 Weeks', 5, 2),
       ('Book', 8, 'Stephen King', 'Horror', 'English', 'A1001', 'Harper', 2012, 'Book', 'The Shining',
        '10002345', 534, '2 Weeks', 5, 2),
       ('Book', 9, 'Margaret Atwood', 'Dystopian Fiction', 'English', 'A1001', 'Penguin', 2012, 'Book',
        'The Handmaids Tale', '10002345', 534, '2 Weeks', 5, 2),
       ('Book', 10, 'Cecelia Aherne', 'Romance', 'English', 'A1001', 'Harper', 2012, 'Book', 'P.S. I Love You',
        '10002345', 534, '2 Weeks', 5, 2),
       ('Book', 11, 'Elizabeth Gilbert', 'Romance', 'English', 'A1001', 'Harper', 2012, 'Book',
        'Eat, Pray, Love', '10002345', 534, '2 Weeks', 5, 2)
;

INSERT INTO user(id, full_name, username, password, email, phone_number, role, created)
VALUES (0, 'my name', 'user1', 'password', 'user1@gmail.com', '+353 87 123 4567', 'admin', '2020-05-06'),
       (1, 'saoirse Ronan', 'user2', 'password', 'user2@gmail.com', '+353 87 123 4567', 'admin', '2020-05-06'),
       (2, 'hozier', 'user3', 'password', 'user3@gmail.com', '+353 87 123 4567', 'member', '2020-05-06');


