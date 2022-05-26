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

- Добавим первое сообщение пользователя:

curl -i -X POST -H "Content-Type:application/json" -H "Content-Length:40" -H "Authorization:Bearer ТОКЕН" -H "Host:localhost" http://localhost:8080/messages/add -d "{\"name\":\"user\",\"message\":\"Hello world1\"}"

- Добавим второе сообщение пользователя

curl -i -X POST -H "Content-Type:application/json" -H "Content-Length:40" -H "Authorization:Bearer ТОКЕН" -H "Host:localhost" http://localhost:8080/messages/add -d "{\"name\":\"user\",\"message\":\"Hello world2\"}"

- Добавим третье сообщение пользователя

curl -i -X POST -H "Content-Type:application/json" -H "Content-Length:40" -H "Authorization:Bearer ТОКЕН" -H "Host:localhost" http://localhost:8080/messages/add -d "{\"name\":\"user\",\"message\":\"Hello world3\"}"

---

### Получение последних n сообщений пользователя

> GET messages/history?count=n

curl -i -X GET -H "accept: application/json" -H "Authorization:Bearer ТОКЕН" -H "Host:localhost" http://localhost:8080/messages/history?count=2

В ответе получим два последних сообщения пользователя

---