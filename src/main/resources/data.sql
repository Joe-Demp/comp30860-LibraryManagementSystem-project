insert into artifact(dtype, id, title, author)
values ('Book', 1, 'Pride and Prejudice', 'Jane Austin');

-- Sample Artifacts
INSERT INTO artifact
(dtype, id, author, genre, language, library_location, publisher, release_date, subject, title, isbn, pages, frequency)
VALUES ('Book', 2, 'J.K. Rowling', 'Fantasy Fiction', 'English', 'A1001', 'Harper', '2005-05-05', 'Book',
        'Harry Potter and the Half-Blood Prince', '10002345', 534, '2 Weeks'),
       ('Book', 3, 'J.K. Rowling', 'Fantasy Fiction', 'English', 'A1001', 'Harper', '2005-05-05', 'Book',
        'Harry Potter and Deathly Hallows', '10002345', 534, '2 Weeks'),
       ('Book', 4, 'Stephen King', 'Horror', 'English', 'A1001', 'Bloomberg', '2005-05-05', 'Book', 'Pet Sematary',
        '10002345', 534, '2 Weeks'),
       ('Book', 5, 'Stephen King', 'Horror', 'English', 'A1001', 'Bloomberg', '2005-05-05', 'Book', 'It', '10002345',
        534, '2 Weeks'),
       ('Book', 6, 'Stephen King', 'Horror', 'English', 'A1001', 'Harper', '2005-05-05', 'Book', 'The Gunslinger',
        '10002345', 534, '2 Weeks'),
       ('Book', 7, 'Stephen King', 'Horror', 'English', 'A1001', 'Harper', '2005-05-05', 'Book', 'The Stand',
        '10002345', 534, '2 Weeks'),
       ('Book', 8, 'Stephen King', 'Horror', 'English', 'A1001', 'Harper', '2005-05-05', 'Book', 'The Shining',
        '10002345', 534, '2 Weeks'),
       ('Book', 9, 'Margaret Atwood', 'Dystopian Fiction', 'English', 'A1001', 'Penguin', '2005-05-05', 'Book',
        'The Handmaids Tale', '10002345', 534, '2 Weeks'),
       ('Book', 10, 'Cecelia Aherne', 'Romance', 'English', 'A1001', 'Harper', '2005-05-05', 'Book', 'P.S. I Love You',
        '10002345', 534, '2 Weeks'),
       ('Book', 11, 'Elizabeth Gilbert', 'Romance', 'English', 'A1001', 'Harper', '2005-05-05', 'Book',
        'Eat, Pray, Love', '10002345', 534, '2 Weeks')
;

INSERT INTO user(id, full_name, username, password, email, phone_number, role, created)
VALUES (0, 'my name' ,'user1', 'password', 'user1@gmail.com', '+353 87 123 4567', 'admin', '2020-05-06');