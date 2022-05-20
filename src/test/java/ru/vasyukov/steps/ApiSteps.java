package ru.vasyukov.steps;

import org.junit.jupiter.api.Assertions;
import ru.vasyukov.apiBase.ApiBase;

public class ApiSteps extends ApiBase {
    private final DataStorage dataStorage = new DataStorage();

    public void findCheckPersonIdForName(String nameFirstPers) {
        dataStorage.setPersonFirstId(findPersonIdForName(nameFirstPers));
        Assertions.assertNotNull(dataStorage.getPersonFirstId(),
                "ID персонажа '" + nameFirstPers + "' не найден");
    }

    public void findFirstPersonCheckEpisodes() {
        dataStorage.setPersonFirst(getPerson(dataStorage.getPersonFirstId(), null));
        Assertions.assertFalse(dataStorage.getPersonFirst().getEpisode().isEmpty(),
                "Не найдены эпизоды у ID " + dataStorage.getPersonFirstId());
    }

    public void findLastEpisodeCheckPersons() {
        dataStorage.setLastEpisode(getLastEpisode(dataStorage.getPersonFirst()));
        Assertions.assertFalse(dataStorage.getLastEpisode().getCharacters().isEmpty(),
                "Не найдены персонажи у эпизода");
    }

    public void findSecondPerson() {
        dataStorage.setPersonSecond(getLastPerson(dataStorage.getLastEpisode()));
    }

    public void assertTwoPersons() {
        System.out.printf("Перс.1: %s  Раса %s  Локация %s\n",
                dataStorage.getPersonFirst().getName(),
                dataStorage.getPersonFirst().getSpecies(),
                dataStorage.getPersonFirst().getLocation().getName());
        System.out.printf("Перс.2: %s  Раса %s  Локация %s\n",
                dataStorage.getPersonSecond().getName(),
                dataStorage.getPersonSecond().getSpecies(),
                dataStorage.getPersonSecond().getLocation().getName());
        Assertions.assertEquals(dataStorage.getPersonFirst().getSpecies(),
                dataStorage.getPersonSecond().getSpecies(),
                "Расы у двух персонажей разные");
        Assertions.assertEquals(dataStorage.getPersonFirst().getLocation().getName(),
                dataStorage.getPersonSecond().getLocation().getName(),
                "Локации у двух персонажей разные");
    }
}
