package ru.vasyukov.properties;

import org.aeonbits.owner.Config;

/**
 * Интерфейс для работы с проперти из файла props.properties и системными проперти
 * usage:  TestData.props.имяМетодаКлюча()
 * Описание в файле проперти
 */
@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "system:env",
        "file:src/test/resources/ru.vasyukov/props.properties"
})
public interface Props extends Config {
    @Key("base.url.rickandmortyapi")
    String baseUrlRickandmortyapi();

    @Key("base.url.reqres")
    String baseUrlReqres();
}
