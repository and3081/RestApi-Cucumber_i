#language: ru
@AllTests
@TestsReqres
Функция: reqres.in

  Сценарий: Проверка Create User с Job
    Дано Создаем файл с данными для запроса '{"name": "Potato"}'
    И Создаем json из файла и дополняем 'job' 'Eat'
    Когда Создаем пользователя с данными из файла
    Тогда Проверяем json ответа
