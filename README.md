## Тестирование REST API rickandmortyapi.com и reqres.in
* Cucumber / RestAssured
* Java 8 / Maven
* Junit5
* Allure

## запуск всех тестов
`mvn clean test`

## запуск тестов по тегам
`mvn clean test -D"cucumber.filter.tags=@тег or @тег"`<br>
_или_<br>
теги задаются в классе RunnerTest в опции tags:<br>
`tags = "@тег or @тег"`

## построение отчета Allure
`mvn allure:serve`

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
### Чеклист1 - reqres.in
* Проверка LIST USERS
* Проверка SINGLE USER
* Проверка SINGLE USER NOT FOUND
* Проверка LIST RESOURCE
* Проверка SINGLE RESOURCE
* Проверка SINGLE RESOURCE NOT FOUND
* Проверка User Job CREATE
* Проверка User Job UPDATE PUT
* Проверка User Job UPDATE PATCH
* Проверка User Job DELETE
* Проверка REGISTER - SUCCESSFUL
* Проверка REGISTER - UNSUCCESSFUL
* Проверка LOGIN - SUCCESSFUL
* Проверка LOGIN - UNSUCCESSFUL