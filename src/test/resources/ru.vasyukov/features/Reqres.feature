#language: ru
@AllTests
@TestsReqres
Функция: reqres.in

  Сценарий: Проверка LIST USERS
    Дано Запрашиваем все страницы юзеров
    Тогда Проверяем количество юзеров 12
    И Проверяем уникальность ID и email юзеров

  Сценарий: Проверка SINGLE USER
    Дано Запрашиваем юзера с ID 2, статус 200
    Тогда Проверяем юзера: ID 2, first_name "Janet", last_name "Weaver"

  Сценарий: Проверка SINGLE USER NOT FOUND
    Дано Запрашиваем юзера с ID 23, статус 404

  Сценарий: Проверка LIST RESOURCE
    Дано Запрашиваем все страницы ресурсов
    Тогда Проверяем количество ресурсов 12
    И Проверяем уникальность ID и name ресурсов

  Сценарий: Проверка SINGLE RESOURCE
    Дано Запрашиваем ресурс с ID 2, статус 200
    Тогда Проверяем ресурс: ID 2, name "fuchsia rose", color "#C74375"

  Сценарий: Проверка SINGLE RESOURCE NOT FOUND
    Дано Запрашиваем ресурс с ID 23, статус 404

  Сценарий: Проверка User Job CREATE
    Дано Создаем файл с данными для запроса '{"name": "Potato"}'
    И Создаем json из файла и дополняем 'job' 'Eat'
    Когда Создаем пользователя с данными из файла
    Тогда Проверяем json ответа CREATE

  Сценарий: Проверка User Job UPDATE PUT
    Дано Изменяем 'put' пользователя 2 на '{"name": "Potato", "job": "QA"}'
    Тогда Проверяем json ответа 'put'

  Сценарий: Проверка User Job UPDATE PATCH
    Дано Изменяем 'patch' пользователя 2 на '{"name": "Potato", "job": "Developer"}'
    Тогда Проверяем json ответа 'patch'

  Сценарий: Проверка User Job DELETE
    Дано Удаляем пользователя 2

  Сценарий: Проверка REGISTER - SUCCESSFUL
    Дано Проверяем запрос регистрации '{"email": "eve.holt@reqres.in", "password": "pistol"}', статус 200

  Сценарий: Проверка REGISTER - UNSUCCESSFUL
    Дано Проверяем запрос регистрации '{"email": "sydney@fife"}', статус 400

  Сценарий: Проверка LOGIN - SUCCESSFUL
    Дано Проверяем запрос авторизации '{"email": "eve.holt@reqres.in", "password": "cityslicka"}', статус 200

  Сценарий: Проверка LOGIN - UNSUCCESSFUL
    Дано Проверяем запрос авторизации '{"email": "sydney@fife"}', статус 400