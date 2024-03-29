/*
    Database initialization script that runs on every web-application redeployment.
*/
DROP TABLE IF EXISTS user_ass;
DROP TABLE IF EXISTS assignment_page;
DROP TABLE IF EXISTS text_page;
DROP TABLE IF EXISTS attendance;
DROP TABLE IF EXISTS users;


CREATE TABLE users (
    email text NOT NULL check (email ~ '.*?@.*') PRIMARY KEY,
    name TEXT NOT NULL,
    role TEXT NOT NULL
);

CREATE TABLE text_page (
    title TEXT PRIMARY KEY,
    content TEXT NOT NULL,
    is_published BOOLEAN
	CONSTRAINT content_not_empty CHECK (content <> '')
);

CREATE TABLE assignment_page (
    title TEXT PRIMARY KEY,
    question TEXT NOT NULL,
    answer TEXT ,
    max_score INTEGER NOT NULL,
    minimum_score INTEGER,
    actual_score INTEGER,
    is_published BOOLEAN

);

CREATE TABLE user_ass (
    name TEXT,
    student_email TEXT,
    role TEXT,
    assignment_title TEXT,
    is_published BOOLEAN,
    question TEXT,
    answer TEXT,
    max_score INTEGER,
    actual_score INTEGER,
    minimum_score INTEGER,
    PRIMARY KEY (student_email, assignment_title),
    FOREIGN KEY(student_email) REFERENCES users(email),
    FOREIGN KEY(assignment_title) REFERENCES assignment_page(title)
    );


CREATE TABLE attendance (
    att_date TEXT NOT NULL,
    att_email TEXT NOT NULL,
    PRIMARY KEY (att_date, att_email),
    FOREIGN KEY (att_email) REFERENCES users(email)
);


INSERT INTO users (email, name,role) VALUES
	('user1@user1', 'user1','Mentor'), -- 1
	('user2@user2', 'user2','Student'), -- 2
	('user3@user3', 'user3','Student'); -- 3

INSERT INTO text_page (title,content,is_published) VALUES
	('Python','Hello World!','false'),   -- 1
	('Java','Waste Recycling','false');  -- 2


INSERT INTO assignment_page (title, question, max_score,minimum_score,is_published) VALUES
	('Python feladat','Hogy van a Hello World pythonban?',12,0,'false'),           -- 1
	('Java feladat','Hogy van a Hello World Javaban?',12,0,'false');          -- 1


INSERT INTO user_ass(name,student_email,role,assignment_title,question,answer,max_score,actual_score,minimum_score)VALUES
('Lakatos Armando','user3@user3','Student','Python feladat','Hogy van a Hello World pythonban?','Print("Hello World")',12,12,0);