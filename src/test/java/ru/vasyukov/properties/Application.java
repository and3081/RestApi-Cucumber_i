package ru.vasyukov.properties;

import org.aeonbits.owner.Config;

/**
 * Интерфейс для работы с проперти из файла application.properties и системными проперти
 * usage:  TestData.application.имяМетодаКлюча()
 */
@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "system:env",
        "file:src/test/resources/ru.vasyukov/application.properties"
})
public interface Application extends Config {
    @Key("base.url.rickandmortyapi")
    String baseUrlRickandmortyapi();

    @Key("api.character")
    String apiCharacter();

    @Key("base.url.reqres")
    String baseUrlReqres();

    @Key("api.users")
    String apiUsers();
}
