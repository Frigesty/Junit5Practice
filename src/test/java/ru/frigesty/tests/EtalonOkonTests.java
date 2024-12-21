package ru.frigesty.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

@Tag("simple")
@DisplayName("Тесты на сайт etalonokon.ru")
public class EtalonOkonTests extends TestBase {

    @BeforeEach
    void setUp(){
        open("https://etalonokon.ru/");
    }

    @ValueSource(strings = {
            "Окна",
            "Профили",
            "Двери",
            "Алюминиевые конструкции",
            "Остекление балконов",
            "Остекление коттеджей"}
    )
    @Tag("Buttons")
    @ParameterizedTest(name = "При наведении курсора на кнопку в header {0} выпадает меню")
    void whenYouHoverOverTheHeaderButtonAMenuDropsDownTest(String value) {
        $$(".menu .menu-item").findBy(text(value)).hover();
        $$(".menu .menu-item").findBy(text(value)).$(".menu-submenu").shouldBe(visible);
    }
}