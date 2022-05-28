1. Тестирование rickandmortyapi.com: Сверка двух персонажей
2. Тестирование reqres.in: Проверка json ответа при создании юзера

# запуск всех тестов
mvn clean test

# запуск тестов по тегам
AllTests
TestsRick
TestsReqres

# построение отчета Allure
mvn allure:serve

# настройки в проперти:
application.properties - параметризация тестов

