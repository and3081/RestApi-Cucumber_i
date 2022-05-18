package ru.vasyukov;

import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.vasyukov.apiBase.ApiBase;
import ru.vasyukov.data.Episode;
import ru.vasyukov.data.Person;

public class Tests extends ApiBase {
    @DisplayName("Тест Сверка двух персонажей")
    @ParameterizedTest(name = "{arguments}")
    @MethodSource("ru.vasyukov.data.TestParams#providerTest1")
    public void TestTwoPersons(String namePers) {
        Integer persId = findPersonIdForName(namePers);
        Assertions.assertNotNull(persId,
                "ID персонажа '" + namePers + "' не найден");
        Person person1 = getPerson(persId , null);
        Assertions.assertFalse(person1.getEpisode().isEmpty(),
                "Не найдены эпизоды у ID " + persId);
        Episode episode = getLastEpisode(person1);
        Assertions.assertFalse(episode.getCharacters().isEmpty(),
                "Не найдены персонажи у эпизода");
        Person person2 = getLastPerson(episode);
        System.out.printf("Перс %s  Раса %s  Локация %s\n", person1.getName(), person1.getSpecies(), person1.getLocation().get("name"));
        System.out.printf("Перс %s  Раса %s  Локация %s\n", person2.getName(), person2.getSpecies(), person2.getLocation().get("name"));
        Assertions.assertEquals(person1.getSpecies(), person2.getSpecies(),
                "Расы у двух персонажей разные");
        Assertions.assertEquals(person1.getLocation().get("name"), person2.getLocation().get("name"),
                "Локации у двух персонажей разные");
    }

    @DisplayName("Тест Проверка json")
    @ParameterizedTest(name = "{displayName}")
    @MethodSource("ru.vasyukov.data.TestParams#providerTest2")
    public void TestJson(String filename) {
        Assertions.assertTrue(createJsonFile(filename), "Файл " + filename + " не создан");
        JSONObject jsonRequest = bodyJson(filename);
        JSONObject jsonResponse = createUser(jsonRequest);
        jsonRequest.put("id", jsonResponse.optString("id", "нет"));
        jsonRequest.put("createdAt", jsonResponse.optString("createdAt", "нет"));
        Assertions.assertTrue(jsonRequest.similar(jsonResponse), "Json ответа не соответствует ожидаемому");
    }
}
