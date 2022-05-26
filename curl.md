Запросы curl
---

### Вход в приложение

> POST api/v1/authentication

curl -i -X POST -H "Content-Type:application/json" -H "Content-Length:33" -H "Host:localhost" http://localhost:8081/api/v1/authenticate -d '{"name":"zawgy","password":"123"}'

В ответе получим токен

---

*Далее необходимо в запросы вставить полученный ранее токен c приставкой Bearer_*

### Создание новых сообщений

> POST api/v1/messages

- Добавление тестовых сообщений

curl -i -X POST -H "Content-Type:application/json" -H "Authorization:Bearer_TOKEN" -H "Host:localhost" http://localhost:8081/api/v1/message -d '{"name":"zawgy","message":"First message"}'


curl -i -X POST -H "Content-Type:application/json" -H "Authorization:Bearer_TOKEN" -H "Host:localhost" http://localhost:8081/api/v1/message -d '{"name":"zawgy","message":"Second message"}'


curl -i -X POST -H "Content-Type:application/json" -H "Authorization:Bearer_TOKEN" -H "Host:localhost" http://localhost:8081/api/v1/message -d '{"name":"zawgy","message":"Third message"}'

---

### Получение последних n сообщений пользователя

> POST api/v1/messages

curl -i -X POST -H "Content-Type:application/json" -H "Authorization:Bearer_TOKEN" -H "Host:localhost" http://localhost:8081/api/v1/message -d '{"name":"zawgy","message":"history 2"}'

В ответе получим два последних сообщения пользователя

---
