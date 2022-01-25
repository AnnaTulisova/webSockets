## Приложение для websocket
### Запуск приложения.
1) ```mvn clean install```
2) Устанавливаем postgresql (запоминаем username и password)
3) Создать чистую базу данных
```sql
create database websocket;
```
4) Запустить приложение, как стандартный Spring boot проект и добавить env
```$xslt
DATABASE_URL=localhost;DATABASE_PORT=5432;DATABASE_NAME=websocket;DATABASE_USERNAME=ваше имя пользователя;DATABASE_PASSWORD=ваш пароль от БД;
```