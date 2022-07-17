## Тестирование REST API rickandmortyapi.com и reqres.in
* Cucumber / RestAssured
* Java 8 / Maven
* Junit5
* Allure

## запуск всех тестов
mvn clean test

## запуск тестов по тегам
задаются в классе RunnerTest в опции tags, например:
* tags = "@AllTests"
* tags = "@TestsRick"
* tags = "@TestsReqres"

## построение отчета Allure
mvn allure:serve

## настройки в проперти:
* application.properties - настройки url и endpoints сайтов, параметризация тестов

## тест-кейсы:
### ТК1 - rickandmortyapi.com
* Сверка двух персонажей:
  * Находим ID персонажа1 по имени "Morty Smith"
  * Находим персонажа1 по ID и проверяем у него наличие эпизодов
  * Находим последний эпизод и проверяем у него наличие персонажей
  * Находим последнего персонажа2 из последнего эпизода
  * Сверяем расу и локацию персонажа1 и персонажа2:
    * Раса должна быть одинакова
    * Локация должна быть разной
### ТК2 - reqres.in
* Проверка json ответа при создании юзера:
  * Создаем файл "src/test/resources/myJson.json" с исходными данными:
    * {"name": "Potato"}
  * Читаем json из файла, модифицируем json для body запроса:
    * {"name":"Tomato","job":"Eat maket"}
  * Создаем пользователя с модифицированным json
  * Сверяем json ответа с json запроса
