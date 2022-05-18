package ru.vasyukov.properties;

import org.aeonbits.owner.Config;

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
