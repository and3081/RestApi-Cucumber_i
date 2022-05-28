1. Тестирование rickandmortyapi.com: Сверка двух персонажей
2. Тестирование reqres.in: Проверка json ответа при создании юзера

# запуск всех тестов
mvn clean test

# запуск тестов по тегам
задаются в классе RunnerTest в опции tags, например:
tags = "@AllTests"
tags = "@TestsRick"
tags = "@TestsReqres"

# построение отчета Allure
mvn allure:serve

# настройки в проперти:
application.properties - параметризация тестов

