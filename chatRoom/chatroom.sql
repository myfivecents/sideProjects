DROP DATABASE chatroom;
CREATE DATABASE chatroom;

\c chatroom;

CREATE TABLE chat_room(
    id serial,
    chat_name varchar NOT NULL,
    UNIQUE (chat_name),
    CONSTRAINT pk_chat_room_id PRIMARY KEY (id)
);

CREATE TABLE messages(
    id serial,
    date_time timestamp NOT NULL,
    chat_id integer NOT NULL,
    account_id integer NOT NULL,
    message varchar NOT NULL,
    CONSTRAINT pk_messages_id PRIMARY KEY (id)
);

CREATE TABLE accounts(
    id serial,
    username varchar NOT NULL,
    first_name varchar NOT NULL,
    last_name varchar NOT NULL,
    password varchar NOT NULL,
    CONSTRAINT pk_accounts_id PRIMARY KEY (id),
    CONSTRAINT password_length CHECK (char_length(password) >= 8)
);

CREATE TABLE account_chat(
    account_id integer,
    chat_id integer,
    CONSTRAINT pk_account_chat_account_id_chat_id PRIMARY KEY (account_id, chat_id)
);



CREATE TABLE blog_post(
    id serial,
    account_id integer NOT NULL,
    title varchar NOT NULL,
    body text NOT NULL,
    date_time timestamp NOT NULl,
    notes varchar,
    CONSTRAINT pk_blog_post_id PRIMARY KEY (id)
);

INSERT INTO chat_room (id, chat_name) VALUES (1, 'chat1');
INSERT INTO chat_room (id, chat_name) VALUES (2, 'chat2');
INSERT INTO chat_room (id, chat_name) VALUES (3, 'chat3');
INSERT INTO chat_room (id, chat_name) VALUES (4, 'chat4');

SELECT setval(pg_get_serial_sequence('chat_room', 'id'), 4);


INSERT INTO accounts (id, username, first_name, last_name, password) VALUES (1, 'user1', 'John', 'Doe', 'password123');
INSERT INTO accounts (id, username, first_name, last_name, password) VALUES (2, 'user2', 'Jacob', 'Nickell', 'password123');
INSERT INTO accounts (id, username, first_name, last_name, password) VALUES (3, 'user3', 'Sallie', 'Mae', 'password123');
INSERT INTO accounts (id, username, first_name, last_name, password) VALUES (4, 'user4', 'Abraham', 'Lincoln', 'password123');
INSERT INTO accounts (id, username, first_name, last_name, password) VALUES (5, 'user5', 'Elvis', 'Presley', 'password123');

SELECT setval(pg_get_serial_sequence('accounts', 'id'), 5);


INSERT INTO messages ( id, date_time, chat_id, account_id, message) VALUES (1, '2017-09-26 15:36:38', 1, 1, 'This is the first message');
INSERT INTO messages ( id, date_time, chat_id, account_id, message) VALUES (2, '2017-09-27 12:30:54', 2, 2, 'This is the second message');
INSERT INTO messages ( id, date_time, chat_id, account_id, message) VALUES (3, '2017-09-28 20:20:20', 3, 3, 'This is the third message');
INSERT INTO messages ( id, date_time, chat_id, account_id, message) VALUES (4, '2017-09-29 23:59:59', 4, 4, 'This is the fourth message');
INSERT INTO messages ( id, date_time, chat_id, account_id, message) VALUES (5, '2017-09-30 01:01:01', 1, 5, 'This is the fifth message');
INSERT INTO messages ( id, date_time, chat_id, account_id, message) VALUES (6, '2017-10-01 09:27:30', 2, 1, 'This is the sixth message');
INSERT INTO messages ( id, date_time, chat_id, account_id, message) VALUES (7, '2017-10-02 12:30:09', 3, 2, 'This is the seventh message');
INSERT INTO messages ( id, date_time, chat_id, account_id, message) VALUES (8, '2017-10-03 05:30:59', 4, 3, 'This is the eigth message');
INSERT INTO messages ( id, date_time, chat_id, account_id, message) VALUES (9, '2017-10-04 01:20:00', 1, 4, 'This is the ninth message');

SELECT setval(pg_get_serial_sequence('messages', 'id'), 9);


INSERT INTO blog_post (id, account_id, title, body, date_time) VALUES (1, 1, 'blog post 1', 'this is my first blog post!!!', '2017-09-26 15:36:38');
INSERT INTO blog_post (id, account_id, title, body, date_time) VALUES (2, 2, 'blog post 2', 'this is my second blog post!!!', '2017-09-27 12:30:54');
INSERT INTO blog_post (id, account_id, title, body, date_time) VALUES (3, 3, 'blog post 3', 'this is my third blog post!!!', '2017-09-28 20:20:20');
INSERT INTO blog_post (id, account_id, title, body, date_time) VALUES (4, 4, 'blog post 4', 'this is my fourth blog post!!!', '2017-09-29 23:59:59');
INSERT INTO blog_post (id, account_id, title, body, date_time) VALUES (5, 5, 'blog post 5', 'this is my fifth blog post!!!', '2017-09-30 01:01:01');

SELECT setval(pg_get_serial_sequence('blog_post', 'id'), 9);

INSERT INTO account_chat (account_id, chat_id) VALUES (1, 1);
INSERT INTO account_chat (account_id, chat_id) VALUES (1, 2);
INSERT INTO account_chat (account_id, chat_id) VALUES (1, 3);
INSERT INTO account_chat (account_id, chat_id) VALUES (1, 4);
INSERT INTO account_chat (account_id, chat_id) VALUES (2, 1);
INSERT INTO account_chat (account_id, chat_id) VALUES (3, 1);
INSERT INTO account_chat (account_id, chat_id) VALUES (2, 2);




ALTER TABLE messages
ADD FOREIGN KEY (chat_id)
REFERENCES chat_room(id);

ALTER TABLE messages
ADD FOREIGN KEY (account_id)
REFERENCES accounts(id);

ALTER TABLE blog_post
ADD FOREIGN KEY (account_id)
REFERENCES accounts(id);

ALTER TABLE account_chat
ADD FOREIGN KEY (account_id)
REFERENCES accounts(id);

ALTER TABLE account_chat
ADD FOREIGN KEY (chat_id)
REFERENCES chat_room(id);
