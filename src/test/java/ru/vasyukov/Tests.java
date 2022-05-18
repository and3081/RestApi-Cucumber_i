package ru.vasyukov;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.vasyukov.apiBase.ApiBase;

public class Tests extends ApiBase {
    @DisplayName("Тест ")
    @Test
    public void Test() {
        System.out.println("id "+findPersIdForName("Morty Smith")); //"Morty Smith"
        //getPers();

    }
}
