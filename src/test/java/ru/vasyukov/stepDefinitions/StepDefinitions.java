package ru.vasyukov.stepDefinitions;

import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Затем;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import org.junit.jupiter.api.Assertions;
import ru.vasyukov.steps.ApiSteps;
import ru.vasyukov.steps.StepStorage;

public class StepDefinitions extends ApiSteps {
    private final StepStorage stepStorage = new StepStorage();

    @Когда("Находим ID персонажа1 по имени {string}")
    public void findCheckPersonIdForName(String nameFirstPers) {
        stepStorage.setPersonFirstId(findPersonIdForName(nameFirstPers));
        Assertions.assertNotNull(stepStorage.getPersonFirstId(),
                "ID персонажа '" + nameFirstPers + "' не найден");
    }

    @Затем("Находим персонажа по ID и проверяем у него наличие эпизодов")
    public void findFirstPersonCheckEpisodes() {
        stepStorage.setPersonFirst(getPerson(stepStorage.getPersonFirstId(), null));
        Assertions.assertFalse(stepStorage.getPersonFirst().getEpisode().isEmpty(),
                "Не найдены эпизоды у ID " + stepStorage.getPersonFirstId());
    }

    @Затем("Находим последний эпизод и проверяем у него наличие персонажей")
    public void findLastEpisodeCheckPersons() {
        stepStorage.setLastEpisode(getLastEpisode(stepStorage.getPersonFirst()));
        Assertions.assertFalse(stepStorage.getLastEpisode().getCharacters().isEmpty(),
                "Не найдены персонажи у эпизода");
    }

    @Затем("Находим последнего персонажа2")
    public void findLastPerson() {
        stepStorage.setPersonSecond(getLastPerson(stepStorage.getLastEpisode()));
    }

    @Тогда("Сверяем расу и локацию персонажа1 и персонажа2")
    public void assertTwoPersons() {
        System.out.printf("Перс.1: %s  Раса %s  Локация %s\n",
                stepStorage.getPersonFirst().getName(),
                stepStorage.getPersonFirst().getSpecies(),
                stepStorage.getPersonFirst().getLocation().getName());
        System.out.printf("Перс.2: %s  Раса %s  Локация %s\n",
                stepStorage.getPersonSecond().getName(),
                stepStorage.getPersonSecond().getSpecies(),
                stepStorage.getPersonSecond().getLocation().getName());
        Assertions.assertEquals(stepStorage.getPersonFirst().getSpecies(),
                stepStorage.getPersonSecond().getSpecies(),
                "Расы у двух персонажей разные");
        Assertions.assertEquals(stepStorage.getPersonFirst().getLocation().getName(),
                stepStorage.getPersonSecond().getLocation().getName(),
                "Локации у двух персонажей разные");
    }

    @Дано("Создаем файл с данными для запроса {string}")
    public void createCheckJsonFile(String filename) {
        stepStorage.setFilename(filename);
        Assertions.assertTrue(createJsonFile(filename),
                "Файл " + filename + " не создан");
    }

    @Когда("Создаем пользователя с данными из файла")
    public void createUserFromFile() {
        stepStorage.setRequestJson(bodyJson(stepStorage.getFilename()));
        stepStorage.setResponseJson(createUser(stepStorage.getRequestJson()));
    }

    @Тогда("Проверяем json ответа")
    public void assertResponse() {
        stepStorage.getRequestJson().put("id",
                stepStorage.getResponseJson().optString("id", "нет"));
        stepStorage.getRequestJson().put("createdAt",
                stepStorage.getResponseJson().optString("createdAt", "нет"));
        Assertions.assertTrue(stepStorage.getRequestJson()
                        .similar(stepStorage.getResponseJson()),
                "Json ответа не соответствует ожидаемому");
    }
}
