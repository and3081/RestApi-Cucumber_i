#language: ru
@AllTests
@TestsReqres
Функция: reqres.in
  Сценарий: Проверка json ответа
    Дано Создаем файл с данными для запроса "src/test/resources/myJson.json"
    Когда Создаем пользователя с данными из файла
    Тогда Проверяем json ответа