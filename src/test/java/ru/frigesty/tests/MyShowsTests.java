package ru.frigesty.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.frigesty.tests.domain.Locale;
import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

@Tag("simple")
@DisplayName("Тесты на сайт https://myshows.me/")
public class MyShowsTests extends TestBase {

    @BeforeEach
    void setUp(){
        open("https://myshows.me/");
    }

    static Stream<Arguments> siteShouldContainAllButtonsAfterLanguageSelectionTest() {
        return Stream.of(
                Arguments.of(Locale.UA, List.of("Серіали", "Фільми", "Новини", "Добірки", "Рейтинги")),
                Arguments.of(Locale.RU, List.of("Сериалы", "Фильмы", "Новости", "Подборки", "Рейтинги")),
                Arguments.of(Locale.EN, List.of("Shows", "Movies", "News", "Collections", "Ratings"))
        );
    }

    @MethodSource
    @ParameterizedTest(name = "После выбора локали {0} должны отображаются кнопки {1}")
    @Tags({
        @Tag("Language"),
        @Tag("Buttons")
    })
    void siteShouldContainAllButtonsAfterLanguageSelectionTest(Locale locale, List<String> buttons) {
        $(".LangSwitcher").click();
        $$(".LangSwitcher-options .LangSwitcher-optionText").find(text(locale.name())).click();
        $$(".HeaderMenu .HeaderMenu__item").shouldHave(texts(buttons));
    }
}