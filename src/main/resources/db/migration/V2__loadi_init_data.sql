INSERT INTO chat(id, name) VALUES (nextval('chat_seq'), 'тест Юля');
INSERT INTO chat(id, name) VALUES (nextval('chat_seq'), 'тест тест');
INSERT INTO chat(id, name) VALUES (nextval('chat_seq'), 'тест Лёша');

INSERT INTO users(id, birth_date, first_name, last_name, login, password) VALUES (nextval('user_seq'), '2000-02-12', 'Анна', 'Тулисова', 'anna', '123');
INSERT INTO users(id, birth_date, first_name, last_name, login, password) VALUES (nextval('user_seq'), '1999-11-24', 'Юлия', 'Гуськова', 'julia', '123');
INSERT INTO users(id, birth_date, first_name, last_name, login, password) VALUES (nextval('user_seq'), '1994-06-20', 'Алексей', 'Стратонов', 'alex', '123');

INSERT INTO user_chats(user_id, chat_id) VALUES (1, 1);
INSERT INTO user_chats(user_id, chat_id) VALUES (1, 2);
INSERT INTO user_chats(user_id, chat_id) VALUES (1, 3);
INSERT INTO user_chats(user_id, chat_id) VALUES (2, 1);
INSERT INTO user_chats(user_id, chat_id) VALUES (2, 2);
INSERT INTO user_chats(user_id, chat_id) VALUES (3, 3);

